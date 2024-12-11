package screens;

import org.w3c.dom.Text;
import screens.ui.Button;
import screens.ui.Label;
import screens.ui.TextField;
import users.adminUsers.AdminUserCreator;
import users.endUsers.EndUserCreator;
import users.UserCreator;

import javax.swing.*;


/**
 * This class will display a registration screen,
 * for the user who has no account yet.
 * @see Screen
 * @since 09.12.2024
 * @author Sara Pereira De Pina
 */
public class RegistrationScreen extends Screen {

    private TextField username;
    private TextField password;
    private TextField mail;
    private TextField phone;
    private TextField address;
    private Label adminLabel;
    private JCheckBox admin;
    private Button submit;

    public RegistrationScreen() {
        super();

        super.drawTitle("Registration");

        username = new TextField("Username", 200, 100, 100, 25, this);
        password = new TextField("Password", 200, 150, 100, 25, this);
        mail = new TextField("Mail", 200, 200, 100, 25, this);
        phone = new TextField("Phone", 200, 250, 100, 25, this);
        address = new TextField("Address", 200, 300, 100, 25, this);


        adminLabel = new Label("Admin", 200, 350, 100, 25, 16, this);
        admin = new JCheckBox();
        admin.setBounds(260, 350, 100, 25);
        add(admin);

        submit = new Button("Sign in", 200, 400, this);
        submit.addActionListener(e -> {
            createUser(username.getText(),
                       password.getText(),
                       mail.getText(),
                       phone.getText(),
                       address.getText(),
                       admin.isSelected());
            if(admin.isSelected())
                changeScreen(new AdminAppScreen());
            else
                changeScreen(new AppScreen());
        });

    }

    public void createUser(String username, String password, String mail, String phone, String address, boolean admin) {
        UserCreator creator;
        if(admin)
            creator = new AdminUserCreator();
        else
            creator = new EndUserCreator();
        creator.createUser(username, password, mail, phone, address);

    }

}
