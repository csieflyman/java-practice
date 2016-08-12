package concurrent.producerconsumer;

/**
 * @author flyman
 */
public class Producer implements Runnable {

    private Factory factory;
    private int total;

    public Producer(Factory factory, int total) {
        this.factory = factory;
        this.total = total;
    }

    @Override
    public void run() {
        for (int i = 0; i < total; i++) {
            factory.produce();
        }
    }
}
