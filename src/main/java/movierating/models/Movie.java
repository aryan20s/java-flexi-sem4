package movierating.models;

public class Movie {
    private int movieID;
    private String movieName;
    private double rating;
    private String genre; // Optional, included for compatibility with MovieCard

    public Movie(int movieID, String movieName, double rating, String genre) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.rating = rating;
        this.genre = genre != null ? genre : "Unknown"; // Default to "Unknown" if genre is null
    }

    public int getMovieID() {
        return movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public double getRating() {
        return rating;
    }

    public String getGenre() {
        return genre;
    }
}