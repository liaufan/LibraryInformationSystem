package Applications.Borrower;

import Infrastructure.ApplicationDbContext;
import Models.Book;
import Models.Borrower;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryAllBorrowers {
    private ApplicationDbContext _context = new ApplicationDbContext();
    public ArrayList<Borrower> Handle() throws SQLException {
        ArrayList<Borrower> borrowers = _context.QueryBorrowers("true");
        _context.Dispose();

        return borrowers;
    }
}
