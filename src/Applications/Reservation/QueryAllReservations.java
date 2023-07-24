package Applications.Reservation;

import Infrastructure.ApplicationDbContext;
import Models.Borrower;
import Models.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryAllReservations {
    private ApplicationDbContext _context = new ApplicationDbContext();
    public ArrayList<Reservation> Handle() throws SQLException {
        ArrayList<Reservation> borrowers = new ArrayList<>();
        _context.Dispose();

        return borrowers;
    }
}
