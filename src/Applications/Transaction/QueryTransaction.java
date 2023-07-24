package Applications.Transaction;

import Infrastructure.ApplicationDbContext;
import Models.Transaction;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryTransaction {
    public Date StartDate;
    public Date EndDate;



    private ApplicationDbContext _context = new ApplicationDbContext();

    public ArrayList<Transaction> Handle() throws SQLException {
        // Write the main command code here
        ArrayList<Transaction> transactions = _context.QueryTransaction( " CreatedDate >= '" + this.StartDate +"'" + "&& CreatedDate <= '" + this.EndDate +"'");
        _context.Dispose();

        transactions.add(transaction);
        _context.AddTransaction(transactions);
        _context.Dispose();
    }
}
