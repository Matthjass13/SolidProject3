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


    private int SPACE_TO_PANEL = 20;
    public Label(String text, int x, int y, int width, int height, int fontSize, boolean bold, JPanel screen) {
        super(text);
        setBounds(x + SPACE_TO_PANEL, y + SPACE_TO_PANEL, width, height);

        if(bold)
            setFont(new Font("Tahoma", Font.BOLD, fontSize));
        else
            setFont(new Font("Tahoma", Font.PLAIN, fontSize));

        screen.add(this);
    }

    public Label(String text, int x, int y, int width, JPanel screen) {
        this(text, x, y, width, 25, 20, false, screen);
    }
    public Label(String text, int x, int y, JPanel screen) {
        this(text, x, y, 200, 25, 20, false, screen);
    }
    public String toString() {
        return getText();
    }

}
