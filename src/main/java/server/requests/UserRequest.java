package server.requests;

import java.util.ArrayList;

/**
 * Represents a user request.
 * Part of the chain of responsibility pattern.
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class UserRequest {

    /**
     * Type of request (path research, path update...)
     */
    private String purpose;

    /**
     * Parameters of the request
     */
    private ArrayList<String> items;


    /**
     * Constructs a UserRquest based on a string.
     * For example : "Search path : Bercy : Bastille".
     * @param input string sent by the client
     */
    public UserRequest(String input){
        String[] elements = input.split(" : ");
        purpose = elements[0];
        items = new ArrayList<>();
        for(int i=1; i<elements.length; ++i)
            items.add(elements[i].trim());
    }

    public String getPurpose() {
        return purpose;
    }
    public String getItem(int i) {
        return items.get(i);
    }

}