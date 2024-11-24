public class TestThread2 extends Thread {
    public TestThread2(String name) {
        super(name);
    }

    public void run() {
        for(int i=0; i<10; ++i) {
            System.out.println(this.getName());
        }
    }
}
