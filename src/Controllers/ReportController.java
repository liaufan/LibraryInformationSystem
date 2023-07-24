package Controllers;

import Applications.Report.QueryAvailableBooks;
import Models.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReportController {
    public ArrayList<Book> GetAvailableBooks(QueryAvailableBooks query) throws SQLException {
        return query.Handle();
    }
}
