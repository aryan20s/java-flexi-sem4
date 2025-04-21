package movierating.pages;

import movierating.components.MovieListCard;
import movierating.db.Database;
import movierating.db.entities.Movie;

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
        JPanel list = new JPanel();
        list.setLayout(new BorderLayout());
        JPanel moviesList = new JPanel();
        moviesList.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE - 1, PADDING_SIZE, PADDING_SIZE - 1));

        try {
            ArrayList<Movie> movies = Database.getAllMovies();
            moviesList.setLayout(new GridLayout(movies.size(), 1, PADDING_SIZE, PADDING_SIZE));

            for (Movie movie: movies) {
                moviesList.add(new MovieListCard(movie, homePage));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        list.add(moviesList, BorderLayout.NORTH);

        return list;
    }
}
