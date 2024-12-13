package screens;


import screens.ui.Button;
import screens.ui.Label;
import screens.ui.Rectangle;
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

    public AdminAppScreen(Client client) {
        super(client);

        titleLabel.setText("Admin App");
        super.drawTitle("Admin App");


        screens.ui.Rectangle connectionForm = new Rectangle(330, 110, 400, 300, this);


        new Label("Change road cost to ", 0, 0, connectionForm);
        roadCost = new TextField("New cost", 190, 0, 100, connectionForm);

        new Label("from", 0, 50, connectionForm);
        firstNodeAdmin = new TextField("First Node", 0, 80, connectionForm);
        new Label("to", 0, 110, connectionForm);
        secondNodeAdmin = new TextField("Second node", 0, 140, connectionForm);

        update = new Button("Update", 0, 200, connectionForm);
        update.addActionListener(e -> {
                    sendRequest("Traffic Update : " + firstNodeAdmin + " : " + secondNodeAdmin + " : " + roadCost);
                }
        );



    }




}
