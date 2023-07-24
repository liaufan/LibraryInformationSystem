package UI;

import Applications.Borrower.AddBorrowerCommand;
import Controllers.BorrowerController;
import Models.Borrower;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BorrowerTab extends JPanel {
    private JPanel BorrowerPanel;
    private JTextField nameTextField;
    private JButton addBorrowerButton;
    private JTextField emailTextField;
    private JTextField phoneTextField;
    private JTable allBorrowersTable;

    private BorrowerController borrowerController = new BorrowerController();
    private ArrayList<Borrower> allBorrowers = new ArrayList();


    public BorrowerTab(){
        this.add(BorrowerPanel);
        addBorrowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBorrower();
            }
        });

        LoadBorrowers();
    }

    public void AddBorrower() {
        if (nameTextField.getText().equals("") || emailTextField.getText().equals("") || phoneTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(BorrowerPanel, "Please input all the fields.");
        } else {
            try {
                AddBorrowerCommand command = new AddBorrowerCommand();
                command.Name = nameTextField.getText();
                command.Email = emailTextField.getText();
                command.Phone = phoneTextField.getText();
                borrowerController.AddBorrower(command);
                JOptionPane.showMessageDialog(BorrowerPanel, "New borrower added.");
                nameTextField.setText("");
                emailTextField.setText("");
                phoneTextField.setText("");

                LoadBorrowers();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(BorrowerPanel, ex.getMessage());
            }
        }
    }

    public void LoadBorrowers() {
        try{
            allBorrowers = borrowerController.GetAllBorrowers();
            DefaultTableModel tableModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };
            if(allBorrowers.size() > 0){
                allBorrowersTable.setVisible(true);
                tableModel.addColumn("Borrower Id");
                tableModel.addColumn("Name");
                tableModel.addColumn("Email");
                tableModel.addColumn("Phone");
                tableModel.addColumn("Created Date");
                allBorrowers.forEach((borrower) -> {
                    Object[] data = {borrower.Id, borrower.Name, borrower.Email, borrower.Phone, borrower.CreatedDate};
                    tableModel.addRow(data);
                });
                allBorrowersTable.setModel(tableModel);
                allBorrowersTable.getColumnModel().getColumn(0).setPreferredWidth(100);
                allBorrowersTable.getColumnModel().getColumn(1).setPreferredWidth(200);
                allBorrowersTable.getColumnModel().getColumn(2).setPreferredWidth(120);
                allBorrowersTable.getColumnModel().getColumn(3).setPreferredWidth(80);
                allBorrowersTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            } else {
                allBorrowersTable.setVisible(false);
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(BorrowerPanel, ex.getMessage());
        }
    }
}
