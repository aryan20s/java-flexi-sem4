package movierating;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static movierating.Constants.*;

public class MovieCard extends JPanel {
    public static final int C_WIDTH = 850, C_HEIGHT = 125;
    private static final BufferedImage iconImage;
    private boolean isMouseHovered = false;

    static {
        try {
            iconImage = ImageIO.read(new File("icon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    String movieName, movieRating, movieGenre;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(hints);

        g2.setColor(isMouseHovered ? Color.WHITE.darker() : Color.WHITE);
        g2.setStroke(new BasicStroke(0.1F));
        RoundRectangle2D rect = new RoundRectangle2D.Double(
            STROKE_WIDTH / 2.0,
            STROKE_WIDTH / 2.0,
            getWidth() - STROKE_WIDTH,
            getHeight() - STROKE_WIDTH,
            CORNER_RADIUS,
            CORNER_RADIUS
        );
        g2.fill(rect);

        g2.setColor(Constants.ACCENT_COLOR);
        g2.setStroke(new BasicStroke(STROKE_WIDTH));
        rect = new RoundRectangle2D.Double(
            STROKE_WIDTH / 2.0,
            STROKE_WIDTH / 2.0,
            getWidth() - STROKE_WIDTH,
            getHeight() - STROKE_WIDTH,
            CORNER_RADIUS,
            CORNER_RADIUS
        );
        g2.draw(rect);

    }

    public MovieCard(String movieGenre, String movieRating, String movieName) {
        this.movieGenre = movieGenre;
        this.movieRating = movieRating;
        this.movieName = movieName;

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setOpaque(false);

//        JButton button = Utils.createButton("Book");
//        button.setMaximumSize(new Dimension(button.getWidth(), 50));
//        JPanel buttonFrame = new JPanel(new BorderLayout());
//        buttonFrame.setBorder(new EmptyBorder(0, 500, 0, 0));
//        buttonFrame.add(button, BorderLayout.EAST);
//        buttonFrame.setOpaque(false);
        this.add(new JLabel(new ImageIcon(iconImage.getScaledInstance(160, 160, Image.SCALE_SMOOTH))));
        this.add(getMovieDetails(movieGenre, movieRating, movieName));

        MovieCard thisW = this;
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                thisW.isMouseHovered = true;
                thisW.repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                thisW.isMouseHovered = false;
                thisW.repaint();
            }
        });
        //this.add(buttonFrame);
    }

    private static JPanel getMovieDetails(String movieGenre, String movieRating, String movieName) {
        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));

        JLabel lMovieName = new JLabel(movieName);
        lMovieName.setFont(ARIAL.deriveFont(24.0F));
        lMovieName.setForeground(Color.BLACK);
        lMovieName.setOpaque(false);

        JLabel lMovieRating = new JLabel(movieRating);
        lMovieRating.setFont(ARIAL.deriveFont(16.0F));
        lMovieRating.setForeground(Color.GRAY);
        lMovieName.setOpaque(false);

        JLabel lMovieGenre = new JLabel(movieGenre);
        lMovieGenre.setFont(ARIAL.deriveFont(16.0F));
        lMovieGenre.setForeground(Color.GRAY);
        lMovieName.setOpaque(false);

        details.add(lMovieName);
        details.add(lMovieGenre);
        details.add(lMovieRating);
        return details;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(C_WIDTH, C_HEIGHT);
    }
}
