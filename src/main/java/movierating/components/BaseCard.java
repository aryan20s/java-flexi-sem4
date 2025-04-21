package movierating.components;

import movierating.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import static movierating.utils.Constants.CORNER_RADIUS;
import static movierating.utils.Constants.STROKE_WIDTH;

public abstract class BaseCard extends JPanel implements CardClickHandler {
    protected int C_WIDTH;
    protected int C_HEIGHT;
    protected boolean isClickable = true;
    private boolean isMouseHovered = false;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.isClickable) {
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        Graphics2D g2 = (Graphics2D) g;
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(hints);

        g2.setColor((isMouseHovered && isClickable) ? Color.WHITE.darker() : Color.WHITE);
        g2.setStroke(new BasicStroke(0.1F));
        RoundRectangle2D rect = new RoundRectangle2D.Double(
            STROKE_WIDTH / 2.0,
            STROKE_WIDTH / 2.0,
            getWidth() - STROKE_WIDTH,
            getHeight() - STROKE_WIDTH,
            CORNER_RADIUS,
            CORNER_RADIUS
        );
        g2.fill(rect);

        g2.setColor(Constants.ACCENT_COLOR);
        g2.setStroke(new BasicStroke(STROKE_WIDTH));
        rect = new RoundRectangle2D.Double(
            STROKE_WIDTH / 2.0,
            STROKE_WIDTH / 2.0,
            getWidth() - STROKE_WIDTH,
            getHeight() - STROKE_WIDTH,
            CORNER_RADIUS,
            CORNER_RADIUS
        );
        g2.draw(rect);
    }

    public BaseCard() {
        this.C_WIDTH = 850;
        this.C_HEIGHT = 125;

        BaseCard thisW = this;
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                thisW.isMouseHovered = true;
                thisW.repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                thisW.isMouseHovered = false;
                thisW.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                thisW.onClicked();
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(C_WIDTH, C_HEIGHT);
    }
}
