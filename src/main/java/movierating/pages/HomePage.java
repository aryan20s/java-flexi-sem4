package movierating.pages;

import movierating.Main;
import movierating.utils.Utils;

import javax.swing.*;
import java.awt.*;

import static movierating.utils.Constants.ACCENT_COLOR;

public class HomePage extends JFrame implements Updatable {
    public static final int W_WIDTH = 900, W_HEIGHT = 600;
    private final JPanel mainPanel;
    private JComponent bookingsSubpage;
    private JComponent moviesSubpage;
    private final CardLayout cl;

    public HomePage() {
        this.setTitle("Home");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(W_WIDTH, W_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        cl = new CardLayout();
        mainPanel = new JPanel(cl);
        JPanel navBar = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel();

        moviesSubpage = MoviesSubpage.makeMoviesSubpage(this);
        mainPanel.add(moviesSubpage, "Movies");
        bookingsSubpage = BookingsSubpage.makeBookingsSubpage(this);
        mainPanel.add(bookingsSubpage, "My Bookings");

        JButton b1 = makePageButton("Movies", mainPanel);
        JButton b2 = makePageButton("My Bookings", mainPanel);

        buttonsPanel.add(b1);
        buttonsPanel.add(b2);
        buttonsPanel.setBackground(ACCENT_COLOR);

        navBar.add(buttonsPanel, BorderLayout.WEST);
        navBar.add(
            Utils.createLabel(
                "Logged in as: " + Main.CURRENT_USER.getUsername() + "   ",
                16.0F, Color.WHITE
            ),
            BorderLayout.EAST
        );
        navBar.setBackground(ACCENT_COLOR);

        this.add(navBar, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);

        this.setPage("Movies");

        this.setVisible(true);
    }

    public void setPage(String page) {
        cl.show(mainPanel, page);
    }

    private static JButton makePageButton(String pageTitle, JPanel mainPanel) {
        JButton b1 = Utils.createButton(pageTitle);
        b1.addActionListener(e -> ((CardLayout) (mainPanel.getLayout())).show(mainPanel, pageTitle));
        return b1;
    }

    @Override
    public void refreshContents() {
        mainPanel.remove(moviesSubpage);
        moviesSubpage = MoviesSubpage.makeMoviesSubpage(this);
        mainPanel.add(moviesSubpage, "Movies");

        mainPanel.remove(bookingsSubpage);
        bookingsSubpage = BookingsSubpage.makeBookingsSubpage(this);
        mainPanel.add(bookingsSubpage, "My Bookings");

        SwingUtilities.invokeLater(() -> {
            this.setPage("My Bookings");
        });
    }
}
