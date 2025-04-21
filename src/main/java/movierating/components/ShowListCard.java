package movierating.components;

import movierating.db.Database;
import movierating.db.entities.Show;
import movierating.pages.HomePage;
import movierating.pages.ShowsPage;
import movierating.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ShowListCard extends BaseCard {
    private final Show show;
    private final ShowsPage showsPage;
    private final int showNum;
    private static final BufferedImage ICON_IMAGE;

    static {
        try {
            ICON_IMAGE = ImageIO.read(new File("icon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ShowListCard(Show show, int showNum, ShowsPage showsPage) {
        this.show = show;
        this.showNum = showNum;
        this.showsPage = showsPage;

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setOpaque(false);

        this.add(new JLabel(new ImageIcon(ICON_IMAGE.getScaledInstance(160, 160, Image.SCALE_SMOOTH))));
        this.add(getShowDetails());
    }

    private JPanel getShowDetails() {
        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));

        JLabel lTheatreName = Utils.createLabel(
            "<html> Show " + this.showNum + ": " + this.show.getTheatre().getTheatreName() + "</html>",
            24.0F, Color.BLACK
        );

        JLabel lShowInfo = Utils.createLabel(
            "Time: " + this.show.getDateTimeString(), 16.0F, Color.GRAY
        );

        details.add(lTheatreName);
        details.add(lShowInfo);

        return details;
    }

    @Override
    public void onClicked() {
        if (JOptionPane.showConfirmDialog (
            null, "Are you sure you want to book?",
            "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION
        ) {
            try {
                if (Database.bookTicket(this.show)) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Ticket was booked successfully!",
                        "Booking successful!",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Failed to book ticket!",
                        "Booking failed!",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            this.showsPage.dispose();
            SwingUtilities.invokeLater(HomePage::new);
        }
    }
}
