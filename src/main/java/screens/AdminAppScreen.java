package screens;


import screens.ui.Button;
import screens.ui.Label;
import screens.ui.TextField;

/**
 * This class will display the main app to the admin :
 * it is the same as for the end user but with more functionalities.
 * @see AppScreen
 * @author Sara Pereira
 * @since 10.12.2024
 */
public class AdminAppScreen extends AppScreen {

    private TextField firstNodeAdmin;
    private TextField secondNodeAdmin;
    private TextField roadCost;
    private Button update;

    private int SPACE_BETWEEN_FORMS;

    public AdminAppScreen() {
        super();


        titleLabel.setText("Admin App");
        super.drawTitle("Admin App");

        new Label("Change road cost to ", 330, 100, this);
        roadCost = new TextField("New cost", 520, 100, this);

        new Label("from", 330, 150, this);
        firstNodeAdmin = new TextField("First Node", 330, 180, this);
        new Label("to", 330, 210, this);
        secondNodeAdmin = new TextField("Second node", 330, 240, this);

        update = new Button("Update", 330, 300, this);
        update.addActionListener(e -> {
                    sendRequest("Traffic Update " + firstNodeAdmin + " " + secondNodeAdmin + " " + roadCost);
                }
        );

    }

}
