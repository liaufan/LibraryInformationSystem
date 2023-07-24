package Controllers;

import Applications.Book.Commands.AddBookCommand;
import Applications.Book.Commands.BorrowBookCommand;
import Applications.Book.Queries.QueryAllBooks;
import Applications.Book.Queries.QueryBook;
import Models.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookController {
    public void AddBook(AddBookCommand command) throws SQLException{
        command.Handle();
    }

    public void BorrowBook(BorrowBookCommand command) throws Exception{
        command.Handle();
    }
    public ArrayList<Book> GetAllBooks() throws SQLException {
        QueryAllBooks query = new QueryAllBooks();
        return query.Handle();
    }
}
