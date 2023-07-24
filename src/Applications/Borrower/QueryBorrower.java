package Applications.Borrower;

import Infrastructure.ApplicationDbContext;
import Models.Book;
import Models.Borrower;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryBorrower {
    private ApplicationDbContext _context = new ApplicationDbContext();
    public int BorrowerId;
    public ArrayList<Borrower> Handle() throws SQLException {
        ArrayList<Borrower> borrowers = _context.QueryBorrowers("Id = " + this.BorrowerId);
        System.out.println("Query Borrower");
        _context.Dispose();

        return borrowers;
    }
}
