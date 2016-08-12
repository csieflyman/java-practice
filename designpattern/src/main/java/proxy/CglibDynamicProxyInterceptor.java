package proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author flyman
 */
public class CglibDynamicProxyInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object resultObject = null;
        try {
            System.out.println("invoke proxy's method " + method.getName() + " args = " + Arrays.toString(args) );
            resultObject = proxy.invokeSuper(obj, args);
        } catch (IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
            e.printStackTrace();
        }
        return resultObject;
    }
}
