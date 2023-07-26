package UI;

import Applications.Book.Commands.AddBookCommand;
import Applications.User.CreateNewUserCommand;
import Applications.User.ResetPasswordCommand;
import Controllers.BookController;
import Controllers.TransactionController;
import Controllers.UserController;
import Models.Book;
import Models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class UserTab extends JPanel {
    private JPanel UserPanel;
    private JTable allUsersTable;
    private JTextField usernameField;
    private JTextField nameField;
    private JTextField phoneNumberField;
    private JButton addUserButton;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private UserController userController = new UserController();
    private ArrayList<User> allUsers = new ArrayList();


    public UserTab(){
        this.add(UserPanel);

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateNewUser();
            }
        });

        allUsersTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    var userId = allUsersTable.getValueAt(allUsersTable.getSelectedRow(), 0).toString();
                    var username = allUsersTable.getValueAt(allUsersTable.getSelectedRow(), 1).toString();

                    ResetPassword(Integer.parseInt(userId), username);

                }catch(Exception ex){
                    JOptionPane.showMessageDialog(UserPanel, ex.getMessage());
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

        LoadUsers();

    }

    public void LoadUsers() {

        try{
            allUsers = userController.GetAllUsers();
            DefaultTableModel tableModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };
            if(allUsers.size() > 0){
                allUsersTable.setVisible(true);
                tableModel.addColumn("User Id");
                tableModel.addColumn("Username");
                tableModel.addColumn("Name");
                tableModel.addColumn("Phone Number");
                tableModel.addColumn("Created Date");

                allUsers.forEach((user) -> {
                    Object[] data = {user.Id, user.Username, user.Name, user.PhoneNumber, user.CreatedDate};
                    tableModel.addRow(data);
                });
                allUsersTable.setModel(tableModel);
                allUsersTable.getColumnModel().getColumn(0).setPreferredWidth(50);
                allUsersTable.getColumnModel().getColumn(1).setPreferredWidth(100);
                allUsersTable.getColumnModel().getColumn(2).setPreferredWidth(100);
                allUsersTable.getColumnModel().getColumn(3).setPreferredWidth(100);
                allUsersTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            } else {
                allUsersTable.setVisible(false);
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(UserPanel, ex.getMessage());
        }
    }

    public void CreateNewUser() {

        if (usernameField.getText().equals("") || passwordField.getText().equals("") || repeatPasswordField.getText().equals("") || nameField.getText().equals("") || phoneNumberField.getText().equals("")) {
            JOptionPane.showMessageDialog(UserPanel, "Please input all the fields.");
        } else if ( !passwordField.getText().equals(repeatPasswordField.getText())) {
            JOptionPane.showMessageDialog(UserPanel, "Repeat password do not match.");
        } else {
            try {
                CreateNewUserCommand command = new CreateNewUserCommand();
                command.Username = usernameField.getText();
                command.Password = passwordField.getText();
                command.Name = nameField.getText();
                command.PhoneNumber = phoneNumberField.getText();
                userController.CreateNewUser(command);
                JOptionPane.showMessageDialog(UserPanel, "New user created.");
                usernameField.setText("");
                passwordField.setText("");
                repeatPasswordField.setText("");
                nameField.setText("");
                phoneNumberField.setText("");
                LoadUsers();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(UserPanel, ex.getMessage());
            }
        }
    }

    public void ResetPassword(int userId, String username) {
        try {
            JPasswordField passwordField = new JPasswordField(5);
            JPasswordField repeatPasswordField = new JPasswordField(5);
            Object[] options = {"Reset Password", "Cancel"};

            JComponent[] dialog = new JComponent[]{
                    new JLabel("Reset Password for " + username),
                    new JLabel("Password:"),
                    passwordField,
                    new JLabel("Repeat Password:"),
                    repeatPasswordField,
            };
            var resetDialog = JOptionPane.showOptionDialog(null, dialog, "Reset Password", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

            if (resetDialog == 0) {
                if (passwordField.getText().isEmpty() || repeatPasswordField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(UserPanel, "Please input all the fields.");
                } else if (!passwordField.getText().equals(repeatPasswordField.getText())) {
                    JOptionPane.showMessageDialog(UserPanel, "Repeat password do not match.");
                } else {
                    ResetPasswordCommand command = new ResetPasswordCommand();
                    command.UserId = userId;
                    command.Password = passwordField.getText();
                    userController.ResetPassword(command);
                    JOptionPane.showMessageDialog(UserPanel, "Password has been reset.");
                }


            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(UserPanel, ex.getMessage());
        }
    }

}
