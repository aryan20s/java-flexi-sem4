package movierating.entities;

public class Movie {
    private final String movieName, movieDescription, movieRating, movieGenre;
    private final int movieID;

    public Movie(String movieName, String movieDescription, String movieRating, String movieGenre, int movieID) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieRating = movieRating;
        this.movieGenre = movieGenre;
        this.movieID = movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public int getMovieID() {
        return movieID;
    }
}
