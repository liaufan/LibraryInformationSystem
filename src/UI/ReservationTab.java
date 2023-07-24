package UI;

import Applications.Borrower.AddBorrowerCommand;
import Applications.Reservation.AddReservationCommand;
import Controllers.BorrowerController;
import Controllers.ReservationController;
import Models.Borrower;
import Models.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReservationTab extends JPanel {
    private JPanel ReservationPanel;
    private JTable allReservationsTable;
    private JTextField bookIDTextField;
    private JTextField borrowerIDTextField;
    private JTextField borrowingDateTextField;
    private JTextField returningDateTextField;
    private JButton reserveBookButton;

    private ReservationController reservationController = new ReservationController();
    private ArrayList<Reservation> allReservations = new ArrayList();

    public ReservationTab() {
        this.add(ReservationPanel);

        reserveBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReserveBook();
            }
        });

        LoadReservations();
    }


    public void ReserveBook() {
        if (bookIDTextField.getText().equals("") || borrowerIDTextField.getText().equals("") || borrowingDateTextField.getText().equals("") || returningDateTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(ReservationPanel, "Please input all the fields.");
        } else {
            try {
                AddReservationCommand command = new AddReservationCommand();
                command.BookId = Integer.parseInt(bookIDTextField.getText());
                command.BorrowerId = Integer.parseInt(borrowerIDTextField.getText());
                command.BorrowDate = borrowingDateTextField.getText();
                command.ReturnDate = returningDateTextField.getText();
                reservationController.AddReservation(command);
                JOptionPane.showMessageDialog(ReservationPanel, "Reservation has been made.");
                bookIDTextField.setText("");
                borrowerIDTextField.setText("");
                borrowingDateTextField.setText("");
                returningDateTextField.setText("");

                LoadReservations();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ReservationPanel, ex.getMessage());
            }
        }
    }

    public void LoadReservations() {
        try{
            allReservations = reservationController.GetAllReservations();
            DefaultTableModel tableModel = new DefaultTableModel();
            if(allReservations.size() > 0){
                allReservationsTable.setVisible(true);
                tableModel.addColumn("Book Name");
                tableModel.addColumn("Borrower Name");
                tableModel.addColumn("Borrowing Date");
                tableModel.addColumn("Returning Date");
                tableModel.addColumn("Created Date");
                allReservations.forEach((reservation) -> {
                    Object[] data = {reservation.BookName, reservation.BorrowerName, reservation.BorrowDate, reservation.ReturnDate, reservation.CreatedDate};
                    tableModel.addRow(data);
                });
                allReservationsTable.setModel(tableModel);
                allReservationsTable.getColumnModel().getColumn(0).setPreferredWidth(220);
                allReservationsTable.getColumnModel().getColumn(1).setPreferredWidth(90);
                allReservationsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
                allReservationsTable.getColumnModel().getColumn(3).setPreferredWidth(100);
                allReservationsTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            } else {
                allReservationsTable.setVisible(false);
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(ReservationPanel, ex.getMessage());
        }
    }
}
