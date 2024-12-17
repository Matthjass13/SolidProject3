package server.requests;

import java.util.ArrayList;

/**
 * Represents a user request
 * Implementation of the chain of responsability pattern
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class UserRequest {
    String purpose;
    ArrayList<String> items;
    public UserRequest(String input){
        String[] elements = input.split(" : ");
        purpose = elements[0];

        items = new ArrayList<>();
        for(int i=1; i<elements.length; ++i) {
            items.add(elements[i].trim());
        }
    }

    public String getPurpose() {
        return purpose;
    }
    public String getItem(int i) {
        return items.get(i);
    }

}

