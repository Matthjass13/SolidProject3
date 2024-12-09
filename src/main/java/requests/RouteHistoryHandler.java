package requests;

/**
 * @author Sara Pereira De Pina
 * @since 01.12.2024
 */
public class RouteHistoryHandler extends Handler{
    final String allow = "Route History";
    public void processRequest(userRequest request){
        if(request.getPurpose().equals(allow)){
            System.out.println("Route History Handler will execute the request : "+allow);
        }
        else{
            if(successor != null){
                successor.processRequest(request);
            }
        }
    }
}
