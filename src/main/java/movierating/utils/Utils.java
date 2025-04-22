package movierating.utils;

import javax.swing.*;
import java.awt.*;

import static movierating.utils.Constants.ACCENT_COLOR;
import static movierating.utils.Constants.ARIAL;

public class Utils {
    public static JLabel createLabel(String text, float fontSize, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(ARIAL.deriveFont(fontSize));
        label.setForeground(color);
        label.setOpaque(false);

        return label;
    }

    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(Constants.ARIAL);
        button.setBackground(ACCENT_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        button.addActionListener(e -> {
//            System.out.println(button.getText());
//        });

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(ACCENT_COLOR.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(ACCENT_COLOR);
            }
        });
        return button;
    }
}
