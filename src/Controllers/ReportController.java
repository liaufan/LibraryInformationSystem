package Controllers;

import Applications.Book.GetAllBooksQuery;
import Applications.Book.QueryBook;
import Applications.Report.GetAvailableBooksQuery;
import Models.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReportController {
    public ArrayList<Book> GetAvailableBook(GetAvailableBooksQuery query) throws SQLException {
        return query.Handle();
    }
}
