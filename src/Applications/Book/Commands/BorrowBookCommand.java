package Applications.Book.Commands;

import Infrastructure.ApplicationDbContext;
import Models.Book;
import Models.Transaction;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class BorrowBookCommand {
    public int BookId;
    public int BorrowerId;
    private ApplicationDbContext _context = new ApplicationDbContext();
    public void Handle() throws Exception {
        ArrayList<Book> books = _context.QueryBooks("Id = " + this.BookId);
        if(books.size() == 0){
            throw new Exception("Book not found.");
        }

        Book book = books.get(0);
        if(!book.IsAvailable){
            throw new Exception("Book is not available.");
        }
        book.IsAvailable = false;
        books.add(book);
        _context.UpdateBooks(books);

        ArrayList<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.BookId = this.BookId;
        transaction.BorrowerId = this.BorrowerId;
        transaction.BorrowDate = new Date(System.currentTimeMillis());
        transaction.ReturnDate = new Date(1,0,1);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 14);
        transaction.ExpectedReturnDate =  new Date( c.getTime().getTime());

        transaction.IsReturned = false;
        transaction.CreatedDate = new Date(System.currentTimeMillis());

        transactions.add(transaction);
        _context.UpdateTransaction(transactions);

        _context.Dispose();
        //return : query in Transaction Table for book id and
        // IsReturned = false. Then need to set IsReturned = true and in Book Table IsAvailable = true

    }
}
