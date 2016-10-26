import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import org.apache.log4j.BasicConfigurator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by maciej on 26.10.16.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        BasicConfigurator.configure();
        JsonRpcServer server = new JsonRpcServer(new ObjectMapper(), new UserServiceImpl(), UserService.class);

        JsonRpcHttpClient client = null;
        try {
            URL serviceUrl = new URL("http://127.0.0.1:1234");
            client = new JsonRpcHttpClient(serviceUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            User user = client.invoke("createUser", new Object[]{"bob", "the builder", "password"}, User.class);
            System.out.println(user.getPassword());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
