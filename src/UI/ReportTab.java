package UI;

import Applications.Report.QueryAvailableBooks;
import Controllers.ReportController;
import Models.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReportTab extends JPanel {
    private JPanel ReportPanel;
    private JButton viewBooksBtn;
    private JTable allBooksTable;
    private JButton viewAllTransactionButton;
    private JButton button1;
    private ArrayList<Book> allAvailableBooks = new ArrayList<>();
    private ReportController reportController = new ReportController();

    public ReportTab(){
        this.add(ReportPanel);
        viewBooksBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QueryAvailableBooks();
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

    }


}
