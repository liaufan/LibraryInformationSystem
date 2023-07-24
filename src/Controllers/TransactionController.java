package Controllers;

import Applications.Transaction.AddTransactionCommand;
import Applications.Transaction.QueryAllTransactions;
import Models.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionController {
    public void AddTransaction(AddTransactionCommand command) throws SQLException {
        command.Handle();
    }
    public ArrayList<Transaction> GetAllTransaction(QueryAllTransactions query) throws SQLException {
        return query.Handle();
    }
}
