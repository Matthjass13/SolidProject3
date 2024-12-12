package requests;

/**
 * Represents a user request
 * Implementation of the chain of responsability pattern
 * @author Sara Pereira
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


    public boolean isAbout(String type) {
        return purpose.contains(type);
    }

}

