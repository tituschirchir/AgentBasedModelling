import com.gs.tablasco.TableVerifier;
import com.gs.tablasco.VerifiableTable;
import com.gs.tablasco.verify.ListVerifiableTable;
import models.accounts.BalanceSheet;
import models.balancesheet.*;
import models.institutions.Bank;
import models.institutions.Institution;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.utility.MapIterate;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import services.NetworkImpl;

import java.util.Map;

/**
 * Created by: tituskc
 * Created On  Thu, Aug 31, 2017 at 9:31 PM.
 */
public class NetworkTest
{

    @Rule
    public final TableVerifier tableVerifier = new TableVerifier()
            .withExpectedDir("src/test/resources")
            .withOutputDir("target")
            .withTolerance(1e-5)
            //.withRebase()
            ;
    @Test
    public void testName()
    {
        Institution bank = new Bank(new BalanceSheet("ABC"));
        Assert.assertEquals("ABC", bank.getName());
    }
    @Test
    public void testCapital()
    {
        Valuable cap = Capital.createCapital(Tier.TIER_1, 234.56, Classification.CAPITAL);
        Assert.assertEquals(Tier.TIER_1, cap.getTier());
        Assert.assertEquals(234.56, cap.getValue(),.001);
    }

    @Test
    public void testNetwork()
    {
        NetworkImpl network = new NetworkImpl(10, .5, 1000000, .2, .05, true, false);
        Map<String, VerifiableTable> verifiableTableMap = UnifiedMap.newMap();
        MapIterate.forEachKeyValue(network.getBankStatements(), (s,lists) -> verifiableTableMap.put(s, new ListVerifiableTable(lists)));
        this.tableVerifier.verify(verifiableTableMap);
    }
}
