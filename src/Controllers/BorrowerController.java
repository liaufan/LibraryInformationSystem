package Controllers;

import Applications.Book.AddBookCommand;
import Applications.Book.GetAllBooksQuery;
import Models.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowerController {
    public void AddBorrower(AddBookCommand command) throws SQLException {
        command.Handle();
    }

    public ArrayList<Book> GetAllBorrowers(GetAllBooksQuery query) throws SQLException {
        return query.Handle();
    }
}
