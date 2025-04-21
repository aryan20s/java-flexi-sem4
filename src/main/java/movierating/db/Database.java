package movierating.db;

import movierating.entities.Movie;
import movierating.entities.MovieActor;
import movierating.entities.MovieReview;
import movierating.entities.MovieStaff;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static Connection con;

    public static void init() throws SQLException {
        con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/movies2",
            "root", ""
        );
    }

    public static ArrayList<Movie> getAllMovies() throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(
            "SELECT m.MovieName,  m.Description, m.Genre, r.Rating, m.MovieID " +
                "FROM Movies m " +
                "JOIN (" +
                    "SELECT MovieID, ROUND(AVG(MovieScore), 1) AS Rating " +
                    "FROM MovieReview " +
                    "GROUP BY MovieID" +
                ") r " +
                "ON m.MovieID = r.MovieID"
        );

        ArrayList<Movie> movies = new ArrayList<>();
        while (rs.next()) {
            String movieName = rs.getString(1);
            String movieDescription = rs.getString(2);
            String movieGenre = rs.getString(3);
            String movieRating = "♥ " + rs.getDouble(4);
            int movieID = rs.getInt(5);

            PreparedStatement ps = con.prepareStatement(
                "SELECT p.Name, w.RoleInProduction " +
                    "FROM Persons p " +
                    "JOIN WorkedOn w " +
                    "ON w.MovieStaffID = p.PersonID " +
                    "WHERE MovieID = ?"
            );
            ps.setInt(1, movieID);

            ResultSet rs2 = ps.executeQuery();

            ArrayList<MovieStaff> movieStaff = new ArrayList<>();
            while (rs2.next()) {
                movieStaff.add(new MovieStaff(rs2.getString(1), rs2.getString(2)));
            }

            ps = con.prepareStatement(
                "SELECT p.Name, a.CharacterPlayed " +
                    "FROM Persons p " +
                    "JOIN ActsIn a " +
                    "ON a.ActorID = p.PersonID " +
                    "WHERE MovieID = ?"
            );
            ps.setInt(1, movieID);

            rs2 = ps.executeQuery();

            ArrayList<MovieActor> movieActors = new ArrayList<>();
            while (rs2.next()) {
                movieActors.add(new MovieActor(rs2.getString(1), rs2.getString(2)));
            }

            movies.add(new Movie(movieName, movieDescription, movieGenre, movieRating, movieID, movieStaff, movieActors));
        }

        return movies;
    }

    public static ArrayList<MovieReview> getMovieReviews(Movie movie) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "SELECT p.Name, r.ReviewText, r.MovieScore " +
                "FROM MovieReview r " +
                "JOIN Persons p " +
                "ON p.PersonID = r.UserID " +
                "WHERE r.MovieID = ?"
        );
        st.setInt(1, movie.getMovieID());

        ResultSet rs = st.executeQuery();


        ArrayList<MovieReview> movies = new ArrayList<>();
        while (rs.next()) {
            String personName = rs.getString(1);
            String reviewText = rs.getString(2);
            double rating = rs.getDouble(3);
            movies.add(new MovieReview(personName, reviewText, movie, rating));
        }

        return movies;
    }
}
