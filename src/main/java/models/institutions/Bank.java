package models.institutions;

import models.accounts.BalanceSheet;
import models.balancesheet.Asset;
import models.balancesheet.Classification;
import models.statements.BalanceSheetStatement;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.utility.MapIterate;

import java.util.List;

/**
 * Created by: tituskc
 * Created On  Thu, Aug 31, 2017 at 9:29 PM.
 */
public class Bank implements Institution
{
    private String name;
    public final boolean defaults = false;
    private final BalanceSheet balanceSheet;

    public Bank(BalanceSheet balanceSheet)
    {
        this.balanceSheet = balanceSheet;
        this.name = balanceSheet.getName();
    }
    public String getName()
    {
        return this.name;
    }

    public BalanceSheet getBalanceSheet()
    {
        return this.balanceSheet;
    }

    public BalanceSheetStatement getBalanceSheetStatement()
    {
        return new BalanceSheetStatement(balanceSheet);
    }
    public double getAssetsValue()
    {
        return this.balanceSheet.getTotalAssets();
    }
    public double getLiabilitiesValue()
    {
        return this.balanceSheet.getTotalLiabilities();
    }
    public double getTotalCapital()
    {
        return this.balanceSheet.getTotalCapital();
    }
    public List<Asset> getExternalAssets()
    {
        return MapIterate.select(this.balanceSheet.getAssets(), (Predicate<Asset>) asset -> Classification.EXTERNAL_ASSETS.equals(asset.getClassification()));
    }
}
