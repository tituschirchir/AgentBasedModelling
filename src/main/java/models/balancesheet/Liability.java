package models.balancesheet;

/**
 * Created by: tituskc
 * Created On  Thu, Aug 31, 2017 at 10:01 PM.
 */
public class Liability implements Valuable
{
    private double value;
    private Tier tier;
    private Classification classification;

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    @Override
    public Tier getTier()
    {
        return this.tier;
    }

    @Override
    public Classification getClassification()
    {
        return this.classification;
    }

    public static Liability createLiability(double value, Tier tier, Classification classification)
    {
        Liability liability = new Liability();
        liability.value = value;
        liability.tier = tier;
        liability.classification = classification;
        return liability;
    }
}
