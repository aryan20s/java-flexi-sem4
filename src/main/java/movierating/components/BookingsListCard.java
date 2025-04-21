package movierating.components;

import movierating.Main;
import movierating.db.Database;
import movierating.db.entities.Booking;
import movierating.db.entities.MovieReview;
import movierating.pages.HomePage;
import movierating.pages.ReviewingPage;
import movierating.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static movierating.utils.Constants.PADDING_SIZE;

public class BookingsListCard extends BaseCard {
    private final Booking booking;
    private HomePage homePage;
    private static final BufferedImage ICON_IMAGE;

    static {
        try {
            ICON_IMAGE = ImageIO.read(new File("icon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BookingsListCard(Booking booking, HomePage homePage) {
        this.isClickable = false;
        this.booking = booking;
        this.homePage = homePage;

        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        JPanel main = new JPanel();
        main.setOpaque(false);
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
        main.add(new JLabel(new ImageIcon(ICON_IMAGE.getScaledInstance(160, 160, Image.SCALE_SMOOTH))));
        main.add(getBookingDetails());

        JButton cancelBooking = Utils.createButton("Cancel Booking");
        cancelBooking.addActionListener(e -> {
            try {
                if (JOptionPane.showConfirmDialog(
                    null, "Are you sure you want to cancel your booking?",
                    "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION
                ) {
                    Database.deleteBookingAndReviews(this.booking);
                    SwingUtilities.invokeLater(() -> {
                        this.homePage.refreshContents();
                    });
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton writeReview = Utils.createButton("Write Review");
        writeReview.addActionListener(e -> {
            this.homePage.dispose();
            SwingUtilities.invokeLater(() -> new ReviewingPage(this.booking));
        });

        JPanel buttons = new JPanel();
        buttons.setOpaque(false);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.add(cancelBooking);
        buttons.add(Box.createRigidArea(new Dimension(0, PADDING_SIZE)));
        buttons.add(writeReview);
        buttons.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE));

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
            writeReview.setText("Update Review");
        }

        this.add(main);
        this.add(buttons, BorderLayout.EAST);
    }

    private JPanel getBookingDetails() {
        JPanel full = new JPanel();
        full.setOpaque(false);
        full.setLayout(new BoxLayout(full, BoxLayout.X_AXIS));

        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));

        JLabel lMovieName = Utils.createLabel(this.booking.getShow().getMovie().getMovieName(), 24.0F, Color.BLACK);
        JLabel lBookingID = Utils.createLabel("Booking ID: " + this.booking.getBookingID(), 16.0F, Color.GRAY);
        JLabel lInfo = Utils.createLabel(this.booking.getShow().getTheatre().getTheatreName() + ", " + this.booking.getShow().getDateTimeString(), 16.0F, Color.GRAY);

        details.add(lMovieName);
        details.add(lBookingID);
        details.add(lInfo);

        full.add(details);

        return full;
    }

    @Override
    public void onClicked() {

    }
}
