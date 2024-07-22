package dynamic_proxy.jdk_proxy;

interface SmsService {
    String send(String message);
}

public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("send " + message);
        return message;
    }
}
