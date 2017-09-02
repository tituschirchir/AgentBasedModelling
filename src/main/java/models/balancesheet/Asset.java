package models.balancesheet;

/**
 * Created by: tituskc
 * Created On  Thu, Aug 31, 2017 at 10:01 PM.
 */
public class Asset implements Valuable
{
    private double value;
    private Tier tier;
    private Classification classification;

    public double getValue()
    {
        return this.value;
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

    public static Asset createAsset(double value, Tier tier, Classification classification)
    {
        Asset asset = new Asset();
        asset.value = value;
        asset.tier = tier;
        asset.classification = classification;
        return asset;
    }

}
