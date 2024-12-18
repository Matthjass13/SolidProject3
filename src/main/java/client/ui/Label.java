package client.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Customised label
 * @author Matthias Gaillard
 * @since 11.12.2024
 */
public class Label extends JLabel {

    private static final int SPACE_TO_PANEL = 20;

    public Label(String text, int x, int y, int width, int height, int fontSize, boolean bold, JPanel panel) {
        super(text);
        setBounds(x + SPACE_TO_PANEL, y + SPACE_TO_PANEL, width, height);
        if(bold)
            setFont(new Font("Tahoma", Font.BOLD, fontSize));
        else
            setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        panel.add(this);
    }

    public Label(String text, int x, int y, int width, boolean bold, JPanel panel) {
        this(text, x, y, width, 25, 20, bold, panel);
    }
    public Label(String text, int x, int y, int width, JPanel panel) {
        this(text, x, y, width, 25, 20, false, panel);
    }
    public Label(String text, int x, int y, boolean bold, JPanel panel) {
        this(text, x, y, 200, 25, 20, bold, panel);
    }
    public Label(String text, int x, int y, JPanel panel) {
        this(text, x, y, 200, 25, 20, false, panel);
    }

    /**
     * Allows to freely use the label as a String
     * @return String value of the label
     */
    public String toString() {
        return getText();
    }

}