package movierating.pages;

import movierating.Main;
import movierating.db.Database;
import movierating.db.entities.Booking;
import movierating.db.entities.MovieReview;
import movierating.utils.Constants;
import movierating.utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.SQLException;

import static movierating.utils.Constants.ACCENT_COLOR;
import static movierating.utils.Constants.PADDING_SIZE;

public class ReviewingPage extends JFrame {
    private final Booking booking;
    public static final int W_WIDTH = 700, W_HEIGHT = 320;

    public ReviewingPage(Booking booking) {
        this.booking = booking;

        this.setTitle("Shows List");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(W_WIDTH, W_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JPanel navBar = new JPanel();
        JPanel buttonsPanel1 = new JPanel();
        JPanel buttonsPanel2 = new JPanel();
        navBar.setLayout(new BorderLayout());

        JButton backButton = Utils.createButton("Back");
        backButton.addActionListener(e -> {
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                HomePage homePage = new HomePage();
                homePage.setPage("My Bookings");
            });
        });
        buttonsPanel1.add(backButton);


        JButton submitButton = Utils.createButton("Submit");
        buttonsPanel2.add(submitButton);

        buttonsPanel1.setBackground(ACCENT_COLOR);
        buttonsPanel2.setBackground(ACCENT_COLOR);
        navBar.add(buttonsPanel1, BorderLayout.WEST);
        navBar.add(buttonsPanel2, BorderLayout.EAST);
        navBar.setBackground(ACCENT_COLOR);
        this.add(navBar, BorderLayout.NORTH);

        JPanel main = new JPanel();
        JTextField rating = new JTextField(5);
        rating.setFont(Constants.ARIAL);
        rating.setBorder(new LineBorder(Color.BLACK));
        JTextArea review = new JTextArea(10, 30);
        review.setLineWrap(true);
        review.setFont(Constants.ARIAL);
        review.setBorder(new LineBorder(Color.BLACK));

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        JPanel ratingPanel = new JPanel(new BorderLayout());
        ratingPanel.add(Utils.createLabel("Rating Score (0-10): ", 20.0F, Color.BLACK), BorderLayout.WEST);
        ratingPanel.add(rating, BorderLayout.CENTER);
        ratingPanel.setBorder(new EmptyBorder(0, 0, PADDING_SIZE * 4, 0));
        main.add(ratingPanel);
        JLabel lReview = Utils.createLabel("Review (optional): ", 20.0F, Color.BLACK);
        lReview.setBorder(new EmptyBorder(0, 0, PADDING_SIZE, 0));
        JPanel reviewPanel = new JPanel(new BorderLayout());
        reviewPanel.add(lReview, BorderLayout.NORTH);
        reviewPanel.add(review, BorderLayout.CENTER);
        main.add(reviewPanel);
        main.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE));

        MovieReview movieReview = null;
        try {
            movieReview = Database.getReviewByUserAndMovie(
                Main.CURRENT_USER.getPersonID(),
                this.booking.getShow().getMovie().getMovieID()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (movieReview != null) {
            rating.setText(movieReview.getRating() + "");
            review.setText(movieReview.getReviewText());

            submitButton.setText("Update");
        }

        MovieReview finalMovieReview = movieReview;
        submitButton.addActionListener(e -> {
            String reviewAmt = rating.getText();
            String reviewText = review.getText();
            double amt = -1.0D;

            try {
                amt = Double.parseDouble(reviewAmt);
            } catch (NumberFormatException ignored) {
            }

            if (amt < 0.0 || amt > 10.0) {
                JOptionPane.showMessageDialog(
                    null,
                    "Invalid rating amount! Must be a number from 0 to 10.",
                    "Invalid rating amount!",
                    JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            if (reviewText.length() >= 255) {
                JOptionPane.showMessageDialog(
                    null,
                    "Review is too long! Must be less than 255 characters.",
                    "Invalid review length!",
                    JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            try {
                boolean success;
                if (finalMovieReview == null) {
                     success = Database.writeMovieReview(new MovieReview(
                        Main.CURRENT_USER.getUsername(),
                        reviewText,
                        this.booking.getShow().getMovie(),
                        amt
                    ));
                } else {
                    success = Database.updateMovieReview(new MovieReview(
                        Main.CURRENT_USER.getUsername(),
                        reviewText,
                        this.booking.getShow().getMovie(),
                        amt
                    ));
                }

                if (success) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Review was submitted successfully!",
                        "Review successful!",
                        JOptionPane.INFORMATION_MESSAGE
                    );

                    this.dispose();

                    SwingUtilities.invokeLater(() -> {
                        HomePage homePage = new HomePage();
                        homePage.setPage("My Bookings");
                    });
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Failed to submit review!",
                        "Review failed!",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        this.add(main);
        this.setVisible(true);
    }
}
