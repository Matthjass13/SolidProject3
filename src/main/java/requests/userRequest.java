package requests;

/**
 * @author Sara Pereira De Pina
 * @since 01.12.2024
 */
public class userRequest {
    String purpose;

    public userRequest(String p){
        purpose = p;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}

