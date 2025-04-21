package movierating.pages;

import movierating.components.MovieListCard;
import movierating.db.Database;
import movierating.entities.Movie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static movierating.utils.Constants.PADDING_SIZE;

public class MoviesSubpage {
    public static JComponent makeMoviesSubpage(HomePage homePage) {
        JPanel moviesList = getMoviesList(homePage);
        JScrollPane scrollPane = new JScrollPane(moviesList);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private static JPanel getMoviesList(HomePage homePage) {
        JPanel moviesList = new JPanel();
        moviesList.setLayout(new GridLayout(5, 1, PADDING_SIZE, PADDING_SIZE));
        moviesList.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE - 1, PADDING_SIZE, PADDING_SIZE - 1));

        try {
            ArrayList<Movie> movies = Database.getAllMovies();
            for (Movie movie: movies) {
                moviesList.add(new MovieListCard(movie, homePage));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return moviesList;
    }
}
