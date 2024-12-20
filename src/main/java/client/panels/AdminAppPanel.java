package client.panels;

import client.Client;
import client.ui.Button;
import client.ui.Label;
import client.ui.Rectangle;
import client.ui.TextField;

/**
 * This class will display the main app to the admin :
 * it is the same as for the end user but with more functionalities.
 * @see AppPanel
 * @author Sara Pereira
 * @since 10.12.2024
 */
public class AdminAppPanel extends AppPanel {

    private final TextField roadCost;
    private final TextField adminSource;
    private final TextField adminSink;

    public AdminAppPanel(Client client) {
        super(client);
        titleLabel.setText("Admin App");
        super.drawTitle("Admin App");

        Rectangle updateForm = new Rectangle(450, 100, 320, 300, this);
        new Label("Change road cost to ", 0, 0, 250, true, updateForm);
        roadCost = new TextField("Cost", 210, 0, 60, updateForm);
        new Label("from", 0, 50, updateForm);
        adminSource = new TextField("First node", 0, 80, updateForm);
        new Label("to", 0, 110, updateForm);
        adminSink = new TextField("Second node", 0, 140, updateForm);

        Button update = new Button("Update", 0, 200, updateForm);
        update.addActionListener(e ->
                sendRequest("Traffic update : " + adminSource + " : " + adminSink + " : " + roadCost));
    }

}