package models.institutions;

/**
 * Created by: tituskc
 * Created On  Thu, Aug 31, 2017 at 9:35 PM.
 */
public class InsuranceAgency implements Institution
{
    private String name;
    public InsuranceAgency(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
}
