package REQUEST;

public abstract class Handler {
    Handler successor;
    public void setSuccessor(Handler successor){
        this.successor = successor;
    };
    public abstract void processRequest(userRequest request);

}
