package Applications.Book.Commands;

import Infrastructure.ApplicationDbContext;
import Models.Book;
import Models.Transaction;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnBookCommand {
    public int TransactionId;
    private int BookId;
    private ApplicationDbContext _context = new ApplicationDbContext();
    private Transaction transaction = new Transaction();
    public void Handle() throws Exception {
        //return : query in Transaction Table for book id and
        // IsReturned = false. Then need to set IsReturned = true and in Book Table IsAvailable = true
        ArrayList<Transaction> transactions = _context.QueryTransaction("Id = " + this.TransactionId);
        if(transactions.size() == 0){
            throw new Exception("transaction not found");
        }
        else{
            transaction = transactions.get(0);
            transaction.IsReturned = true;

            ArrayList<Book> books = _context.QueryBooks("Id = " + transaction.BookId);
            if(books.size() > 0){
                books.get(0).IsAvailable = true;
            }

        }
        ArrayList<Book> books = _context.QueryBooks("Id = " + transaction.BookId);

        for(Book book: books){
            book.IsAvailable = true;
        }
        _context.UpdateBooks(books);
        _context.UpdateTransaction(transactions);
        _context.Dispose();
    }
}
