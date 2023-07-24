package Controllers;

import Applications.Reservation.AddReservationCommand;
import Applications.Reservation.QueryAllReservations;
import Models.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationController {
    public void AddReservation(AddReservationCommand command) throws Exception {
        command.Handle();
    }

    public ArrayList<Reservation> GetAllReservations() throws SQLException {
        QueryAllReservations query = new QueryAllReservations();
        return query.Handle();
    }
}
