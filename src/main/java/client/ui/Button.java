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
    private int SPACE_TO_PANEL = 20;
    public Button(String text, int x, int y, Color color, JPanel screen) {
        super(text);
        setBackground(color);
        setBounds(x + SPACE_TO_PANEL, y + SPACE_TO_PANEL, 150, 50);
        setFont(new Font("Tahoma", Font.BOLD, 30));
        setBorder(new LineBorder(Color.BLACK, 3));
        screen.add(this);
    }

    public Button(String text, int x, int y, JPanel screen) {
        this(text, x, y, Color.decode("#6495ED"), screen);
    }

}
