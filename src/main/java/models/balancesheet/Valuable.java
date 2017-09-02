package models.balancesheet;

import org.eclipse.collections.api.block.function.Function;

/**
 * Created by: tituskc
 * Created On  Thu, Aug 31, 2017 at 10:04 PM.
 */
public interface Valuable
{
    Function<String, Tier> TIER_FUNCTION = tier -> Tier.TIER_1.name.equals(tier) ? Tier.TIER_1 : Tier.TIER_2;
    double getValue();
    void setValue(double value);
    Tier getTier();
    Classification getClassification();
}
