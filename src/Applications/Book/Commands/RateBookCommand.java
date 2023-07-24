package Applications.Book.Commands;

import Infrastructure.ApplicationDbContext;
import Models.Book;
import Models.Rating;
import Models.Transaction;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class RateBookCommand {
    public int BookId;
    public String BorrowerName;
    public String BookName;
    public int Rating;
    public String Reviews;
    public Date CreatedDate;

    private ApplicationDbContext _context = new ApplicationDbContext();
    public void Handle() throws SQLException {
        // Write the main command code here
        ArrayList<Models.Rating> ratings = new ArrayList<>();
        Rating rating = new Rating();
        rating.BookId = this.BookId;
        rating.BorrowerName = this.BorrowerName;
        rating.BookName = this.BookName;
        rating.Reviews = this.Reviews;
        rating.Rating = this.Rating;
        rating.CreatedDate = new Date(System.currentTimeMillis());
        System.out.println("Rate book command");
        ratings.add(rating);
        _context.UpdateRating(ratings);
        _context.Dispose();
    }
}
