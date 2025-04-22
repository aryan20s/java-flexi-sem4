package movierating.pages;

import movierating.components.BookingsListCard;
import movierating.components.MovieListCard;
import movierating.db.Database;
import movierating.db.entities.Booking;
import movierating.db.entities.Movie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static movierating.utils.Constants.PADDING_SIZE;

public class BookingsSubpage {
    public static JComponent makeBookingsSubpage(HomePage homePage) {
        JPanel bookingsList = getBookingsList(homePage);
        JScrollPane scrollPane = new JScrollPane(bookingsList);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private static JPanel getBookingsList(HomePage homePage) {
        JPanel list = new JPanel();
        list.setLayout(new BorderLayout());
        JPanel bookingsList = new JPanel();
        bookingsList.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE - 1, PADDING_SIZE, PADDING_SIZE - 1));

        try {
            ArrayList<Booking> bookings = Database.getAllBookings();
            bookingsList.setLayout(new GridLayout(bookings.size(), 1, PADDING_SIZE, PADDING_SIZE));

            for (Booking booking : bookings) {
                bookingsList.add(new BookingsListCard(booking, homePage));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        list.add(bookingsList, BorderLayout.NORTH);

        return list;
    }
}
