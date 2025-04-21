package movierating.db;

import movierating.Main;
import movierating.db.entities.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class Database {
    private static Connection con;

    public static void init() throws SQLException {
        con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/movies2",
            "root", ""
        );
    }

    public static Movie getMovieForID(int id) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "SELECT m.MovieName,  m.Description, m.Genre, r.Rating, m.MovieID " +
            "FROM Movies m " +
            "JOIN (" +
                "SELECT MovieID, ROUND(AVG(MovieScore), 1) AS Rating " +
                "FROM MovieReview " +
                "GROUP BY MovieID" +
            ") r " +
            "ON m.MovieID = r.MovieID " +
            "WHERE m.MovieID = ?"
        );
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        String movieName = null, movieDescription = null, movieGenre = null, movieRating = null;
        int movieID = 0;
        while(rs.next()) {
            movieName = rs.getString(1);
            movieDescription = rs.getString(2);
            movieGenre = rs.getString(3);
            movieRating = "♥ " + rs.getDouble(4);
            movieID = rs.getInt(5);
        }

        if (movieName == null) {
            return null;
        }

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

        return new Movie(movieName, movieDescription, movieRating, movieGenre, movieID, movieStaff, movieActors);
    }

    public static ArrayList<Movie> getAllMovies() throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(
            "SELECT m.MovieID " +
                "FROM Movies m"
        );

        ArrayList<Movie> movies = new ArrayList<>();
        while (rs.next()) {
            movies.add(getMovieForID(rs.getInt(1)));
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

    public static Theatre getTheatreForID(int theatreID) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM Theatres t " +
                "WHERE t.TheatreID = ?"
        );
        st.setInt(1, theatreID);

        ResultSet rs = st.executeQuery();

        Theatre theatre = null;
        while (rs.next()) {
            String theatreName = rs.getString(2);
            String theatreAddress = rs.getString(3);
            int theatreSeats = rs.getInt(4);
            theatre = new Theatre(theatreName, theatreAddress, theatreID, theatreSeats);
        }

        return theatre;
    }

    public static ArrayList<Theatre> getAllTheatresForMovie(Movie movie) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM Theatres t " +
                "WHERE t.TheatreID IN (" +
                    "SELECT s.TheatreID FROM Shows s WHERE MovieID = ? " +
                ")"
        );
        st.setInt(1, movie.getMovieID());

        ResultSet rs = st.executeQuery();

        ArrayList<Theatre> theatres = new ArrayList<>();
        while (rs.next()) {
            int theatreID = rs.getInt(1);
            String theatreName = rs.getString(2);
            String theatreAddress = rs.getString(3);
            int theatreSeats = rs.getInt(4);
            theatres.add(new Theatre(theatreName, theatreAddress, theatreID, theatreSeats));
        }

        return theatres;
    }

    public static String getPasswordForUsername(String username) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "SELECT Password FROM Users " +
                "WHERE Username = ?"
        );
        st.setString(1, username);

        ResultSet rs = st.executeQuery();

        String pw = null;
        if (rs.next()) {
            pw = rs.getString(1);
        }

        return pw;
    }

    public static User getPersonforUsername(String username) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "SELECT PersonID FROM Users " +
                "WHERE Username = ?"
        );
        st.setString(1, username);

        ResultSet rs = st.executeQuery();

        int id = -1;
        if (rs.next()) {
            id = rs.getInt(1);
        }

        return new User(username, id);
    }

    public static ArrayList<Show> getAllShowsForMovieAndTheatre(Movie movie, Theatre theatre) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM Shows " +
                "WHERE TheatreID = ? AND MovieID = ?"
        );
        st.setInt(1, theatre.getTheatreID());
        st.setInt(2, movie.getMovieID());

        ResultSet rs = st.executeQuery();

        ArrayList<Show> shows = new ArrayList<>();
        while (rs.next()) {
            int showID = rs.getInt(1);
            LocalTime startTime = rs.getTime(2).toLocalTime();
            LocalDate showDate = rs.getDate(3).toLocalDate();
            double price = rs.getDouble(4);

            Show show = new Show(showID, price, movie, theatre, showDate, startTime);
            shows.add(show);
        }

        return shows;
    }

    public static Show getShowByID(int showID) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM Shows " +
                "WHERE ShowID = ?"
        );
        st.setInt(1, showID);

        ResultSet rs = st.executeQuery();

        Show show = null;
        while (rs.next()) {
            LocalTime startTime = rs.getTime(2).toLocalTime();
            LocalDate showDate = rs.getDate(3).toLocalDate();
            double price = rs.getDouble(4);
            int theatreID = rs.getInt(5);
            int movieID = rs.getInt(6);

            show = new Show(showID, price, getMovieForID(movieID), getTheatreForID(theatreID), showDate, startTime);
        }

        return show;
    }

    public static boolean bookTicket(Show show) throws SQLException {
        int personID = Main.CURRENT_USER.getPersonID();
        int showID = show.getShowID();

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT BookingID FROM Bookings");

        boolean bookingIDFound = false;
        Random r = new Random();
        int bookingID = r.nextInt(1000, 10000);
        while (!bookingIDFound) {
            boolean foundThisTime = false;
            bookingID = r.nextInt(1000, 10000);

            while (rs.next()) {
                if (bookingID == rs.getInt(1)) {
                    foundThisTime = true;
                    break;
                }
            }

            if (!foundThisTime) {
                bookingIDFound = true;
            }
        }

        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO Bookings VALUES (" +
                    "?, ?, ?" +
                ")"
        );

        ps.setInt(1, bookingID);
        ps.setInt(2, showID);
        ps.setInt(3, personID);

        return ps.executeUpdate() != 0; // if 0 rows set then failed
    }

    public static ArrayList<Booking> getAllBookings() throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "SELECT BookingID, ShowID FROM Bookings " +
                "WHERE UserID = ?"
        );
        st.setInt(1, Main.CURRENT_USER.getPersonID());
        
        ResultSet rs = st.executeQuery();
        ArrayList<Booking> bookings = new ArrayList<>();
        
        while (rs.next()) {
            int bookingID = rs.getInt(1);
            Show show = getShowByID(rs.getInt(2));

            bookings.add(new Booking(bookingID, show));
        }
        
        return bookings;
    }

    public static MovieReview getReviewByUserAndMovie(int userID, int movieID) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "SELECT p.Name, r.ReviewText, r.MovieScore " +
                "FROM MovieReview r " +
                "JOIN Persons p ON p.PersonID = r.UserID " +
                "WHERE r.UserID = ? AND r.MovieID = ?"
        );
        st.setInt(1, userID);
        st.setInt(2, movieID);

        ResultSet rs = st.executeQuery();
        MovieReview review = null;
        if (rs.next()) {
            String personName = rs.getString(1);
            String reviewText = rs.getString(2);
            double rating = rs.getDouble(3);
            Movie movie = getMovieForID(movieID);
            review = new MovieReview(personName, reviewText, movie, rating);
        }
        return review;
    }

    public static boolean writeMovieReview(MovieReview review) throws SQLException {
        int userID = Main.CURRENT_USER.getPersonID();
        int movieID = review.getMovie().getMovieID();

        PreparedStatement st = con.prepareStatement(
            "INSERT INTO MovieReview (UserID, MovieID, MovieScore, ReviewText) VALUES (?, ?, ?, ?)"
        );
        st.setInt(1, userID);
        st.setInt(2, movieID);
        st.setDouble(3, review.getRating());
        st.setString(4, review.getReviewText());

        return st.executeUpdate() > 0;
    }

    public static boolean updateMovieReview(MovieReview review) throws SQLException {
        int userID = Main.CURRENT_USER.getPersonID();
        int movieID = review.getMovie().getMovieID();

        PreparedStatement st = con.prepareStatement(
            "UPDATE MovieReview SET MovieScore = ?, ReviewText = ? " +
                "WHERE UserID = ? AND MovieID = ?"
        );
        st.setDouble(1, review.getRating());
        st.setString(2, review.getReviewText());
        st.setInt(3, userID);
        st.setInt(4, movieID);

        return st.executeUpdate() > 0;
    }

    public static boolean deleteBookingAndReviews(Booking booking) throws SQLException {
        int userID = Main.CURRENT_USER.getPersonID();
        int movieID = booking.getShow().getMovie().getMovieID();
        int bookingID = booking.getBookingID();

        PreparedStatement stReview = con.prepareStatement(
            "DELETE FROM MovieReview WHERE UserID = ? AND MovieID = ?"
        );
        stReview.setInt(1, userID);
        stReview.setInt(2, movieID);
        stReview.executeUpdate();

        PreparedStatement stBooking = con.prepareStatement(
            "DELETE FROM Bookings WHERE BookingID = ?"
        );
        stBooking.setInt(1, bookingID);

        return stBooking.executeUpdate() > 0;
    }
}
