package Applications.User;

import Infrastructure.ApplicationDbContext;
import Models.User;

import java.sql.Date;
import java.util.ArrayList;

public class CreateNewUserCommand {
    public String Username;
    public String Password;
    public String Name;
    public String PhoneNumber;

    private ApplicationDbContext _context = new ApplicationDbContext();
    public void Handle() throws Exception {
        if(Username.isEmpty() || Password.isEmpty()){
            throw new Exception("Username and Password cannot be empty!");
        }

        ArrayList<User> users = new ArrayList<>();
        User newUser = new User();
        newUser.Username = this.Username;
        newUser.Password = this.Password;
        newUser.Name = this.Name;
        newUser.PhoneNumber = this.PhoneNumber;
        newUser.CreatedDate = new Date(System.currentTimeMillis());

        _context.UpdateUsers(users);
        _context.Dispose();
    }
}
