package Models;

import java.sql.Date;

public class Rating {
    public int Id;
    public int BookId;
    public int BorrowerId;
    public int Rating;
    public String Reviews;
    public Date CreatedDate;
}
