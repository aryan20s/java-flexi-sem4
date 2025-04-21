package movierating.components;

import movierating.db.Database;
import movierating.entities.Movie;
import movierating.entities.MovieReview;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static movierating.utils.Constants.ARIAL;
import static movierating.utils.Constants.PADDING_SIZE;

public class MovieReviewsCard extends Card {
    private final Movie movie;
    private static final BufferedImage ICON_IMAGE;

    static {
        try {
            ICON_IMAGE = ImageIO.read(new File("icon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MovieReviewsCard(Movie movie) {
        this.C_WIDTH = 750;
        this.movie = movie;
        this.shouldHoverDarken = false;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE - 1, PADDING_SIZE, PADDING_SIZE - 1));
        this.setOpaque(false);
        this.add(getMovieReviews());
    }

    private JPanel getMovieReviews() {
        ArrayList<MovieReview> reviews = new ArrayList<>();
        try {
            reviews = Database.getMovieReviews(this.movie);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JPanel main = new JPanel();
        this.C_HEIGHT = 100 * reviews.size();
        main.setLayout(new GridLayout(reviews.size(), 1, 10, 10));
        main.setOpaque(false);
        for (MovieReview review: reviews) {
            JPanel details = new JPanel();
            details.setOpaque(false);
            details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));

            JLabel lPersonName = new JLabel(review.getPersonName());
            lPersonName.setFont(ARIAL.deriveFont(24.0F));
            lPersonName.setForeground(Color.BLACK);
            lPersonName.setOpaque(false);

            JLabel lRating = new JLabel("♥ " + review.getRating());
            lRating.setFont(ARIAL.deriveFont(20.0F));
            lRating.setForeground(Color.GRAY);
            lRating.setOpaque(false);


            String reviewText = review.getReviewText();
            if (reviewText == null || reviewText.isEmpty()) {
                reviewText = "N/A";
            }
            JLabel lReviewText = new JLabel(reviewText);
            lReviewText.setFont(ARIAL.deriveFont(16.0F));
            lReviewText.setForeground(Color.GRAY);
            lReviewText.setOpaque(false);

            details.add(lPersonName);
            details.add(lRating);
            details.add(lReviewText);

            main.add(details);
        }

        return main;
    }
}
