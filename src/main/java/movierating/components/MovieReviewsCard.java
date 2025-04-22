package movierating.components;

import movierating.db.Database;
import movierating.db.entities.Movie;
import movierating.db.entities.MovieReview;
import movierating.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static movierating.utils.Constants.PADDING_SIZE;

public class MovieReviewsCard extends BaseCard {
    private static final int REVIEW_HEIGHT = 100;
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
        this.isClickable = false;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE - 1, PADDING_SIZE, PADDING_SIZE - 1));
        this.setOpaque(false);
        this.add(getMovieReviews());
    }

    private JPanel getMovieReviews() {
        ArrayList<MovieReview> reviews;
        try {
            reviews = Database.getMovieReviews(this.movie);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        JPanel main = new JPanel();
        this.C_HEIGHT = REVIEW_HEIGHT * reviews.size();
        main.setLayout(new GridLayout(reviews.size(), 1, PADDING_SIZE, PADDING_SIZE));
        main.setOpaque(false);
        for (MovieReview review: reviews) {
            JPanel details = new JPanel();
            details.setOpaque(false);
            details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));

            String reviewText = review.getReviewText();
            if (reviewText == null || reviewText.isEmpty()) {
                reviewText = "N/A";
            }

            JLabel lPersonName = Utils.createLabel(review.getPersonName(), 24.0F, Color.BLACK);
            JLabel lRating = Utils.createLabel("♥ " + review.getRating(), 20.0F, Color.GRAY);
            JLabel lReviewText = Utils.createLabel(reviewText, 20.0F, Color.GRAY);

            details.add(lPersonName);
            details.add(lRating);
            details.add(lReviewText);

            main.add(details);
        }

        return main;
    }

    @Override
    public void onClicked() {

    }
}
