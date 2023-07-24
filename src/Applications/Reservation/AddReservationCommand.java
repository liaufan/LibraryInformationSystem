package Applications.Reservation;

import Infrastructure.ApplicationDbContext;
import Models.Reservation;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AddReservationCommand {
    public int BookId;
    public int BorrowerId;
    public String BorrowDate;
    public String ReturnDate;

    private ApplicationDbContext _context = new ApplicationDbContext();
    public void Handle() throws Exception {
        // Write the main command code here

        var books = _context.QueryBooks("Id = " + this.BookId);
        if(books.size() == 0){
            throw new Exception("Book not found.");
        }

        if(!books.get(0).IsAvailable){
            throw new Exception("Book is not available");
        }

        var borrowers = _context.QueryBorrowers("Id = " + this.BorrowerId);
        if(borrowers.size() == 0){
            throw new Exception("Borrower not found.");
        }

        String sql = "BookId = " + this.BookId;
        sql += " and not (('" + this.BorrowDate + "' < BorrowDate && '" + this.ReturnDate + "' < BorrowDate)";
        sql += " || ('" + this.BorrowDate + "' > ReturnDate && '" + this.ReturnDate + "' > ReturnDate))";
        var existingReservations = _context.QueryReservations(sql);

        if(existingReservations.size() > 0){
            throw new Exception("<" + books.get(0).Title + "> has already been reserved from " + existingReservations.get(0).BorrowDate + " until "+ existingReservations.get(0).ReturnDate + ".");
        }

        ArrayList<Reservation> reservations = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservation.BookId = this.BookId;
        reservation.BookName = books.get(0).Title;
        reservation.BorrowerId = this.BorrowerId;
        reservation.BorrowerName = borrowers.get(0).Name;


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        reservation.BorrowDate = new Date(dateFormat.parse(this.BorrowDate).getTime());
        reservation.ReturnDate = new Date(dateFormat.parse(this.ReturnDate).getTime());
        if(reservation.ReturnDate.before(reservation.BorrowDate)){
            throw new Exception("Borrow Date must be earlier than Return Date.");
        }


        reservation.CreatedDate = new Date(System.currentTimeMillis());

        reservations.add(reservation);
        _context.UpdateReservations(reservations);
        _context.Dispose();
    }
}
