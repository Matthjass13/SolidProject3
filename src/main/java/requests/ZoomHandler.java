package requests;

/**
 * @author Sara Pereira De Pina
 * @since 01.12.2024
 */
public class ZoomHandler extends Handler{
    final String allow = "Zoom";
    public void processRequest(userRequest request){
        if(request.getPurpose().equals(allow)){
            System.out.println("Zoom Handler will execute the request : "+allow);
        }
        else{
            if(successor != null){
                successor.processRequest(request);
            }
        }
    }
}
