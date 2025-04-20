package movierating.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static movierating.utils.Constants.*;

public class MovieCard extends Card {
    private final String movieName, movieRating, movieGenre;
    private static final BufferedImage ICON_IMAGE;

    static {
        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(MovieCard.class.getResource("/movierating/icon.png"));
        } catch (IOException e) {
            System.err.println("Warning: Could not load icon.png - " + e.getMessage());
        }
        ICON_IMAGE = tempImage;
    }

    public MovieCard(String movieGenre, String movieRating, String movieName) {
        this.movieGenre = movieGenre;
        this.movieRating = movieRating;
        this.movieName = movieName;

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setOpaque(false);

        if (ICON_IMAGE != null) {
            this.add(new JLabel(new ImageIcon(ICON_IMAGE.getScaledInstance(160, 160, Image.SCALE_SMOOTH))));
        } else {
            // Fallback if image is missing
            JLabel placeholder = new JLabel("No Image");
            placeholder.setPreferredSize(new Dimension(160, 160));
            this.add(placeholder);
        }
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
        lMovieRating.setOpaque(false);

        JLabel lMovieGenre = new JLabel(this.movieGenre);
        lMovieGenre.setFont(ARIAL.deriveFont(16.0F));
        lMovieGenre.setForeground(Color.GRAY);
        lMovieGenre.setOpaque(false);

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