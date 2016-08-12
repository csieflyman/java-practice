package concurrent.producerconsumer;

/**
 * @author flyman
 */
public class SynchronizedBlockFactory implements Factory {
    private int maxSize = 5;
    private volatile int productCount = 0;
    private Object pLockObject = new Object();
    private Object cLockObject = new Object();

    @Override
    public void produce() {
        synchronized (pLockObject) {
            producerWaitIfFull();
            productCount++;
            System.out.println(Thread.currentThread().getName() + " produce " + productCount);
        }
        synchronized (cLockObject) {
            notifyConsumerIfNotEmpty();
        }
    }

    private void producerWaitIfFull() {
        while (productCount >= maxSize) {
            try {
                pLockObject.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void notifyConsumerIfNotEmpty() {
        if(productCount > 0) {
            cLockObject.notifyAll();
        }
    }

    @Override
    public void consume() {
        synchronized (cLockObject) {
            consumerWaitIfEmpty();
            productCount--;
            System.out.println(Thread.currentThread().getName() + " consume " + productCount);
        }
        synchronized (pLockObject) {
            notifyProducerIfNotFull();
        }
    }

    private void consumerWaitIfEmpty() {
        while (productCount == 0) {
            try {
                cLockObject.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void notifyProducerIfNotFull() {
        if (productCount < maxSize) {
            pLockObject.notifyAll();
        }
    }
}
