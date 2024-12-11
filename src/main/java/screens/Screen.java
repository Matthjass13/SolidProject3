package screens;

import javax.swing.*;

import screens.ui.Title;

import java.awt.*;

/**
 * This class will display a generic JFrame to the client.
 * Subclasses will define the specific of each screen.
 * @see RegistrationScreen
 * @see ConnectionScreen
 * @see AppScreen
 * @since 09.12.2024
 * @author Sara Pereira De Pina
 */

public class Screen extends JFrame {



    private Title titleLabel;
    private static Color BACKGROUND_COLOR = Color.decode("#87CEFA");
    public Screen() {
        setBounds(0, 0, 800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        //getContentPane().setBackground(BACKGROUND_COLOR);
    }

    public void changeScreen(Screen s) {
        s.setVisible(true);
        int x = getX();
        int y = getY();
        int width = getWidth();
        int height = getHeight();
        s.setBounds(x, y, width, height);
        dispose();
    }

    public void drawTitle(String title) {
        titleLabel = new Title(title, this);
    }

}
