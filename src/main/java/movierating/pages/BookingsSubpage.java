package movierating.pages;

import javax.swing.*;

public class BookingsSubpage {
    public static JComponent makeBookingsSubpage() {
        JScrollPane scrollPane = new JScrollPane(new JLabel("empty"));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }
}
