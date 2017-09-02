package models.statements;

import java.util.List;

/**
 * Created by: tituskc
 * Created On  Fri, Sep 01, 2017 at 3:29 PM.
 */
public class IncomeStatement implements AccountStatement
{

    public String name()
    {
        return "Profit and Loss Account";
    }

    @Override
    public List<List<Object>> getContents()
    {
        return null;
    }
}
