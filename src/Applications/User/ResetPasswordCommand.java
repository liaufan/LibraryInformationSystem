package Applications.User;

import Infrastructure.ApplicationDbContext;
import Models.User;

import java.sql.Date;
import java.util.ArrayList;

public class ResetPasswordCommand {
    public int UserId;
    public String Password;

    private ApplicationDbContext _context = new ApplicationDbContext();
    public void Handle() throws Exception {
        if(Password.isEmpty()){
            throw new Exception("Password cannot be empty!");
        }

        ArrayList<User> users = _context.QueryUsers("Id = " + UserId);
        if(users.get(0) == null){
            throw new Exception("User not found");
        }

        users.get(0).Password = this.Password;
        _context.UpdateUsers(users);
        _context.Dispose();
    }
}
