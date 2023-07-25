package Controllers;

import Applications.User.CreateNewUserCommand;
import Applications.User.LoginCommand;
import Applications.User.QueryAllUsers;
import Applications.User.ResetPasswordCommand;
import Models.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
    public boolean Login(LoginCommand command) throws Exception {
        return command.Handle();
    }

    public ArrayList<User> GetAllUsers() throws SQLException {
        QueryAllUsers query = new QueryAllUsers();
        return query.Handle();
    }

    public void CreateNewUser(CreateNewUserCommand command) throws Exception {
        command.Handle();
    }

    public void ResetPassword(ResetPasswordCommand command) throws Exception {
        command.Handle();
    }
}
