package Applications.Book.Queries;

import Infrastructure.ApplicationDbContext;
import Models.Book;
import Models.Rating;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryBookRatings {
    public int BookId;
    ApplicationDbContext _context = new ApplicationDbContext();

    public ArrayList<Rating> Handle() throws SQLException {
        ArrayList<Rating> ratings = _context.QueryBookRating("BookId = " + this.BookId);
        _context.Dispose();
        return ratings;
    }
}
