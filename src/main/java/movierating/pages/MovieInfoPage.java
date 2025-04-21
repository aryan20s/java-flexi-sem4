package movierating.pages;

import movierating.components.MovieInfoCard;
import movierating.components.MovieReviewsCard;
import movierating.db.entities.Movie;
import movierating.utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static movierating.utils.Constants.ACCENT_COLOR;
import static movierating.utils.Constants.PADDING_SIZE;

public class MovieInfoPage extends JFrame {
    public static final int W_WIDTH = 900, W_HEIGHT = 600;

    public MovieInfoPage(Movie movie) {
        this.setTitle("Movie Info");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(W_WIDTH, W_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JPanel navBar = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel();

        JPanel page = new JPanel();
        page.setLayout(new BoxLayout(page, BoxLayout.Y_AXIS));
        page.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE - 1, PADDING_SIZE, PADDING_SIZE - 1));
        page.add(new MovieInfoCard(movie, this));
        page.add(Box.createRigidArea(new Dimension(0, PADDING_SIZE)));
        page.add(new MovieReviewsCard(movie));

        JButton backButton = Utils.createButton("Back");
        backButton.addActionListener(e -> {
            this.dispose();
            SwingUtilities.invokeLater(HomePage::new);
        });
        buttonsPanel.add(backButton);
        buttonsPanel.setBackground(ACCENT_COLOR);

        navBar.add(buttonsPanel, BorderLayout.WEST);
        navBar.setBackground(ACCENT_COLOR);

        JScrollPane scrollPane = new JScrollPane(page);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(navBar, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
