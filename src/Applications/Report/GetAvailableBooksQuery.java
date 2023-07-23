package Applications.Report;
import Infrastructure.ApplicationDbContext;
import Models.Book;
import java.sql.SQLException;
import java.util.ArrayList;


public class GetAvailableBooksQuery {
    private ApplicationDbContext _context = new ApplicationDbContext();
    public ArrayList<Book> Handle() throws SQLException {
        ArrayList<Book> books = _context.QueryBooks("isAvailable=true");
        _context.Dispose();

        return books;
    }
}
