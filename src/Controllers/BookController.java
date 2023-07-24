package Controllers;

import Applications.Book.AddBookCommand;
import Applications.Book.GetAllBooksQuery;
import Applications.Book.QueryBook;
import Applications.Book.ReturnBookCommand;
import Applications.Report.GetAvailableBooksQuery;
import Models.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookController {
    public void AddBook(AddBookCommand command) throws SQLException{
        command.Handle();
    }
    public ArrayList<Book> GetAllBooks(GetAllBooksQuery query) throws SQLException {
        return query.Handle();
    }
    public ArrayList<Book> QueryBooks(QueryBook query) throws SQLException {
        return query.Handle();
    }

}
