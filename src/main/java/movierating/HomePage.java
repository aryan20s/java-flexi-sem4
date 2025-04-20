package movierating;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static movierating.Constants.ACCENT_COLOR;
import static movierating.Constants.PADDING_SIZE;

public class HomePage extends JFrame {
    public static final int W_WIDTH = 900, W_HEIGHT = 600;

    public HomePage() {
        this.setTitle("Movie Booking App");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(W_WIDTH, W_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JPanel moviesList = getMoviesList();
        JPanel mainPanel = new JPanel(new CardLayout());
        JPanel navBar = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel();

        JScrollPane scrollPane = new JScrollPane(moviesList);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        CardLayout cl = (CardLayout)(mainPanel.getLayout());
        cl.show(mainPanel, "Movies");

        mainPanel.add(scrollPane, "Movies");
        navBar.add(buttonsPanel, BorderLayout.WEST);
        buttonsPanel.add(Utils.createButton("Movies"));
        buttonsPanel.add(Utils.createButton("My Bookings"));
        buttonsPanel.setBackground(ACCENT_COLOR);
        navBar.setBackground(ACCENT_COLOR);

        this.add(navBar, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);

        this.setVisible(true);
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
