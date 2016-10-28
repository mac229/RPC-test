package http.service;

/**
 * Created by Marcin on 26.10.2016.
 */
public interface EchoServiceInterface {
    /**
     * Returns a welcome message
     */
    public String sayHello();

    /**
     * Returns the argument
     * @param text
     * @return
     */
    public String echo(String text);
}
