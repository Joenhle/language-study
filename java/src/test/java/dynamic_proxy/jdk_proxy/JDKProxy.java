package dynamic_proxy.jdk_proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

class SmsHandler implements InvocationHandler {

    private final Object target;

    public SmsHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("begin");
        Object result = method.invoke(target, args);
        System.out.println("end");
        return result;
    }
}

public class JDKProxy {

    public static void main(String[] args) {
        SmsServiceImpl smsService = new SmsServiceImpl();
        SmsHandler smsHandler = new SmsHandler(smsService);

        SmsService proxy = (SmsService) Proxy.newProxyInstance(
                SmsServiceImpl.class.getClassLoader(),
                new Class[]{SmsService.class},
                smsHandler
        );
        proxy.send("123");

        Future future = new FutureTask();
        future.cancel()
    }
}
