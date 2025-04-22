package movierating;

import movierating.db.Database;
import movierating.db.entities.User;
import movierating.pages.LoginPage;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static User CURRENT_USER;

    public static void main(String[] args) throws SQLException {
        Database.init();

        SwingUtilities.invokeLater(LoginPage::new);
    }
}