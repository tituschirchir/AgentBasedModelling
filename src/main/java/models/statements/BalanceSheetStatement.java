package models.statements;

import models.accounts.BalanceSheet;
import models.balancesheet.Asset;
import models.balancesheet.Capital;
import models.balancesheet.Liability;
import models.balancesheet.Valuable;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.utility.MapIterate;
import org.eclipse.collections.impl.utility.StringIterate;

import java.util.List;
import java.util.Map;

/**
 * Created by: tituskc
 * Created On  Fri, Sep 01, 2017 at 3:59 PM.
 */
public class BalanceSheetStatement implements AccountStatement
{
    private final BalanceSheet balanceSheet;
    private List<List<Object>> contents = FastList.newList();

    public BalanceSheetStatement(BalanceSheet balanceSheet)
    {
        this.balanceSheet = balanceSheet;
    }

    public List<List<Object>> getContents()
    {
        Map<String, Asset> assets = this.balanceSheet.getAssets();
        Map<String, Liability> liabilities = this.balanceSheet.getLiabilities();
        Map<String, Capital> capital = this.balanceSheet.getCapital();

        contents.add(FastList.newListWith("Type","Classification","Tier","Value"));
        contents.add(FastList.newListWith("Assets", "","",""));
        MapIterate.forEachKeyValue(assets, (s, asset) -> contents.add(FastList.<Object>newListWith("",asset.getClassification(), asset.getTier(),asset.getValue())));
        contents.add(FastList.newListWith("Liabilities", "","",""));
        MapIterate.forEachKeyValue(liabilities, (s, asset) -> contents.add(FastList.<Object>newListWith("", asset.getClassification(), asset.getTier(),asset.getValue())));
        contents.add(FastList.newListWith("Capital", "","","",""));
        MapIterate.forEachKeyValue(capital, (s, asset) -> contents.add(FastList.<Object>newListWith("",asset.getClassification(), asset.getTier(),asset.getValue())));
        contents.add(FastList.newListWith("TOTAL ASSETS", "","",this.balanceSheet.getTotalAssets()));
        contents.add(FastList.newListWith("TOTAL LIABILITIES", "","", this.balanceSheet.getTotalLiabilities()));
        return this.contents;
    }

    @Override
    public String name()
    {
        return "Balance Sheet";
    }
}
