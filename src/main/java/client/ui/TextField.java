package client.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Customised text field
 * @author Matthias Gaillard
 * @since 11.12.2024
 */
public class TextField extends JTextField {

    protected static int SPACE_TO_PANEL = 20;
    private final String placeholder;

    public TextField(String placeholder, int x, int y, int width, int height, int fontSize, JPanel panel) {
        super();
        this.placeholder = placeholder;
        setBounds(x + SPACE_TO_PANEL, y + SPACE_TO_PANEL, width, height);
        setBorder(new LineBorder(Color.BLACK, 2));
        setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        setColumns(30);
        panel.add(this);
    }

    public TextField(String placeholder, int x, int y, int width, JPanel panel) {
        this(placeholder, x, y, width, 30, 20, panel);
    }
    public TextField(String placeholder, int x, int y, JPanel panel) {
        this(placeholder, x, y, 150, 30, 20, panel);
    }


    /**
     * Allows to freely use the text field as a String
     * @return String value of the text field
     */
    public String toString() {
        return getText();
    }

    /**
     * Stuff to add to correctly display the placeholder
     * ChatGPT generated
     * @param g the Graphics object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (placeholder != null && getText().isEmpty() && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.setFont(getFont());

            Insets insets = getInsets();
            int x = insets.left + 2;
            int y = getHeight() / 2 + g2.getFontMetrics().getAscent() / 2 - 2;

            g2.drawString(placeholder, x, y);
        }

        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                repaint();
            }
        });
    }

}