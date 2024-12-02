package REQUEST;

public class SettingsUpdateHandler extends Handler{
    final String allow = "Settings Update";
    public void processRequest(userRequest request){
        if(request.getPurpose().equals(allow)){
            System.out.println("Settings Update Handler will execute the request : "+allow);
        }
        else{
            if(successor != null){
                successor.processRequest(request);
            }
        }
    }
}
