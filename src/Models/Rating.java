package Models;

import java.sql.Date;

public class Rating {
    public int Id;
    public int BookId;
    public String BorrowerName;
    public String BookName;
    public int Rating;
    public String Reviews;
    public Date CreatedDate;
}
