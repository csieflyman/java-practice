package singleton;

/**
 * @author flyman
 */
public class Main {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        LazySingleton lazySingleton = LazySingleton.getInstance();
        LazyAsyncSingleton lazyAsyncSingleton = LazyAsyncSingleton.getInstance();
    }
}
