package Applications.Book.Commands;

import Infrastructure.ApplicationDbContext;
import Models.Book;
import Models.Transaction;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnBookCommand {
    public int BookId;
    private ApplicationDbContext _context = new ApplicationDbContext();
    public void Handle() throws SQLException {
        //return : query in Transaction Table for book id and
        // IsReturned = false. Then need to set IsReturned = true and in Book Table IsAvailable = true

    }
}
