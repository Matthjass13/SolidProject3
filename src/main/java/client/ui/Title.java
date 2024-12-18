package client.ui;

import client.panels.ClientPanel;

import javax.swing.*;

/**
 * This class corresponds to the titles
 * on top of each client panel.
 * @see Label
 * @see ClientPanel
 * @author Matthias Gaillard
 * @since 11.12.2024
 */
public class Title extends Label {
    public Title(String text, JPanel screen) {
        super(text, 10, -20, 500, 100, 40, true, screen);
    }

}
