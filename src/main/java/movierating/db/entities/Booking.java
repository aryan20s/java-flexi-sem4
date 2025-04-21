package movierating.db.entities;

public class Booking {
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
}
