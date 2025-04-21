package movierating.pages;

import movierating.utils.Utils;

import javax.swing.*;
import java.awt.*;

import static movierating.utils.Constants.ACCENT_COLOR;

public class HomePage extends JFrame {
    public static final int W_WIDTH = 900, W_HEIGHT = 600;

    public HomePage() {
        this.setTitle("Home");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(W_WIDTH, W_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JPanel mainPanel = new JPanel(new CardLayout());
        JPanel navBar = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel();

        mainPanel.add(MoviesSubpage.makeMoviesSubpage(this), "Movies");
        mainPanel.add(BookingsSubpage.makeBookingsSubpage(), "My Bookings");

        JButton b1 = makePageButton("Movies", mainPanel);
        JButton b2 = makePageButton("My Bookings", mainPanel);

        buttonsPanel.add(b1);
        buttonsPanel.add(b2);
        buttonsPanel.setBackground(ACCENT_COLOR);

        navBar.add(buttonsPanel, BorderLayout.WEST);
        navBar.setBackground(ACCENT_COLOR);

        this.add(navBar, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);

        CardLayout cl = (CardLayout)(mainPanel.getLayout());
        cl.show(mainPanel, "Movies");

        this.setVisible(true);
    }

    private static JButton makePageButton(String pageTitle, JPanel mainPanel) {
        JButton b1 = Utils.createButton(pageTitle);
        b1.addActionListener(e -> ((CardLayout) (mainPanel.getLayout())).show(mainPanel, pageTitle));
        return b1;
    }
}
