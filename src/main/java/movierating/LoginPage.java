package movierating;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LoginPage extends JFrame {
    public LoginPage() {
        setTitle("Custom Login Page");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(200);
        splitPane.setDividerSize(2);
        splitPane.setEnabled(false);

        splitPane.setLeftComponent(createLeftPanel());
        splitPane.setRightComponent(createRightPanel());

        add(splitPane, BorderLayout.CENTER);
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(44, 62, 80));
        return panel;
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(236, 240, 241));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font serifFont = new Font("Serif", Font.PLAIN, 16);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(serifFont);
        panel.add(userLabel, gbc);

        gbc.gridy++;
        JTextField userField = new JTextField();
        styleControl(userField, serifFont);
        panel.add(userField, gbc);

        gbc.gridy++;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(serifFont);
        panel.add(passLabel, gbc);

        gbc.gridy++;
        JPasswordField passField = new JPasswordField();
        styleControl(passField, serifFont);
        panel.add(passField, gbc);

        gbc.gridy++;
        JButton loginButton = new JButton("Login");
        loginButton.setFont(serifFont);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(52, 152, 219)); // Blue
        loginButton.setBorder(new RoundedBorder(10, new Color(41, 128, 185)));
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(200, 35));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            JOptionPane.showMessageDialog(LoginPage.this,
                "Username: " + username + "\nPassword: " + password,
                "Login Info", JOptionPane.INFORMATION_MESSAGE);
        });

        return panel;
    }

    private void styleControl(JComponent comp, Font font) {
        comp.setFont(font);
        comp.setBackground(Color.WHITE);
        comp.setOpaque(true);
        comp.setBorder(new RoundedBorder(30, new Color(189, 195, 199)));
        comp.setPreferredSize(new Dimension(200, 30));
    }

    private static class RoundedBorder implements Border {
        private int radius;
        private Color color;

        public RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius+1, radius+1, radius+1, radius+1);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}

