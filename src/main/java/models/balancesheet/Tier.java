package models.balancesheet;

/**
 * Created by: tituskc
 * Created On  Thu, Aug 31, 2017 at 10:02 PM.
 */
public enum Tier
{
    TIER_1("Tier 1"), TIER_2("Tier 2");

    public String name;

    Tier(String name)
    {
        this.name = name;
    }
    @Override
    public String toString()
    {
        return this.name;
    }
}
