package UI;

import Applications.Transaction.QueryTransaction;
import Controllers.ReportController;
import Controllers.TransactionController;
import Models.Book;
import Models.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class ReportTab extends JPanel {
    private JPanel ReportPanel;
    private JButton viewBooksBtn;
    private JTable allBooksTable;
    private JButton viewTransactionRange;
    private JButton borrowedBookBtn;
    private ArrayList<Book> allAvailableBooks = new ArrayList<>();
    private ReportController reportController = new ReportController();
    private ArrayList<Transaction> allTransactionsRange = new ArrayList<>();
    private TransactionController transactionController = new TransactionController();

    public ReportTab(){
        this.add(ReportPanel);
        viewBooksBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QueryAvailableBooks();
            }
        });
        viewTransactionRange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratesTransactionHistory();
            }
        });
        borrowedBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryBorrowedBooks();
            }
        });
    }

    public void QueryAvailableBooks(){
        try{
            allAvailableBooks = reportController.GetAvailableBooks();
            DefaultTableModel tableModel = new DefaultTableModel();
            if(allAvailableBooks.size() > 0){
                allBooksTable.setVisible(true);
                tableModel.addColumn("Book Id");
                tableModel.addColumn("Book Title");
                tableModel.addColumn("Author");
                tableModel.addColumn("Publication Year");
                tableModel.addColumn("Is Available");
                tableModel.addColumn("Record Created Date");
                allAvailableBooks.forEach((book) -> {
                    Object[] data = {book.Id, book.Title, book.Author, book.PublicationYear, book.IsAvailable, book.CreatedDate};
                    tableModel.addRow(data);
                });
                allBooksTable.setModel(tableModel);
                allBooksTable.getColumnModel().getColumn(0).setPreferredWidth(50);
                allBooksTable.getColumnModel().getColumn(1).setPreferredWidth(220);
                allBooksTable.getColumnModel().getColumn(2).setPreferredWidth(100);
                allBooksTable.getColumnModel().getColumn(3).setPreferredWidth(80);
                allBooksTable.getColumnModel().getColumn(4).setPreferredWidth(50);
                allBooksTable.getColumnModel().getColumn(5).setPreferredWidth(100);
            } else {
                allBooksTable.setVisible(false);
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(ReportPanel, ex.getMessage());
        }
    }

    public void GeneratesTransactionHistory(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 7);
        QueryTransaction query = new QueryTransaction();
        query.EndDate = new Date( c.getTime().getTime());
        query.StartDate = new Date(System.currentTimeMillis());

        try{
            allTransactionsRange = transactionController.GetTransaction(query);
            DefaultTableModel tableModel = new DefaultTableModel();
            if(allTransactionsRange.size() > 0){
                allBooksTable.setVisible(true);
                tableModel.addColumn("Id");
                tableModel.addColumn("Book ID");
                tableModel.addColumn("Borrower ID");
                tableModel.addColumn("Borrowed Date");
                tableModel.addColumn("Returned Date");
                tableModel.addColumn("Is Returned");
                tableModel.addColumn("Record Created Date");
                allTransactionsRange.forEach((transaction) -> {
                    Object[] data = {transaction.Id, transaction.BookId, transaction.BorrowerId, transaction.BorrowDate, transaction.ReturnDate,transaction.IsReturned ,transaction.CreatedDate};
                    tableModel.addRow(data);
                });
                allBooksTable.setModel(tableModel);
                allBooksTable.getColumnModel().getColumn(0).setPreferredWidth(50);
                allBooksTable.getColumnModel().getColumn(1).setPreferredWidth(220);
                allBooksTable.getColumnModel().getColumn(2).setPreferredWidth(100);
                allBooksTable.getColumnModel().getColumn(3).setPreferredWidth(80);
                allBooksTable.getColumnModel().getColumn(4).setPreferredWidth(50);
                allBooksTable.getColumnModel().getColumn(5).setPreferredWidth(100);
                allBooksTable.getColumnModel().getColumn(6).setPreferredWidth(100);
            } else {
                allBooksTable.setVisible(false);
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(ReportPanel, ex);
        }
    }

    public void queryBorrowedBooks(){
        try{
            allAvailableBooks = reportController.GetBorrowedBook();
            DefaultTableModel tableModel = new DefaultTableModel();
            if(allAvailableBooks.size() > 0){
                allBooksTable.setVisible(true);
                tableModel.addColumn("Book Id");
                tableModel.addColumn("Book Title");
                tableModel.addColumn("Author");
                tableModel.addColumn("Publication Year");
                tableModel.addColumn("Is Available");
                tableModel.addColumn("Record Created Date");
                allAvailableBooks.forEach((book) -> {
                    Object[] data = {book.Id, book.Title, book.Author, book.PublicationYear, book.IsAvailable, book.CreatedDate};
                    tableModel.addRow(data);
                });
                allBooksTable.setModel(tableModel);
                allBooksTable.getColumnModel().getColumn(0).setPreferredWidth(50);
                allBooksTable.getColumnModel().getColumn(1).setPreferredWidth(220);
                allBooksTable.getColumnModel().getColumn(2).setPreferredWidth(100);
                allBooksTable.getColumnModel().getColumn(3).setPreferredWidth(80);
                allBooksTable.getColumnModel().getColumn(4).setPreferredWidth(50);
                allBooksTable.getColumnModel().getColumn(5).setPreferredWidth(100);
            } else {
                allBooksTable.setVisible(false);
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(ReportPanel, ex);
        }
    }


}
