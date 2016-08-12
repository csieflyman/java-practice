package singleton;

/**
 * @author flyman
 */
public class LazyAsyncSingleton {

    private LazyAsyncSingleton() {

    }

    private static class LazyAsyncSingletonHolder {
        private static LazyAsyncSingleton instance = new LazyAsyncSingleton();
    }

    public static LazyAsyncSingleton getInstance() {
        return LazyAsyncSingletonHolder.instance;
    }
}
