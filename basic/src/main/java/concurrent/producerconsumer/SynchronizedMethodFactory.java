package concurrent.producerconsumer;

/**
 * @author flyman
 */
public class SynchronizedMethodFactory implements Factory{

    private int maxSize = 5;
    private int productCount = 0;

    @Override
    public synchronized void produce() {
        producerWaitIfFull();
        productCount++;
        System.out.println("produce " + productCount);
        notifyConsumerIfNotEmpty();
    }

    private synchronized void producerWaitIfFull() {
        while(productCount >= maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void notifyConsumerIfNotEmpty() {
        if(productCount > 0) {
            notifyAll();
        }
    }

    @Override
    public synchronized void consume() {
        consumerWaitIfEmpty();
        productCount--;
        System.out.println("consume " + productCount);
        notifyProducerIfNotFull();
    }

    private synchronized void consumerWaitIfEmpty() {
        while(productCount == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void notifyProducerIfNotFull() {
        if(productCount < maxSize) {
            notifyAll();
        }
    }
}
