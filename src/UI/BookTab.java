package UI;

import Applications.Book.AddBookCommand;
import Applications.Book.GetAllBooksQuery;
import Applications.Book.QueryBook;
import Applications.Report.GetAvailableBooksQuery;
import Applications.Transaction.AddTransactionCommand;
import Controllers.BookController;
import Controllers.TransactionController;
import Models.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BookTab extends JPanel {
    private JPanel BookPanel;
    private JTextField titleTextField;
    private JTextField authorTextField;
    private JTextField publicationYearTextField;
    private JCheckBox isAvailableCheckBox;
    private JButton addBookButton;
    private JTable allBooksTable;
    private BookController bookController = new BookController();
    private TransactionController transactionController = new TransactionController();
    private ArrayList<Book> allBooks = new ArrayList();

    public BookTab(){
        this.add(BookPanel);
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBook();
            }
        });

        LoadBooks();

    }

    public void AddBook() {
        if (titleTextField.getText().equals("") || authorTextField.getText().equals("") || publicationYearTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(BookPanel, "Please input all the fields.");
        } else {
            try {
                AddBookCommand command = new AddBookCommand();
                command.Title = titleTextField.getText();
                command.Author = authorTextField.getText();
                command.PublicationYear = publicationYearTextField.getText();
                command.IsAvailable = isAvailableCheckBox.isSelected();
                bookController.AddBook(command);
                AddTransaction(command.Title, command.PublicationYear, command.IsAvailable);
                JOptionPane.showMessageDialog(BookPanel, "New book added.");
                titleTextField.setText("");
                authorTextField.setText("");
                publicationYearTextField.setText("");
                isAvailableCheckBox.setSelected(false);
                LoadBooks();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(BookPanel, ex);
            }
        }
    }

    public void LoadBooks() {
        try{
            GetAllBooksQuery query = new GetAllBooksQuery();
            allBooks = bookController.GetAllBooks(query);
            DefaultTableModel tableModel = new DefaultTableModel();
            if(allBooks.size() > 0){
                allBooksTable.setVisible(true);
                tableModel.addColumn("Book Id");
                tableModel.addColumn("Book Title");
                tableModel.addColumn("Author");
                tableModel.addColumn("Publication Year");
                tableModel.addColumn("Is Available");
                tableModel.addColumn("Record Created Date");
                allBooks.forEach((book) -> {
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
            JOptionPane.showMessageDialog(BookPanel, ex);
        }
    }

    public void AddTransaction(String BookTitle, String PublicationYear, Boolean IsAvailable){
        QueryBook query = new QueryBook();
        query.BookTitle= BookTitle;
        query.PublicationYear = PublicationYear;
        query.IsAvailable= IsAvailable;

        try {
            allBooks = bookController.QueryBooks(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);
        long returnDate = c.getTimeInMillis();
        AddTransactionCommand command = new AddTransactionCommand();
        command.BookId = allBooks.get(0).Id;
        command.BorrowDate = new Date(System.currentTimeMillis());
        command.BorrowerId = 0;
        command.ReturnDate = new Date(returnDate);
        command.IsReturned = false;

        try {
            transactionController.AddTransaction(command);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
