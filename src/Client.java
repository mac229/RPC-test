import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

/**
 * Created by maciej on 26.10.16.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        BasicConfigurator.configure();

        UserService remoteService = null;
        try {
            URL serviceUrl = new URL("http://127.0.0.1:1234");
            JsonRpcClient client = new JsonRpcClient(new ObjectMapper());
            ClassLoader classLoader = UserService.class.getClassLoader();
            Socket socket = new Socket(InetAddress.getByName("localhost"), 1234);
            remoteService = ProxyUtil.createClientProxy(classLoader, UserService.class, client, socket);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            User user = remoteService.createUser("bob", "the builder", "password");
            System.out.println(user.getFirstName());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.in.read();
    }
}
