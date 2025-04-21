package movierating.db.entities;

public class Theatre {
    private final String theatreName, theatreAddress;
    private final int theatreID, theatreSeats;

    public Theatre(String theatreName, String theatreAddress, int theatreID, int theatreSeats) {
        this.theatreName = theatreName;
        this.theatreAddress = theatreAddress;
        this.theatreID = theatreID;
        this.theatreSeats = theatreSeats;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public String getTheatreAddress() {
        return theatreAddress;
    }

    public int getTheatreID() {
        return theatreID;
    }

    public int getTheatreSeats() {
        return theatreSeats;
    }
}
