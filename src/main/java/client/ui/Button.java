package client.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Customised buttons
 * @author Matthias Gaillard
 * @since 11.12.2024
 */
public class Button extends JButton {

    private final int SPACE_TO_PANEL = 20;

    public Button(String text, int x, int y, Color color, JPanel panel) {
        super(text);
        setBounds(x + SPACE_TO_PANEL, y + SPACE_TO_PANEL, 150, 50);
        setBorder(new LineBorder(Color.BLACK, 3));
        setBackground(color);
        setFont(new Font("Tahoma", Font.BOLD, 30));
        panel.add(this);
    }

    /**
     * Constructor of a button with a default color
     * @param text
     * @param x
     * @param y
     * @param screen
     */
    public Button(String text, int x, int y, JPanel screen) {
        this(text, x, y, Color.decode("#6495ED"), screen);
    }

}