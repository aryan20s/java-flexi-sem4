package movierating.pages;

import movierating.Main;
import movierating.db.Database;
import movierating.utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

import static movierating.utils.Constants.ACCENT_COLOR;
import static movierating.utils.Constants.PADDING_SIZE;

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

        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        JLabel lUsername = Utils.createLabel("Username:  ", 16.0F, Color.BLACK);
        JLabel lPassword = Utils.createLabel("Password:  ", 16.0F, Color.BLACK);
        lUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        lPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 0)));
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 0)));

        JPanel fieldColumns = new JPanel();
        fieldColumns.setLayout(new BoxLayout(fieldColumns, BoxLayout.X_AXIS));
        fieldColumns.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE));

        JPanel labels = new JPanel();
        labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
        labels.add(lUsername);
        labels.add(Box.createRigidArea(new Dimension(0, (PADDING_SIZE * 5) / 2)));
        labels.add(lPassword);
        JPanel fields = new JPanel();
        fields.add(usernameField);
        fields.add(Box.createRigidArea(new Dimension(0, PADDING_SIZE)));
        fields.add(passwordField);
        fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));

        fieldColumns.add(labels);
        fieldColumns.add(fields);

        this.add(navBar, BorderLayout.NORTH);
        this.add(fieldColumns, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }
}
