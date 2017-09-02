package models.accounts;

import com.gs.collections.impl.map.mutable.UnifiedMap;
import models.balancesheet.*;
import org.eclipse.collections.impl.utility.MapIterate;

import java.util.Map;

/**
 * Created by: tituskc
 * Created On  Thu, Aug 31, 2017 at 9:58 PM.
 */
public class BalanceSheet
{
    private final String name;
    private Map<String, Asset> assets= UnifiedMap.newMap();
    private Map<String, Liability> liabilities = UnifiedMap.newMap();
    private Map<String, Capital> capital = UnifiedMap.newMap();

    public BalanceSheet(String name){
        this.name = name;
    }
    public void addAsset(double value, Tier tier, Classification classification)
    {
        String name = classification.name();
        if(assets.get(name)==null)
            assets.put(name, Asset.createAsset(value, tier, classification));
        else
            assets.get(name).setValue(assets.get(name).getValue() + value);
    }
    public void addLiability(double value, Tier tier, Classification classification)
    {
        String name = classification.name();
        if(liabilities.get(name)==null)
            liabilities.put(name, Liability.createLiability(value, tier, classification));
        else
            liabilities.get(name).setValue(liabilities.get(name).getValue() + value);
    }

    public void addCapital(double value, Tier tier, Classification classification)
    {
        String name = classification.name();
        if(this.capital.get(name)==null)
            this.capital.put(name, Capital.createCapital(tier, value, classification));
        else
            this.capital.get(name).setValue(this.capital.get(name).getValue() + value);
    }

    public boolean balances()
    {
        return getTotalAssets() == (getTotalLiabilities() + getTotalCapital());
    }

    public double getTotalCapital()
    {
        return MapIterate.collectDouble(capital, Capital::getValue).sum();
    }

    public double getTotalLiabilities()
    {
        return MapIterate.collectDouble(liabilities, Liability::getValue).sum();
    }

    public double getTotalAssets()
    {
        return MapIterate.collectDouble(assets, Asset::getValue).sum();
    }

    public Map<String, Asset> getAssets()
    {
        return assets;
    }
    public Map<String, Liability> getLiabilities()
    {
        return liabilities;
    }

    public Map<String, Capital> getCapital()
    {
        return capital;
    }

    public String name()
    {
        return "Balance Sheet";
    }

    public String getName()
    {
        return name;
    }
}
