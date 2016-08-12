package singleton;

/**
 * @author flyman
 */
public class Singleton implements java.io.Serializable{

    private Singleton() {

    }

    private static final Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

    private Object readResolve() {
        return instance;
    }
}
