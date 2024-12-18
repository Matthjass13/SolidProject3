package client.panels;

/**
 * Abstract state of the JFrame of the client.
 * Implementation of the state pattern.
 * @author Matthias Gaillard
 * @since 12.12.2024
 */
public interface ClientState {

    /**
     * Transition from AppState to ConnectionState
     */
    void logOut();

    /**
     * Transition from ConnectionState to AppState or AdminAppState
     */
    void logIn(boolean admin);

    /**
     * Transition from RegistrationState to ConnectionState
     */
    void goToConnection();

    /**
     * Transition from ConnectionState to RegistrationState
     */
    void goToRegistration();

}