package screens.ui;

import screens.Screen;

import javax.swing.*;
import java.awt.*;

/**
 * Customised buttons
 * @author Matthias Gaillard
 * @since 11.12.2024
 */
public class Button extends JButton {
    public Button(String text, int x, int y, int width, int height, int fontSize, Screen screen) {
        super(text);
        setBackground(Color.GREEN);
        setBounds(x, y, width, height);
        setFont(new Font("Arial", Font.BOLD, fontSize));
        screen.add(this);
    }

    public Button(String text, int x, int y, Screen screen) {
        this(text, x, y, 200, 50, 20, screen);
    }

}
