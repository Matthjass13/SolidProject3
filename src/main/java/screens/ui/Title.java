package screens.ui;

import screens.Screen;

/**
 * This class is used to display the titles
 * on top of each screen.
 * @see Label
 * @see Screen
 * @author Matthias Gaillard
 * @since 11.12.2024
 */
public class Title extends Label {
    public Title(String text, Screen screen) {
        super(text, 30, 0, 500, 100, 30, screen);
    }

}
