package Applications.Reservation;

import Infrastructure.ApplicationDbContext;
import Models.Borrower;
import Models.Reservation;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AddReservationCommand {
    public int BookId;
    public int BorrowerId;
    public String BorrowDate;
    public String ReturnDate;

    private ApplicationDbContext _context = new ApplicationDbContext();
    public void Handle() throws SQLException {
        // Write the main command code here
        ArrayList<Reservation> reservations = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservation.BookId = this.BookId;
        reservation.BorrowerId = this.BorrowerId;
//        reservation.BorrowDate = Date.parse(this.BorrowDate);
//        reservation.ReturnDate = this.ReturnDate;

        reservation.CreatedDate = new Date(System.currentTimeMillis());

        reservations.add(reservation);
       // _context.UpdateBorrowers(reservations);
        _context.Dispose();
    }
}
