package Controllers;

import Applications.Transaction.AddTransactionCommand;
import Applications.Transaction.QueryAllTransactions;
import Applications.Transaction.QueryOverdueTransactions;
import Models.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionController {
    public void AddTransaction(AddTransactionCommand command) throws SQLException {
        command.Handle();
    }
    public ArrayList<Transaction> GetAllTransactions() throws SQLException {
        QueryAllTransactions query = new QueryAllTransactions();
        return query.Handle();
    }
    public ArrayList<Transaction> GetOverdueTransactions() throws SQLException {
        QueryOverdueTransactions query = new QueryOverdueTransactions();
        return query.Handle();
    }
    public ArrayList<Transaction> GetTransaction(QueryTransaction query) throws SQLException {
        return query.Handle();
    }
    public void ReturnBook(ReturnBookCommand command) throws Exception{
        command.Handle();
    }
}
