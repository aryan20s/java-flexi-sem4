package movierating.db.entities;

import movierating.Main;
import movierating.db.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Booking implements WriteableDAO {
    private final int bookingID;
    private final Show show;

    public Booking(int bookingID, Show show) {
        this.bookingID = bookingID;
        this.show = show;
    }

    public int getBookingID() {
        return bookingID;
    }

    public Show getShow() {
        return show;
    }

    @Override
    public boolean insert() throws SQLException  {
        PreparedStatement ps = Database.con.prepareStatement(
            "INSERT INTO Bookings VALUES (" +
                    "?, ?, ?" +
                ")"
        );

        ps.setInt(1, bookingID);
        ps.setInt(2, this.show.getShowID());
        ps.setInt(3, Main.CURRENT_USER.getPersonID());

        return ps.executeUpdate() != 0;
    }

    public boolean delete() throws SQLException {
        int userID = Main.CURRENT_USER.getPersonID();
        int movieID = this.show.getMovie().getMovieID();
        int bookingID = this.bookingID;

        PreparedStatement ps = Database.con.prepareStatement(
                "DELETE FROM MovieReview WHERE UserID = ? AND MovieID = ?"
        );
        ps.setInt(1, userID);
        ps.setInt(2, movieID);
        ps.executeUpdate();

        PreparedStatement ps2 = Database.con.prepareStatement(
                "DELETE FROM Bookings WHERE BookingID = ?"
        );
        ps2.setInt(1, bookingID);

        return ps2.executeUpdate() > 0;
    }
}
