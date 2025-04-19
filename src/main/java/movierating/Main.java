package movierating;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginPage page = new LoginPage();
            page.setVisible(true);
        });
    }
}