package Applications.User;

import Infrastructure.ApplicationDbContext;
import Models.User;

public class LoginCommand {
    public String Username;
    public String Password;

    private ApplicationDbContext _context = new ApplicationDbContext();

    public boolean Handle() throws Exception {
        var users = _context.QueryUsers("Username = '" + Username + "'");
        if(users.size() == 0){
            throw new Exception("Username not found!");
        }

        if(!users.get(0).Password.equals(this.Password)){
            throw new Exception("Invalid Password!");
        }

        return true;

    }
}
