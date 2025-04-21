package movierating.components;

import com.mysql.cj.util.StringUtils;
import movierating.entities.Movie;
import movierating.utils.Constants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;

import java.awt.*;

import static movierating.utils.Constants.PADDING_SIZE;

public class WriteReviewCard extends Card {
    private final Movie movie;

    public WriteReviewCard(Movie movie) {
        this.C_WIDTH = 750;
        this.C_HEIGHT = 144;
        this.movie = movie;
        this.shouldHoverDarken = false;
        this.setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE - 1, PADDING_SIZE, PADDING_SIZE - 1));
        this.setOpaque(false);

        JTextArea area = new JTextArea("Write a Review");
        area.setFont(Constants.ARIAL.deriveFont(16.0F));
        area.setColumns(60);
        area.setRows(6);
        area.setLineWrap(true);
        area.setEditable(false);
        this.add(area);

        area.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    }
}
