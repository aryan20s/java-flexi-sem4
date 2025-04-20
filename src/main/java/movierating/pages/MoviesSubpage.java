package movierating.pages;

import movierating.components.MovieCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
        moviesList.setLayout(new GridLayout(5, 1, PADDING_SIZE, PADDING_SIZE));
        moviesList.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE - 1, PADDING_SIZE, PADDING_SIZE - 1));
        moviesList.add(new MovieCard("Action", "♥ 4.3", "Name 1"));
        moviesList.add(new MovieCard("Thriller", "♥ 4.1", "Name 2"));
        moviesList.add(new MovieCard("Kids", "♥ 3.7", "Name 3"));
        moviesList.add(new MovieCard("Comedy", "♥ 4.2", "Name 4"));
        moviesList.add(new MovieCard("Sci-Fi", "♥ 4.6", "Name 5"));
        return moviesList;
    }
}
