package UI;

import Applications.Book.Commands.AddBookCommand;
import Applications.Book.Commands.BorrowBookCommand;
import Applications.Book.Queries.QueryAllBooks;
import Applications.Book.Queries.QueryBook;
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
import java.util.ArrayList;
import java.util.Calendar;

public class BookTab extends JPanel {
    private JPanel BookPanel;
    private JTextField titleTextField;
    private JTextField authorTextField;
    private JTextField publicationYearTextField;
    private JButton addBookButton;
    private JTable allBooksTable;
    private JTextField bookIDTextField;
    private JTextField borrowerIDTextField;
    private JButton borrowBookButton;
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

        borrowBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorrowBook();
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
                command.IsAvailable = true;
                bookController.AddBook(command);
                JOptionPane.showMessageDialog(BookPanel, "New book added.");
                titleTextField.setText("");
                authorTextField.setText("");
                publicationYearTextField.setText("");
                LoadBooks();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(BookPanel, ex.getMessage());
            }
        }
    }

    public void LoadBooks() {
        try{
            allBooks = bookController.GetAllBooks();
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
            JOptionPane.showMessageDialog(BookPanel, ex.getMessage());
        }
    }

    public void BorrowBook(){
        if (bookIDTextField.getText().equals("") || borrowerIDTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(BookPanel, "Please input all the fields.");
        } else {
            try {
                int bookId = Integer.parseInt(bookIDTextField.getText());
                int borrowerId = Integer.parseInt(borrowerIDTextField.getText());
                BorrowBookCommand command = new BorrowBookCommand();
                command.BookId = bookId;
                command.BorrowerId = borrowerId;

                bookController.BorrowBook(command);
                JOptionPane.showMessageDialog(BookPanel, "Book borrowed.");
                bookIDTextField.setText("");
                borrowerIDTextField.setText("");

                LoadBooks();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(BookPanel, ex.getMessage());
            }
        }
    }


}
