package client.panels;


import client.Client;
import client.ui.Button;
import client.ui.Label;
import client.ui.Rectangle;
import client.ui.TextField;

/**
 * This class will display the main app to the admin :
 * it is the same as for the end user but with more functionalities.
 * @see AppClientPanel
 * @author Sara Pereira
 * @since 10.12.2024
 */
public class AdminAppClientPanel extends AppClientPanel {

    private TextField firstNodeAdmin;
    private TextField secondNodeAdmin;
    private TextField roadCost;
    private Button update;

    private int SPACE_BETWEEN_FORMS;

    public AdminAppClientPanel(Client client) {
        super(client);

        titleLabel.setText("Admin App");
        super.drawTitle("Admin App");


        Rectangle connectionForm = new Rectangle(450, 110, 320, 300, this);


        new Label("Change road cost to ", 0, 0, 250, true, connectionForm);



        roadCost = new TextField("New cost", 205, 0, 85, connectionForm);

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
