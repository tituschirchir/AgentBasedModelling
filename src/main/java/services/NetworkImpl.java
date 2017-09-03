package services;

import com.gs.collections.impl.map.mutable.UnifiedMap;
import models.accounts.BalanceSheet;
import models.balancesheet.Classification;
import models.balancesheet.Tier;
import models.institutions.Bank;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.utility.ListIterate;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by: tituskc
 * Created On  Fri, Sep 01, 2017 at 8:00 PM.
 */
public class NetworkImpl implements Network
{
    private final List<Bank> banks = FastList.newList();
    private int links = 0;
    int failures = 0;
    int index = 0;
    private int N;
    private double p;
    private double entropy = 0;
    private List<List<Integer>> inverseAdjacencyMatrix = FastList.newList();
    private List<List<Integer>> prunedAdjacencyMatrix = FastList.newList();
    private boolean generatePlots = false;
    private final boolean isRandom;

    public void addBank(Bank bank)
    {
        banks.add(bank);
    }

    public NetworkImpl(int N, double p, double A, double theta, double gamma, boolean generatePlots)
    {
        this(N, p, A, theta, gamma, generatePlots, true);
    }

    public NetworkImpl(int N, double p, double A, double theta, double gamma, boolean generatePlots, boolean isRandom)
    {
        this.N = N;
        this.p = p;
        this.generatePlots = generatePlots;
        this.isRandom = isRandom;
        initializeAdjacencyMatrix();
        initiateNetwork(A, theta, gamma);
    }

    @Override
    public void initializeAdjacencyMatrix()
    {
        List<Integer> temp;
        Random rand = isRandom ? new Random() : new Random(50);
        for (int i = 0; i < N; ++i) {
            temp = FastList.newList();
            for (int j = 0; j < N; ++j)
                temp.add((i != j && rand.nextDouble() > p) ? 1 : 0);
            inverseAdjacencyMatrix.add(temp);
        }
        int v;
        for (int i = 0; i < N; ++i) {
            temp = FastList.newList();
            for (int j = 0; j < N; ++j) {
                v = inverseAdjacencyMatrix.get(i).get(j) * (1 - inverseAdjacencyMatrix.get(j).get(i));
                if (v == 1) {
                    temp.add(v);
                    links += 1;
                } else temp.add(0);
            }
            prunedAdjacencyMatrix.add(temp);
        }
        for (int i = 0; i < N; ++i) {
            temp = FastList.newList();
            for (int j = 0; j < N; ++j)
                temp.add(prunedAdjacencyMatrix.get(j).get(i));
            inverseAdjacencyMatrix.add(temp);
        }
    }

    @Override
    public void initiateNetwork(double A, double theta, double gamma)
    {
        double e_bk = links == 0 ? 0 : theta * A / links;
        String prefix = "Bank ";
        for (int i = 0; i < N; ++i) {
            final double interbankAssets = ListIterate.sumOfInt(inverseAdjacencyMatrix.get(i), integer -> integer) * e_bk;
            double excessAssets = A / N - interbankAssets;
            final double externalAssets = excessAssets > 0 ? excessAssets : 0;
            final double capital = (interbankAssets + externalAssets) * gamma;
            final double interbankBorrowing = ListIterate.sumOfInt(prunedAdjacencyMatrix.get(i), integer -> integer) * e_bk;
            double customerDeposits = externalAssets + interbankAssets - capital - interbankBorrowing;

            BalanceSheet balanceSheet = new BalanceSheet(prefix + i);
            balanceSheet.addLiability(interbankBorrowing, Tier.TIER_1, Classification.INTERBANK_BORROWING);
            balanceSheet.addLiability(customerDeposits, Tier.TIER_1, Classification.CUSTOMER_DEPOSITS);
            balanceSheet.addAsset(interbankAssets, Tier.TIER_2, Classification.INTERBANK_ASSET);
            balanceSheet.addAsset(externalAssets, Tier.TIER_1, Classification.EXTERNAL_ASSETS);
            balanceSheet.addCapital(capital, Tier.TIER_1, Classification.CAPITAL);
            banks.add(new Bank(balanceSheet));
        }
        if (e_bk > 0) entropy += e_bk * links * Math.log(e_bk);
    }

    @Override
    public List<Integer> getExposureVector(List<Integer> expVec, int pos)
    {
        return null;
    }

    @Override
    public List<Bank> getBanks()
    {
        return this.banks;
    }

    public Map<String, List<List<Object>>> getBankStatements()
    {
        Map<String, List<List<Object>>> bss = UnifiedMap.newMap();
        ListIterate.forEach(banks, bank -> bss.put(bank.getName(), bank.getBalanceSheetStatement().getContents()));
        return bss;
    }

    @Override
    public void simulateShock(int pos, double shock, boolean isInitial)
    {

    }

    @Override
    public int getFailures()
    {
        return 0;
    }

    @Override
    public List<List<Integer>> getPrunedAdjacencyMatrix()
    {
        return this.prunedAdjacencyMatrix;
    }

    @Override
    public void writeNetworkData()
    {

    }

    @Override
    public void writeMetaData(double initialShock)
    {

    }

    @Override
    public double getMarketLoss()
    {
        return 0;
    }

    @Override
    public double getEntropy()
    {
        return 0;
    }
}
