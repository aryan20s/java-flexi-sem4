package movierating.components;

import movierating.entities.Movie;
import movierating.pages.HomePage;
import movierating.pages.MovieInfoPage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static movierating.utils.Constants.*;

public class MovieListCard extends Card {
    private final Movie movie;
    private final HomePage homePage;
    private static final BufferedImage ICON_IMAGE;

    static {
        try {
            ICON_IMAGE = ImageIO.read(new File("icon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MovieListCard(Movie movie, HomePage homePage) {
        this.movie = movie;
        this.homePage = homePage;

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setOpaque(false);

        this.add(new JLabel(new ImageIcon(ICON_IMAGE.getScaledInstance(160, 160, Image.SCALE_SMOOTH))));
        this.add(getMovieDetails());
    }

    private JPanel getMovieDetails() {
        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));

        JLabel lMovieName = new JLabel(this.movie.getMovieName());
        lMovieName.setFont(ARIAL.deriveFont(24.0F));
        lMovieName.setForeground(Color.BLACK);
        lMovieName.setOpaque(false);

        JLabel lMovieRating = new JLabel(this.movie.getMovieRating());
        lMovieRating.setFont(ARIAL.deriveFont(16.0F));
        lMovieRating.setForeground(Color.GRAY);
        lMovieName.setOpaque(false);

        JLabel lMovieGenre = new JLabel(this.movie.getMovieGenre());
        lMovieGenre.setFont(ARIAL.deriveFont(16.0F));
        lMovieGenre.setForeground(Color.GRAY);
        lMovieName.setOpaque(false);

        details.add(lMovieName);
        details.add(lMovieGenre);
        details.add(lMovieRating);
        return details;
    }

    @Override
    public void onClicked() {
        System.out.println(this.movie.getMovieName() + " clicked.");

        SwingUtilities.invokeLater(() -> {
            new MovieInfoPage(this.movie);
            homePage.dispose();
        });
    }
}
