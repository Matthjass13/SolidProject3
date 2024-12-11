package screens;


import screens.ui.Button;
import screens.ui.Label;
import screens.ui.TextField;

import javax.swing.*;

/**
 * This class will display the main app to the admin :
 * it is the same as for the end user but with more functionalities.
 * @see AppScreen
 * @since 10.12.2024
 * @author Sara Pereira De Pina
 */
public class AdminAppScreen extends AppScreen {

    private Label changeRoadCost;
    private TextField firstNodeAdmin;
    private Label toAdmin;
    private TextField secondNodeAdmin;
    private TextField roadCost;
    private Button calculateAdmin;

    public AdminAppScreen() {
        super();

        super.drawTitle("Admin App");

        changeRoadCost = new Label("Change road cost : ", 400, 100, 200, 25, this);
        firstNodeAdmin = new TextField("First Node", 400, 150, 80, 25, this);
        toAdmin = new Label("to", 500, 150, 40, 25, this);
        secondNodeAdmin = new TextField("Second node", 520, 150, 80, 25, this);

        roadCost = new TextField("New cost", 400, 200, 200, 25, this);
        roadCost.setBounds(400, 200, 200, 25);
        add(roadCost);

        calculateAdmin = new Button("Change cost", 400, 250, this);
        calculateAdmin.addActionListener(e -> {
                }
        );

    }

}
