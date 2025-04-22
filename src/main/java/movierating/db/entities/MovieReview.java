package movierating.db.entities;

import movierating.Main;
import movierating.db.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieReview implements WriteableDAO {
    private final String personName, reviewText;
    private final Movie movie;
    private final double rating;

    public MovieReview(String personName, String reviewText, Movie movie, double rating) {
        this.personName = personName;
        this.reviewText = reviewText;
        this.movie = movie;
        this.rating = rating;
    }

    public String getPersonName() {
        return personName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public boolean insert() throws SQLException {
        int userID = Main.CURRENT_USER.getPersonID();
        int movieID = this.movie.getMovieID();

        PreparedStatement st = Database.con.prepareStatement(
            "INSERT INTO MovieReview (UserID, MovieID, MovieScore, ReviewText) VALUES (?, ?, ?, ?)"
        );
        st.setInt(1, userID);
        st.setInt(2, movieID);
        st.setDouble(3, this.rating);
        st.setString(4, this.reviewText);

        return st.executeUpdate() > 0;
    }

    public boolean update() throws SQLException {
        int userID = Main.CURRENT_USER.getPersonID();
        int movieID = this.movie.getMovieID();

        PreparedStatement st = Database.con.prepareStatement(
            "UPDATE MovieReview SET MovieScore = ?, ReviewText = ? " +
                    "WHERE UserID = ? AND MovieID = ?"
        );
        st.setDouble(1, this.rating);
        st.setString(2, this.reviewText);
        st.setInt(3, userID);
        st.setInt(4, movieID);

        return st.executeUpdate() > 0;
    }
}
