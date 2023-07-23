package Applications.Transaction;

import Infrastructure.ApplicationDbContext;
import Models.Book;
import Models.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryAllTransactions {
    private ApplicationDbContext _context = new ApplicationDbContext();
    public ArrayList<Transaction> Handle() throws SQLException {
        ArrayList<Transaction> transactions = _context.QueryTransaction("true");
        _context.Dispose();

        return transactions;
    }
}
