package Applications.Borrower;

import Infrastructure.ApplicationDbContext;
import Models.Book;
import Models.Borrower;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddBorrowerCommand {
    public String Name;
    public String Email;
    public String Phone;

    private ApplicationDbContext _context = new ApplicationDbContext();
    public void Handle() throws SQLException {
        // Write the main command code here
        ArrayList<Borrower> borrowers = new ArrayList<>();
        Borrower borrower = new Borrower();
        borrower.Name = this.Name;
        borrower.Email = this.Email;
        borrower.Phone = this.Phone;

        borrower.CreatedDate = new Date(System.currentTimeMillis());

        borrowers.add(borrower);
        _context.UpdateBorrowers(borrowers);
        _context.Dispose();
    }
}
