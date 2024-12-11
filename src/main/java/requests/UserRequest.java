package requests;

/**
 * Represents a user request
 * Implementation of the Chain of Responsability pattern
 * @author Sara Pereira De Pina
 * @since 01.12.2024
 */
public class UserRequest {
    String purpose;

    public UserRequest(String p){
        purpose = p;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}

