package Applications.Book.Queries;

import Infrastructure.ApplicationDbContext;
import Models.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryBook {
    private ApplicationDbContext _context = new ApplicationDbContext();
    public String BookTitle;
    public String PublicationYear;
    public Boolean IsAvailable;
    public ArrayList<Book> Handle() throws SQLException {
        ArrayList<Book> books = _context.QueryBooks("Id=-1 || IsAvailable=true");
        _context.Dispose();

        return books;
    }
}
