package movierating.pages;

import movierating.components.MovieCard;
import movierating.models.Movie;
import movierating.services.MovieService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

import static movierating.utils.Constants.PADDING_SIZE;

public class MoviesSubpage {
    public static JComponent makeMoviesSubpage() {
        JPanel moviesList = getMoviesList();
        JScrollPane scrollPane = new JScrollPane(moviesList);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private static JPanel getMoviesList() {
        JPanel moviesList = new JPanel();
        moviesList.setLayout(new GridLayout(0, 1, PADDING_SIZE, PADDING_SIZE)); // Dynamic rows, 1 column
        moviesList.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE - 1, PADDING_SIZE, PADDING_SIZE - 1));

        // Fetch movies from the database
        MovieService movieService = new MovieService();
        List<Movie> movies = movieService.getAllMovies();

        // Add a MovieCard for each movie
        for (Movie movie : movies) {
            String ratingText = "♥ " + String.format("%.1f", movie.getRating());
            moviesList.add(new MovieCard(movie.getGenre(), ratingText, movie.getMovieName()));
        }

        return moviesList;
    }
}