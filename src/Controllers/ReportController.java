package Controllers;

import Applications.Report.QueryAvailableBooks;
import Applications.Report.QueryBorrowedBooks;
import Models.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReportController {
    public ArrayList<Book> GetAvailableBooks() throws SQLException {
        QueryAvailableBooks query = new QueryAvailableBooks();
        return query.Handle();
    }
    public ArrayList<Book> GetBorrowedBook() throws SQLException {
        QueryBorrowedBooks query = new QueryBorrowedBooks();
        return query.Handle();
    }
}
