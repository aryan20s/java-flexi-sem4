package movierating.components;

import movierating.db.entities.Movie;
import movierating.db.entities.Theatre;
import movierating.pages.ShowsPage;
import movierating.pages.TheatresPage;
import movierating.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TheatreListCard extends BaseCard {
    private final Movie movie;
    private final Theatre theatre;
    private final TheatresPage theatresPage;
    private static final BufferedImage ICON_IMAGE;

    static {
        try {
            ICON_IMAGE = ImageIO.read(new File("icon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TheatreListCard(Movie movie, Theatre theatre, TheatresPage theatresPage) {
        this.movie = movie;
        this.theatre = theatre;
        this.theatresPage = theatresPage;

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setOpaque(false);

        this.add(new JLabel(new ImageIcon(ICON_IMAGE.getScaledInstance(160, 160, Image.SCALE_SMOOTH))));
        this.add(getTheatreDetails());
    }

    private JPanel getTheatreDetails() {
        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));

        JLabel lTheatreName = Utils.createLabel(this.theatre.getTheatreName(), 24.0F, Color.BLACK);

        JLabel lTheatreAddress = Utils.createLabel(
            "<html>" + this.theatre.getTheatreAddress() + "</html>", 16.0F, Color.GRAY
        );

        JLabel lTheatreSeats = Utils.createLabel(
            "No. of Seats: " + this.theatre.getTheatreSeats(), 16.0F, Color.GRAY
        );

        details.add(lTheatreName);
        details.add(lTheatreSeats);
        details.add(lTheatreAddress);
        return details;
    }

    @Override
    public void onClicked() {
        this.theatresPage.dispose();
        SwingUtilities.invokeLater(() -> new ShowsPage(this.movie, this.theatre));
    }
}
