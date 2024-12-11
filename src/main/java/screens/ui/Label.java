package screens.ui;

import screens.Screen;
import javax.swing.*;
import java.awt.*;

/**
 * Customised labels
 * @author Matthias Gaillard
 * @since 11.12.2024
 */

public class Label extends JLabel {
    public Label(String text, int x, int y, int width, int height, int fontSize, Screen screen) {
        super(text);
        setBounds(x, y, width, height);
        setFont(new Font("Arial", Font.BOLD, fontSize));
        screen.add(this);
    }

    public Label(String text, int x, int y, int width, int height, Screen screen) {
        this(text, x, y, width, height, 20, screen);
    }

    public String toString() {
        return getText();
    }

}
