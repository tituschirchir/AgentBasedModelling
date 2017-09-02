package models.balancesheet;

/**
 * Created by: tituskc
 * Created On  Thu, Aug 31, 2017 at 10:02 PM.
 */
public class Capital implements Valuable
{
    private double value;
    private Classification classification;
    private Tier tier;

    public static Capital createCapital(Tier tier, double value, Classification classification)
    {
        Capital cap = new Capital();
        cap.setTier(tier);
        cap.setValue(value);
        cap.classification = classification;
        return cap;
    }
    public double getValue()
    {
        return this.value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    public void setTier(Tier tier)
    {
        this.tier = tier;
    }
    public Tier getTier()
    {
        return this.tier;
    }

    @Override
    public Classification getClassification()
    {
        return this.classification;
    }
}
