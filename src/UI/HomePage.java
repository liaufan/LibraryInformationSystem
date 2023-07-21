package UI;

import javax.swing.*;

public class HomePage extends JFrame {
    private JPanel HomePanel;
    private JTabbedPane MainTabbedPane;

    public HomePage() {
        setContentPane(HomePanel);
        setTitle("Library Information System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        setVisible(true);

        JPanel BookTab = new BookTab();
        JPanel BorrowerTab = new BorrowerTab();
        JPanel TransactionTab = new TransactionTab();
        JPanel ReportTab = new ReportTab();

        MainTabbedPane.addTab("Books", BookTab);
        MainTabbedPane.addTab("Borrowers", BorrowerTab);
        MainTabbedPane.addTab("Transactions", TransactionTab);
        MainTabbedPane.addTab("Reports", ReportTab);


    }
}
