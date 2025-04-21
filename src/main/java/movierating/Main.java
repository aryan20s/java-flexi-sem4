package movierating;

import movierating.db.Database;
import movierating.pages.HomePage;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database.init();

        SwingUtilities.invokeLater(HomePage::new);
    }
}