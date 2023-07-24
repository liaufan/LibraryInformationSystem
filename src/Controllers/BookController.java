package Controllers;

import Applications.Book.Commands.AddBookCommand;
import Applications.Book.Commands.BorrowBookCommand;
import Applications.Book.Commands.RateBookCommand;
import Applications.Book.Queries.QueryAllBooks;
import Applications.Book.Queries.QueryBook;
import Applications.Book.Queries.QueryBookRatings;
import Applications.Transaction.QueryTransaction;
import Models.Book;
import Models.Rating;
import Models.Transaction;

import javax.management.Query;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookController {
    public void AddBook(AddBookCommand command) throws SQLException{
        command.Handle();
    }

    public void BorrowBook(BorrowBookCommand command) throws Exception{
        command.Handle();
    }
    public void RateBook(RateBookCommand command) throws Exception{
        command.Handle();
    }

    public ArrayList<Book> GetAllBooks() throws SQLException {
        QueryAllBooks query = new QueryAllBooks();
        return query.Handle();
    }
    public ArrayList<Book> QueryBook(QueryBook query) throws SQLException {
        return query.Handle();
    }
    public ArrayList<Rating> QueryBookRating(QueryBookRatings query) throws SQLException {
        return query.Handle();
    }

}
