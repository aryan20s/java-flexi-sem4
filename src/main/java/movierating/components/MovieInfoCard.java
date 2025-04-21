package movierating.components;

import movierating.db.entities.Movie;
import movierating.db.entities.MovieActor;
import movierating.db.entities.MovieStaff;
import movierating.pages.MovieInfoPage;
import movierating.pages.TheatresPage;
import movierating.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static movierating.utils.Constants.PADDING_SIZE;

public class MovieInfoCard extends BaseCard {
    private final Movie movie;
    private final MovieInfoPage movieInfoPage;
    private static final BufferedImage ICON_IMAGE;

    static {
        try {
            ICON_IMAGE = ImageIO.read(new File("icon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MovieInfoCard(Movie movie, MovieInfoPage movieInfoPage) {
        this.C_WIDTH = 750;
        this.C_HEIGHT = 450;

        this.movie = movie;
        this.movieInfoPage = movieInfoPage;
        this.isClickable = false;

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(new JLabel(new ImageIcon(ICON_IMAGE.getScaledInstance(160, 160, Image.SCALE_SMOOTH))));

        this.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE - 1, PADDING_SIZE, PADDING_SIZE - 1));
        this.setOpaque(false);
        this.add(getMovieDetails());
    }

    private JPanel getMovieDetails() {
        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));

        JLabel lMovieName = Utils.createLabel(this.movie.getMovieName(), 24.0F, Color.BLACK);
        JLabel lMovieDesc = Utils.createLabel("<html>" + this.movie.getMovieDescription() + "</html>", 20.0F, Color.GRAY);
        JLabel lMovieRating = Utils.createLabel(this.movie.getMovieRating(), 16.0F, Color.GRAY);
        JLabel lMovieGenre = Utils.createLabel(this.movie.getMovieGenre(), 16.0F, Color.GRAY);

        details.add(lMovieName);
        details.add(lMovieGenre);
        details.add(lMovieRating);

        details.add(Box.createRigidArea(new Dimension(0, PADDING_SIZE)));
        details.add(Utils.createLabel("Starring:", 18.0F, Color.DARK_GRAY));
        for (MovieActor actor : movie.getMovieActors()) {
            details.add(Utils.createLabel(actor.getName() + " as " + actor.getRole(), 16.0F, Color.GRAY));
        }

        details.add(Box.createRigidArea(new Dimension(0, PADDING_SIZE)));

        details.add(Utils.createLabel("Staff:", 18.0F, Color.DARK_GRAY));
        for (MovieStaff staff : movie.getMovieStaff()) {
            details.add(Utils.createLabel(staff.getName() + " as " + staff.getRole(), 16.0F, Color.GRAY));
        }

        details.add(Box.createRigidArea(new Dimension(0, PADDING_SIZE)));
        details.add(lMovieDesc);
        details.add(Box.createRigidArea(new Dimension(0, PADDING_SIZE)));

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        p1.setOpaque(false);
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(10000, 50));
        JButton book = Utils.createButton("Book");

        book.addActionListener(e -> {
            this.movieInfoPage.dispose();
            SwingUtilities.invokeLater(() -> {
                new TheatresPage(this.movie);
            });
        });
        panel.add(book);
        p1.add(panel, BorderLayout.SOUTH);
        details.add(p1);
        return details;
    }

    @Override
    public void onClicked() {

    }
}
