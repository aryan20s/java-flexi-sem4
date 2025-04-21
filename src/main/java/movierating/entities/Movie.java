package movierating.entities;

import java.util.ArrayList;

public class Movie {
    private final String movieName, movieDescription, movieRating, movieGenre;
    private final int movieID;
    private final ArrayList<MovieStaff> movieStaff;
    private final ArrayList<MovieActor> movieActors;

    public Movie(String movieName, String movieDescription, String movieRating, String movieGenre, int movieID, ArrayList<MovieStaff> movieStaff, ArrayList<MovieActor> movieActors) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieRating = movieRating;
        this.movieGenre = movieGenre;
        this.movieID = movieID;
        this.movieStaff = movieStaff;
        this.movieActors = movieActors;
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

    public ArrayList<MovieStaff> getMovieStaff() {
        return movieStaff;
    }

    public ArrayList<MovieActor> getMovieActors() {
        return movieActors;
    }
}
