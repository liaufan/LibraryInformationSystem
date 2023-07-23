package Models;

import java.sql.Date;

public class Transaction {
    public int Id;
    public int BookId;
    public int BorrowerId;
    public Date BorrowDate;
    public Date ReturnDate;
    public Date ExpectedReturnDate;
    public Boolean IsReturned;
    public Date CreatedDate;
}
