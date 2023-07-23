package Controllers;

import Applications.Book.GetAllBooksQuery;
import Applications.Borrower.AddBorrowerCommand;
import Models.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowerController {
    public void AddBorrower(AddBorrowerCommand command) throws SQLException {
        command.Handle();
    }

    public ArrayList<Book> GetAllBorrowers(GetAllBooksQuery query) throws SQLException {
        return query.Handle();
    }
}
