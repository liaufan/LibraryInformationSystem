package Controllers;

import Applications.Report.QueryAvailableBooks;
import Models.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReportController {
    public ArrayList<Book> GetAvailableBook(QueryAvailableBooks query) throws SQLException {
        return query.Handle();
    }
}
