package screens.ui;

import screens.Screen;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


/**
 * Customised text fields
 * @author Matthias Gaillard
 * @since 11.12.2024
 */
public class TextField extends JTextField {

    private String placeholder;
    private Color placeholderColor = Color.GRAY;

    public TextField(String placeholder, int x, int y, int width, int height, int fontSize, Screen screen) {
        super();
        this.placeholder = placeholder;
        setColumns(30);
        setBounds(x, y, width, height);
        setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        screen.add(this);

        setBorder(new LineBorder(Color.BLACK, 2));


    }

    public TextField(String placeholder, int x, int y, Screen screen) {
        this(placeholder, x, y, 200, 30, 20, screen);
    }

    /**
     * Stuff to add to correctly display the placeholder
     * ChatGPT generated
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Display placeholder if the field is empty and he is not focused
        if (placeholder != null && getText().isEmpty() && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(placeholderColor);
            g2.setFont(getFont());

            // Calculate position to draw text
            Insets insets = getInsets();
            int x = insets.left + 2;
            int y = getHeight() / 2 + g2.getFontMetrics().getAscent() / 2 - 2;

            g2.drawString(placeholder, x, y);
        }
    }

    public String toString() {
        return getText();
    }

}

