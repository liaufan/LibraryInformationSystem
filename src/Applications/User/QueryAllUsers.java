package Applications.User;

import Infrastructure.ApplicationDbContext;
import Models.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryAllUsers {
    private ApplicationDbContext _context = new ApplicationDbContext();
    public ArrayList<User> Handle() throws SQLException {
        var users = _context.QueryUsers("true");
        _context.Dispose();

        return users;
    }
}
