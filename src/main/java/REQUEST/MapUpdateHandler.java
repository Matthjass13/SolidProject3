package REQUEST;

public class MapUpdateHandler extends Handler{
    final String allow = "Map Update";
    public void processRequest(userRequest request){
        if(request.getPurpose().equals(allow)){
            System.out.println("Map Update Handler will execute the request : "+allow);
        }
        else{
            if(successor != null){
                successor.processRequest(request);
            }
        }
    }
}
