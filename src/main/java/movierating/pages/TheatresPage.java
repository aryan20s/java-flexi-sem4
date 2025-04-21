package movierating.pages;

import movierating.components.TheatreListCard;
import movierating.db.Database;
import movierating.db.entities.Movie;
import movierating.db.entities.Theatre;
import movierating.utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static movierating.utils.Constants.ACCENT_COLOR;
import static movierating.utils.Constants.PADDING_SIZE;

public class TheatresPage extends JFrame {
    private final Movie movie;
    public static final int W_WIDTH = 900, W_HEIGHT = 600;

    public TheatresPage(Movie movie) {
        this.movie = movie;

        this.setTitle("Theatres List");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(W_WIDTH, W_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JPanel navBar = new JPanel();
        JPanel buttonsPanel = new JPanel();
        navBar.setLayout(new BorderLayout());
        JButton backButton = Utils.createButton("Back");
        backButton.addActionListener(e -> {
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                new MovieInfoPage(this.movie);
            });
        });
        buttonsPanel.add(backButton, BorderLayout.WEST);
        buttonsPanel.setBackground(ACCENT_COLOR);
        navBar.add(buttonsPanel, BorderLayout.WEST);
        navBar.setBackground(ACCENT_COLOR);
        this.add(navBar, BorderLayout.NORTH);
        this.add(this.makeTheatresSubpage());

        this.setVisible(true);
    }

    public JComponent makeTheatresSubpage() {
        JPanel moviesList = getTheatresList();
        JScrollPane scrollPane = new JScrollPane(moviesList);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private JPanel getTheatresList() {
        JPanel theatresList = new JPanel();
        theatresList.setLayout(new BoxLayout(theatresList, BoxLayout.Y_AXIS));
        theatresList.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE - 1, PADDING_SIZE, PADDING_SIZE - 1));

        try {
            ArrayList<Theatre> theatres = Database.getAllTheatresForMovie(this.movie);
            for (Theatre theatre: theatres) {
                theatresList.add(new TheatreListCard(this.movie, theatre, this));
                theatresList.add(Box.createRigidArea(new Dimension(0, PADDING_SIZE)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return theatresList;
    }
}