package models.balancesheet;

import org.eclipse.collections.api.block.function.Function;

/**
 * Created by: tituskc
 * Created On  Thu, Aug 31, 2017 at 10:04 PM.
 */
public interface Valuable
{
    double getValue();
    void setValue(double value);
    Tier getTier();
    Classification getClassification();
}
