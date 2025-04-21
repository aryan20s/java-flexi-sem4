package movierating.components;

import movierating.entities.Movie;
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
        this.C_HEIGHT = 300;

        this.movie = movie;
        this.shouldHoverDarken = false;

        this.setLayout(new GridLayout(2, 1));
        this.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE - 1, PADDING_SIZE, PADDING_SIZE - 1));
        this.setOpaque(false);
        this.add(getMovieDetails());
        JButton book = Utils.createButton("Book Ticket");
        this.add(book);
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
        return details;
    }
}
