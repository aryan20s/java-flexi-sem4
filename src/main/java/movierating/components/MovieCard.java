package movierating.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static movierating.utils.Constants.*;

public class MovieCard extends Card {
    private final String movieName, movieRating, movieGenre;
    private static final BufferedImage ICON_IMAGE;

    static {
        try {
            ICON_IMAGE = ImageIO.read(new File("icon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MovieCard(String movieGenre, String movieRating, String movieName) {
        this.movieGenre = movieGenre;
        this.movieRating = movieRating;
        this.movieName = movieName;

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setOpaque(false);

        this.add(new JLabel(new ImageIcon(ICON_IMAGE.getScaledInstance(160, 160, Image.SCALE_SMOOTH))));
        this.add(getMovieDetails());
    }

    private JPanel getMovieDetails() {
        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));

        JLabel lMovieName = new JLabel(this.movieName);
        lMovieName.setFont(ARIAL.deriveFont(24.0F));
        lMovieName.setForeground(Color.BLACK);
        lMovieName.setOpaque(false);

        JLabel lMovieRating = new JLabel(this.movieRating);
        lMovieRating.setFont(ARIAL.deriveFont(16.0F));
        lMovieRating.setForeground(Color.GRAY);
        lMovieName.setOpaque(false);

        JLabel lMovieGenre = new JLabel(this.movieGenre);
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
        System.out.println(this.movieName + " clicked.");
    }
}
