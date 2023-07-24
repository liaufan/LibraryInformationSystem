package Models;

import java.sql.Date;

public class Reservation {
    public int Id;
    public int BookId;
    public String BookName;
    public int BorrowerId;
    public String BorrowerName;
    public Date BorrowDate;
    public Date ReturnDate;
    public Date CreatedDate;
}
