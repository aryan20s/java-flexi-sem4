package movierating.services;

import movierating.models.Movie;
import movierating.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieService {
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT MovieID, Moviename, rating FROM Movies"; // Adjust if you have a genre column

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int movieID = rs.getInt("MovieID");
                String movieName = rs.getString("Moviename");
                double rating = rs.getDouble("rating");
                // Pass null for genre if not in database; Movie constructor handles default
                movies.add(new Movie(movieID, movieName, rating, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // In a production app, handle errors more gracefully (e.g., log or show message)
        }
        return movies;
    }
}