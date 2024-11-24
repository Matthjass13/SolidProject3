
public class Test {

    public static void main(String[] args) {

        Thread t = new Thread(new TestThread2("A"));
        Thread t2 = new Thread(new TestThread2("  B"));
        t.start();
        t2.start();

    }

}
