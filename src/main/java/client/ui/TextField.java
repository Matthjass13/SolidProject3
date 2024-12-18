package client.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Customised text fields
 * @author Matthias Gaillard
 * @since 11.12.2024
 */
public class TextField extends JTextField {

    private String placeholder;
    protected int SPACE_TO_PANEL = 20;

    public TextField(String placeholder, int x, int y, int width, int height, int fontSize, JPanel screen) {
        super();
        this.placeholder = placeholder;
        setColumns(30);
        setBounds(x + SPACE_TO_PANEL, y + SPACE_TO_PANEL, width, height);
        setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        screen.add(this);
        setBorder(new LineBorder(Color.BLACK, 2));
    }

    public TextField(String placeholder, int x, int y, JPanel screen) {
        this(placeholder, x, y, 150, 30, 20, screen);
    }

    public TextField(String placeholder, int x, int y, int width, JPanel screen) {
        this(placeholder, x, y, width, 30, 20, screen);
    }

    /**
     * Stuff to add to correctly display the placeholder
     * ChatGPT generated
     * @param g the <code>Graphics</code> object to protect
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

    public String toString() {
        return getText();
    }

}

