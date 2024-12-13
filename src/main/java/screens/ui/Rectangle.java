package screens.ui;

import screens.ClientPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * This class represents a uni-color background rectangle
 * for a section of our JFrame.
 * @author Matthias Gaillard
 * @since 12.12.2024
 */
public class Rectangle extends JPanel {
    public Rectangle(int x, int y, int width, int height, ClientPanel clientPanel) {
        super();
        setBounds(x, y, width, height);
        setBorder(new LineBorder(Color.BLACK, 7));

        setLayout(null);
        setBackground(Color.decode("#BBD2EC"));
        clientPanel.add(this);


    }





}
