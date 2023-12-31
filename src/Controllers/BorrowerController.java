package Controllers;


import Applications.Borrower.AddBorrowerCommand;
import Applications.Borrower.QueryAllBorrowers;
import Applications.Borrower.QueryBorrower;
import Models.Borrower;

import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowerController {
    public void AddBorrower(AddBorrowerCommand command) throws SQLException {
        command.Handle();
    }

    public ArrayList<Borrower> GetAllBorrowers() throws SQLException {
        QueryAllBorrowers query = new QueryAllBorrowers();
        return query.Handle();
    }
    public ArrayList<Borrower> GetBorrowers(QueryBorrower query) throws SQLException {
        return query.Handle();
    }

}
