package REQUEST;

public class TrafficUpdateHandler extends Handler{
    final String allow = "Traffic Update";
    public void processRequest(userRequest request){
        if(request.getPurpose().equals(allow)){
            System.out.println("Traffic Update Handler will execute the request : "+allow);
        }
        else{
            if(successor != null){
                successor.processRequest(request);
            }
        }
    }
}
