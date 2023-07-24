package UI;

import Applications.Transaction.QueryAllTransactions;
import Controllers.TransactionController;
import Models.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TransactionTab extends JPanel {
    private JPanel TransactionPanel;
    private TransactionController transactionController = new TransactionController();
    private ArrayList<Transaction> allTransactions = new ArrayList<>();
    private JTable transactionTable;
    private JButton button1;

    public TransactionTab() {
        this.add(TransactionPanel);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        QueryAllTransactions();
    }

    public void QueryAllTransactions() {
        QueryAllTransactions query = new QueryAllTransactions();
        try{
            allTransactions = transactionController.GetAllTransaction(query);
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
                transactionTable.getColumnModel().getColumn(0).setPreferredWidth(50);
                transactionTable.getColumnModel().getColumn(1).setPreferredWidth(220);
                transactionTable.getColumnModel().getColumn(2).setPreferredWidth(100);
                transactionTable.getColumnModel().getColumn(3).setPreferredWidth(80);
                transactionTable.getColumnModel().getColumn(4).setPreferredWidth(50);
                transactionTable.getColumnModel().getColumn(5).setPreferredWidth(100);
                transactionTable.getColumnModel().getColumn(6).setPreferredWidth(100);
            } else {
                transactionTable.setVisible(false);
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(TransactionPanel, ex);
        }
    }
}
