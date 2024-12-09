package requests;

/**
 * @author Sara Pereira De Pina
 * @since 01.12.2024
 */
public abstract class Handler {
    Handler successor;
    public void setSuccessor(Handler successor){
        this.successor = successor;
    };
    public abstract void processRequest(userRequest request);

}
