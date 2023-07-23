package UI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HomePage extends JFrame {
    private JPanel HomePanel;
    private JTabbedPane MainTabbedPane;

    public HomePage() {
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


    }
}
