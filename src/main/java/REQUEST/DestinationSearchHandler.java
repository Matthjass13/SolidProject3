package REQUEST;

public class DestinationSearchHandler extends Handler{
    final String allow = "Destination Search"; /* for the exemple i put some purpose
    but instead we can implement for exemple action/event*/
    public void processRequest(userRequest request){
        if(request.getPurpose().equals(allow)){
            System.out.println("Destination Search Handler will execute the request : "+allow);
        }
        else{
            if(successor != null){
                successor.processRequest(request);
            }
        }
    }

}
