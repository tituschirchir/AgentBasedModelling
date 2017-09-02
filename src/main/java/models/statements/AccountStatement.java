package models.statements;

import java.util.List;

/**
 * Created by: tituskc
 * Created On  Thu, Aug 31, 2017 at 9:59 PM.
 */
public interface AccountStatement
{
    String name();

    List<List<Object>> getContents();
}
