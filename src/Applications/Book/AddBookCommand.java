package Applications.Book;

import Infrastructure.ApplicationDbContext;
import Models.Book;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddBookCommand {
    public String Title;
    public String Author;
    public String PublicationYear;
    public Boolean IsAvailable;

    private ApplicationDbContext _context = new ApplicationDbContext();
    public void Handle() throws SQLException {
        // Write the main command code here
        ArrayList<Book> books = new ArrayList<>();
        Book book = new Book();
        book.Title = this.Title;
        book.Author = this.Author;
        book.PublicationYear = this.PublicationYear;
        book.IsAvailable = this.IsAvailable;
        book.CreatedDate = new Date(System.currentTimeMillis());

        books.add(book);
        _context.UpdateBooks(books);
    }
}
