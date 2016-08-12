package reflection;

/**
 * @author flyman
 */
public class Main {

    public static void main(String[] args) {
        testDynamicProxy();
    }

    private static void testDynamicProxy() {
        Fly bird = new Bird();
        DynamicProxyHandler handler = new DynamicProxyHandler();
        Fly proxyBird = (Fly) handler.bind(bird);
        String result = proxyBird.fly("right-wing", "left-wing");
    }
}
