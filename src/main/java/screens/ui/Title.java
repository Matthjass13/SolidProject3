package screens.ui;

import screens.Screen;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used to display the titles
 * on top of each screen.
 * @see Label
 * @see Screen
 * @author Matthias Gaillard
 * @since 11.12.2024
 */
public class Title extends Label {
    public Title(String text, JPanel screen) {
        super(text, 10, -20, 500, 100, 40, true, screen);
    }

}
