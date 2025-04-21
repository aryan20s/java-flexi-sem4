package movierating.db.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Show {
    private final int showID;
    private final double price;
    private final Movie movie;
    private final Theatre theatre;
    private final LocalDate localDate;
    private final LocalTime localTime;

    public Show(int showID, double price, Movie movie, Theatre theatre, LocalDate localDate, LocalTime localTime) {
        this.showID = showID;
        this.price = price;
        this.movie = movie;
        this.theatre = theatre;
        this.localDate = localDate;
        this.localTime = localTime;
    }

    public int getShowID() {
        return showID;
    }

    public double getPrice() {
        return price;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public String getDateTimeString() {
        return localDate.atTime(localTime).format(DateTimeFormatter.ofPattern("HH:mm, dd-MM-yyyy"));
    }
}
