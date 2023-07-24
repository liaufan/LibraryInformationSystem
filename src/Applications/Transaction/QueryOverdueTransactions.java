package Applications.Transaction;

import Infrastructure.ApplicationDbContext;
import Models.Transaction;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryOverdueTransactions {
    private ApplicationDbContext _context = new ApplicationDbContext();
    public ArrayList<Transaction> Handle() throws SQLException {
        ArrayList<Transaction> transactions = _context.QueryTransaction("ExpectedReturnDate < '" + new Date(System.currentTimeMillis()) + "' && IsReturned = false");
        _context.Dispose();

        return transactions;
    }
}
