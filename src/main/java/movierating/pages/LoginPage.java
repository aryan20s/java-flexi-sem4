package movierating.pages;

import movierating.Main;
import movierating.db.Database;
import movierating.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

import static movierating.utils.Constants.ACCENT_COLOR;

public class LoginPage extends JFrame {
    public static final int W_WIDTH = 400, W_HEIGHT = 200;

    public LoginPage() {
        this.setTitle("Log In");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(W_WIDTH, W_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JLabel label = Utils.createLabel("Log In", 24.0F, Color.WHITE);
        JPanel navBar = new JPanel();
        navBar.setBackground(ACCENT_COLOR);
        navBar.add(label);
        this.add(navBar, BorderLayout.NORTH);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(2, 2));
        loginPanel.setOpaque(false);
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        JLabel lUsername = Utils.createLabel("Username", 16.0F, Color.BLACK);
        JLabel lPassword = Utils.createLabel("Password", 16.0F, Color.BLACK);
        lUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        lPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(lUsername);
        loginPanel.add(usernameField);
        loginPanel.add(lPassword);
        loginPanel.add(passwordField);
        this.add(loginPanel, BorderLayout.CENTER);

        JButton loginButton = Utils.createButton("Enter");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            try {
                if (Objects.equals(password, Database.getPasswordForUsername(username))) {
                    Main.CURRENT_USER = Database.getPersonforUsername(username);

                    this.dispose();
                    SwingUtilities.invokeLater(HomePage::new);
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Invalid login!",
                        "Invalid login!",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        this.add(loginButton, BorderLayout.SOUTH);

        this.setVisible(true);
    }
}
