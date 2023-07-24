package UI;

import Controllers.TransactionController;
import Models.Transaction;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.Date;

public class Library extends JFrame {
    private JPanel HomePanel;
    private JTabbedPane MainTabbedPane;
    private TransactionController transactionController = new TransactionController();

    public Library() {
        setContentPane(HomePanel);
        setTitle("Library Information System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        setVisible(true);

        BookTab bookTab = new BookTab();
        BorrowerTab borrowerTab = new BorrowerTab();
        TransactionTab transactionTab = new TransactionTab();
        ReportTab reportTab = new ReportTab();

        MainTabbedPane.addTab("Books", bookTab);
        MainTabbedPane.addTab("Borrowers", borrowerTab);
        MainTabbedPane.addTab("Transactions", transactionTab);
        MainTabbedPane.addTab("Reports", reportTab);

        MainTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                transactionTab.QueryAllTransactions();
            }
        });

        ShowOverdueNotification();
    }

    private void ShowOverdueNotification() {
        try{
            ArrayList<Transaction> overdueTransactions = transactionController.GetOverdueTransactions();
            if (overdueTransactions.size() > 0) {
                JOptionPane.showMessageDialog(HomePanel, "There are " + overdueTransactions.size() + " overdue borrowing transactions as of " + new Date(System.currentTimeMillis()));
            }

        } catch (Exception e){}
    }
}
