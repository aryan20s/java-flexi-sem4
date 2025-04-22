package movierating.pages;

import movierating.components.ShowListCard;
import movierating.components.TheatreListCard;
import movierating.db.Database;
import movierating.db.entities.Movie;
import movierating.db.entities.Show;
import movierating.db.entities.Theatre;
import movierating.utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static movierating.utils.Constants.ACCENT_COLOR;
import static movierating.utils.Constants.PADDING_SIZE;

public class ShowsPage extends JFrame {
    private final Movie movie;
    private final Theatre theatre;
    public static final int W_WIDTH = 900, W_HEIGHT = 600;

    public ShowsPage(Movie movie, Theatre theatre) {
        this.movie = movie;
        this.theatre = theatre;

        this.setTitle("Shows List");
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
                new TheatresPage(this.movie);
            });
        });
        buttonsPanel.add(backButton, BorderLayout.WEST);
        buttonsPanel.setBackground(ACCENT_COLOR);
        navBar.add(buttonsPanel, BorderLayout.WEST);
        navBar.setBackground(ACCENT_COLOR);
        this.add(navBar, BorderLayout.NORTH);
        this.add(this.makeShowsSubpage());

        this.setVisible(true);
    }

    public JComponent makeShowsSubpage() {
        JPanel moviesList = getShowsList();
        JScrollPane scrollPane = new JScrollPane(moviesList);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private JPanel getShowsList() {
        JPanel showsList = new JPanel();
        showsList.setLayout(new BoxLayout(showsList, BoxLayout.Y_AXIS));
        showsList.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE - 1, PADDING_SIZE, PADDING_SIZE - 1));

        try {
            ArrayList<Show> shows = Database.getAllShowsForMovieAndTheatre(this.movie, this.theatre);

            int showNum = 1;
            for (Show show: shows) {
                showsList.add(new ShowListCard(show, showNum++, this));
                showsList.add(Box.createRigidArea(new Dimension(0, PADDING_SIZE)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return showsList;
    }
}