package proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author flyman
 */
public class Main {

    public static void main(String[] args) {
        testJDK();
        testCglib();
    }

    private static void testJDK() {
        Fly bird = new Bird();
        JDKDynamicProxyHandler handler = new JDKDynamicProxyHandler();
        Fly proxyBird = (Fly) handler.bind(bird);
        String result = proxyBird.fly("right-wing", "left-wing");
    }

    private static void testCglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibDynamicProxyInterceptor());
        enhancer.setSuperclass(Bird.class);
        Bird bird = (Bird) enhancer.create();
        bird.fly("right-wing", "left-wing");
    }
}
