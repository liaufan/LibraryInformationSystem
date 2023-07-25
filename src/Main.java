import Applications.User.LoginCommand;
import Controllers.UserController;
import Infrastructure.ApplicationDbContext;
import UI.Library;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ApplicationDbContext _context = new ApplicationDbContext();
        _context.DatabaseSeed();

        var login = Login();

        while(!login){
            JOptionPane.showMessageDialog(null, "Invalid username or password! You must login to access the Library Information System.");
            login = Login();
        }

        new Library();
    }

    private static boolean Login() {
        UserController userController = new UserController();
        JTextField usernameField = new JTextField(5);
        JPasswordField passwordField = new JPasswordField(5);
        Object[] options = { "Login"};

        JComponent[] dialog = new JComponent[]{
                new JLabel("Username:"),
                usernameField,
                new JLabel("Password:"),
                passwordField,
        };
        var login = JOptionPane.showOptionDialog(null ,dialog , "Library Information System | Login", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

        if(login != 0){
            System.exit(0);
        }

        LoginCommand command = new LoginCommand();
        command.Username = usernameField.getText();
        command.Password = passwordField.getText();
        try{
            return userController.Login(command);
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }

    }
}