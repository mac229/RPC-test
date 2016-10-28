package http.service;

/**
 * Created by Marcin on 26.10.2016.
 */
public class EchoServiceImpl implements EchoServiceInterface {

    @Override
    public String sayHello() {
        return "asdasd";
    }

    @Override
    public String echo(String text) {
        return null;
    }
}
