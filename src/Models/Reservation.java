package Models;

import java.sql.Date;

public class Reservation {
    public int Id;
    public int BookId;
    public int BorrowerId;
    public Date BorrowDate;
    public Date ReturnDate;
    public Date CreatedDate;
}
