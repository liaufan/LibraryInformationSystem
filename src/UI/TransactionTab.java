package UI;

import Applications.Book.Commands.ReturnBookCommand;
import Applications.Transaction.QueryAllTransactions;
import Controllers.TransactionController;
import Models.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class TransactionTab extends JPanel {
    private JPanel TransactionPanel;
    private TransactionController transactionController = new TransactionController();
    private ArrayList<Transaction> allTransactions = new ArrayList<>();
    private JTable transactionTable;
    private JButton button1;

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
}
