package Applications.Transaction;

import Infrastructure.ApplicationDbContext;
import Models.Transaction;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryTransaction {
    public int BookId;
    public int BorrowerId;
    public Date BorrowDate;
    public Date ReturnDate;
    public Boolean IsReturned;

    private ApplicationDbContext _context = new ApplicationDbContext();

    public void Handle() throws SQLException {
        // Write the main command code here
        ArrayList<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.BookId = this.BookId;
        transaction.BorrowerId = this.BorrowerId;
        transaction.BorrowDate = this.BorrowDate;
        transaction.ReturnDate = this.ReturnDate;
        transaction.IsReturned = this.IsReturned;
        transaction.CreatedDate = new Date(System.currentTimeMillis());

        transactions.add(transaction);
        _context.AddTransaction(transactions);
    }
}
