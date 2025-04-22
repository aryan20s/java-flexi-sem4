package movierating.components;

import movierating.db.entities.Movie;
import movierating.pages.HomePage;
import movierating.pages.MovieInfoPage;
import movierating.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MovieListCard extends BaseCard {
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

        JLabel lMovieName = Utils.createLabel(this.movie.getMovieName(), 24.0F, Color.BLACK);
        JLabel lMovieRating = Utils.createLabel(this.movie.getMovieRating(), 16.0F, Color.GRAY);
        JLabel lMovieGenre = Utils.createLabel(this.movie.getMovieGenre(), 16.0F, Color.GRAY);

        details.add(lMovieName);
        details.add(lMovieGenre);
        details.add(lMovieRating);
        return details;
    }

    @Override
    public void onClicked() {
        SwingUtilities.invokeLater(() -> {
            new MovieInfoPage(this.movie);
            homePage.dispose();
        });
    }
}
