package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author flyman
 */
public class JDKDynamicProxyHandler implements InvocationHandler{

    private Object target;

    public Object bind(Object target) {
        this.target = target;
        Object proxyObject = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        return proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object resultObject = null;
        try {
            System.out.println("invoke proxy's method " + method.getName() + " args = " + Arrays.toString(args) );
            resultObject = method.invoke(target, args);
        } catch (IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
            e.printStackTrace();
        }
        return resultObject;
    }
}
