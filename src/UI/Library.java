package UI;

import Controllers.TransactionController;
import Models.Transaction;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.text.SimpleDateFormat;
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
        ReservationTab reservationTab = new ReservationTab();
        UserTab userTab = new UserTab();

        MainTabbedPane.addTab("Books", bookTab);
        MainTabbedPane.addTab("Borrowers", borrowerTab);
        MainTabbedPane.addTab("Transactions", transactionTab);
        MainTabbedPane.addTab("Reservations", reservationTab);
        MainTabbedPane.addTab("Reports", reportTab);
        MainTabbedPane.addTab("Admin Users", userTab);

        MainTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                bookTab.LoadBooks();
                borrowerTab.LoadBorrowers();
                transactionTab.QueryAllTransactions();
                reservationTab.LoadReservations();
            }
        });

        ShowOverdueNotification();
    }

    private void ShowOverdueNotification() {
        try{
            ArrayList<Transaction> overdueTransactions = transactionController.GetOverdueTransactions();
            if (overdueTransactions.size() > 0) {
                var today = new SimpleDateFormat("EE, dd MMM yyyy").format(new Date(System.currentTimeMillis()));
                JOptionPane.showMessageDialog(HomePanel, "There is(are) " + overdueTransactions.size() + " overdue borrowing transactions as of " + today);
            }

        } catch (Exception e){}
    }
}
