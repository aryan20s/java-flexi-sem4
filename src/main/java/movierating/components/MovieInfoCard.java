package movierating.components;

import movierating.entities.Movie;
import movierating.entities.MovieActor;
import movierating.entities.MovieStaff;
import movierating.pages.HomePage;
import movierating.pages.MovieInfoPage;
import movierating.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static movierating.utils.Constants.ARIAL;
import static movierating.utils.Constants.PADDING_SIZE;

public class MovieInfoCard extends Card {
    private final Movie movie;
    private static final BufferedImage ICON_IMAGE;

    static {
        try {
            ICON_IMAGE = ImageIO.read(new File("icon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MovieInfoCard(Movie movie) {
        this.C_WIDTH = 750;
        this.C_HEIGHT = 400;

        this.movie = movie;
        this.shouldHoverDarken = false;

        this.setLayout(new GridLayout(1, 1));
        this.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE - 1, PADDING_SIZE, PADDING_SIZE - 1));
        this.setOpaque(false);
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

        JLabel lMovieDesc = new JLabel("<html>" + this.movie.getMovieDescription() + "</html>");
        lMovieDesc.setFont(ARIAL.deriveFont(20.0F));
        lMovieDesc.setForeground(Color.GRAY);
        lMovieDesc.setOpaque(false);

        JLabel lMovieRating = new JLabel(this.movie.getMovieRating());
        lMovieRating.setFont(ARIAL.deriveFont(16.0F));
        lMovieRating.setForeground(Color.GRAY);
        lMovieRating.setOpaque(false);

        JLabel lMovieGenre = new JLabel(this.movie.getMovieGenre());
        lMovieGenre.setFont(ARIAL.deriveFont(16.0F));
        lMovieGenre.setForeground(Color.GRAY);
        lMovieGenre.setOpaque(false);

        details.add(lMovieName);
        details.add(lMovieGenre);
        details.add(lMovieRating);
        details.add(lMovieDesc);

        details.add(Box.createRigidArea(new Dimension(0, PADDING_SIZE)));
        JLabel lStarring = new JLabel("Starring:");
        lStarring.setFont(ARIAL.deriveFont(18.0F));
        lStarring.setForeground(Color.DARK_GRAY);
        lStarring.setOpaque(false);
        details.add(lStarring);
        for (MovieActor actor: movie.getMovieActors()) {
            JLabel lActor = new JLabel(actor.getName() + " as " + actor.getRole());
            lActor.setFont(ARIAL.deriveFont(16.0F));
            lActor.setForeground(Color.GRAY);
            lActor.setOpaque(false);
            details.add(lActor);
        }

        details.add(Box.createRigidArea(new Dimension(0, PADDING_SIZE)));
        JLabel lStaff = new JLabel("Staff:");
        lStaff.setFont(ARIAL.deriveFont(18.0F));
        lStaff.setForeground(Color.DARK_GRAY);
        lStaff.setOpaque(false);
        details.add(lStaff);
        for (MovieStaff staff: movie.getMovieStaff()) {
            JLabel lActor = new JLabel(staff.getName() + " as " + staff.getRole());
            lActor.setFont(ARIAL.deriveFont(16.0F));
            lActor.setForeground(Color.GRAY);
            lActor.setOpaque(false);
            details.add(lActor);
        }

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        p1.setOpaque(false);
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(10000, 50));
        JButton book = Utils.createButton("Book");
        book.setMaximumSize(new Dimension(10, 10));
        panel.add(book);
        p1.add(panel, BorderLayout.SOUTH);
        details.add(p1);
        return details;
    }
}
