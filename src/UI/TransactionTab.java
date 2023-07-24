package UI;

import Applications.Book.Commands.RateBookCommand;
import Applications.Book.Commands.ReturnBookCommand;
import Applications.Book.Queries.QueryBook;
import Applications.Borrower.QueryBorrower;
import Applications.Transaction.QueryAllTransactions;
import Applications.Transaction.QueryTransaction;
import Controllers.BookController;
import Controllers.BorrowerController;
import Controllers.TransactionController;
import Models.Book;
import Models.Borrower;
import Models.Rating;
import Models.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionTab extends JPanel {
    private JPanel TransactionPanel;
    private TransactionController transactionController = new TransactionController();
    private ArrayList<Transaction> allTransactions = new ArrayList<>();
    private JTable transactionTable;
    private JButton button1;
    private BookController bookController = new BookController();
    private BorrowerController borrowerController = new BorrowerController();
    private Book book = new Book();
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Borrower> borrowers = new ArrayList<>();
    private Borrower borrower = new Borrower();
    private ArrayList<Transaction> transactions = new ArrayList<>();


    public TransactionTab() {
        this.add(TransactionPanel);
        transactionTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                var res = JOptionPane.showConfirmDialog(TransactionPanel, "Return Book?");
                var isReturned = transactionTable.getValueAt(transactionTable.getSelectedRow(), 5).toString();
                if(res==0 && isReturned=="false"){
                    var transactionID = transactionTable.getValueAt(transactionTable.getSelectedRow(), 0).toString();
                    ReturnBook(Integer.valueOf(transactionID));
                    QueryAllTransactions();
                    QueryTransaction queryTrans = new QueryTransaction();
                    queryTrans.TransactionId = Integer.valueOf(transactionID);

                    try {
                        transactions = transactionController.GetTransaction(queryTrans);
                        var ratingScore = JOptionPane.showInputDialog(TransactionPanel, "Please give this book a rating (0-5)");
                        var ratingReview = JOptionPane.showInputDialog(TransactionPanel, "Please let us know how much you like this book");
                        rateBook(transactions.get(0), ratingScore, ratingReview);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(TransactionPanel, "Book is already returned");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        QueryAllTransactions();
    }

    public void QueryAllTransactions() {
        try{
            allTransactions = transactionController.GetAllTransactions();
            DefaultTableModel tableModel = new DefaultTableModel();
            if(allTransactions.size() > 0){
                transactionTable.setVisible(true);
                tableModel.addColumn("Id");
                tableModel.addColumn("Book ID");
                tableModel.addColumn("Borrower ID");
                tableModel.addColumn("Borrowed Date");
                tableModel.addColumn("Returned Date");
                tableModel.addColumn("Is Returned");
                tableModel.addColumn("Record Created Date");
                allTransactions.forEach((transaction) -> {
                    Object[] data = {transaction.Id, transaction.BookId, transaction.BorrowerId, transaction.BorrowDate, transaction.ReturnDate,transaction.IsReturned ,transaction.CreatedDate};
                    tableModel.addRow(data);
                });
                transactionTable.setModel(tableModel);
                transactionTable.getColumnModel().getColumn(0).setPreferredWidth(60);
                transactionTable.getColumnModel().getColumn(1).setPreferredWidth(60);
                transactionTable.getColumnModel().getColumn(2).setPreferredWidth(80);
                transactionTable.getColumnModel().getColumn(3).setPreferredWidth(100);
                transactionTable.getColumnModel().getColumn(4).setPreferredWidth(100);
                transactionTable.getColumnModel().getColumn(5).setPreferredWidth(100);
                transactionTable.getColumnModel().getColumn(6).setPreferredWidth(100);
            } else {
                transactionTable.setVisible(false);
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(TransactionPanel, ex.getMessage());
        }
    }
    public void ReturnBook(int TransactionId){
        ReturnBookCommand command = new ReturnBookCommand();
        command.TransactionId = TransactionId;
        try {
            transactionController.ReturnBook(command);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void rateBook(Transaction transaction, String score, String review){
        QueryBook query = new QueryBook();
        query.BookId = transaction.BookId;

        try {
            books = bookController.QueryBook(query);
            book = books.get(0);
            QueryBorrower query2 = new QueryBorrower();
            query2.BorrowerId = transaction.BorrowerId;
            try{
                borrowers = borrowerController.GetBorrowers(query2);
                borrower = borrowers.get(0);

                RateBookCommand command = new RateBookCommand();
                command.BookId = book.Id;
                command.BookName = book.Title;
                command.BorrowerName = borrower.Name;
                if(review!=null){
                    command.Reviews = review;
                }
                if(score!=null){
                    command.Rating =  Integer.valueOf(score);
                }
                command.CreatedDate = new Date(System.currentTimeMillis());

                bookController.RateBook(command);
                JOptionPane.showMessageDialog(TransactionPanel, "New rating added.");
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }







    }

}
