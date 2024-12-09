package screens;

import javax.swing.*;

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

    public Screen() {
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
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

}
