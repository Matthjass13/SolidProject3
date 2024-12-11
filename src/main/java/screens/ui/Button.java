package screens.ui;

import screens.Screen;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Customised buttons
 * @author Matthias Gaillard
 * @since 11.12.2024
 */
public class Button extends JButton {
    public Button(String text, int x, int y, Color color, Screen screen) {
        super(text);
        setBackground(color);
        setBounds(x, y, 150, 50);
        setFont(new Font("Tahoma", Font.BOLD, 30));
        setBorder(new LineBorder(Color.BLACK, 3));
        screen.add(this);
    }

    public Button(String text, int x, int y, Screen screen) {
        this(text, x, y, Color.decode("#009DCF"), screen);
    }

}
