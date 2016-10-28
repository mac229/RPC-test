import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.googlecode.jsonrpc4j.ExceptionResolver;
import com.googlecode.jsonrpc4j.JsonRpcClient;
import com.googlecode.jsonrpc4j.JsonRpcClientException;
import com.googlecode.jsonrpc4j.ProxyUtil;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by maciej on 26.10.16.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        BasicConfigurator.configure();

        UserService remoteService = null;
        try {
            JsonRpcClient client = new JsonRpcClient(new ObjectMapper());
            final String auth = "auth";
            final String authValue = "secret1";
            client.setAdditionalJsonContent(new HashMap<String, Object>() {
                {
                    put(auth, authValue);
                }
            });
            client.setExceptionResolver(new ExceptionResolver() {
                @Override
                public Throwable resolveException(ObjectNode objectNode) {
                    JsonNode error = objectNode.get("error");
                    int code = error.get("code").asInt();
                    throw new JsonRpcClientException(1, "bla bla bla", null);
                }
            });
            ClassLoader classLoader = UserService.class.getClassLoader();
            Socket socket = new Socket(InetAddress.getByName("localhost"), 1234);
            remoteService = ProxyUtil.createClientProxy(classLoader, UserService.class, client, socket);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            remoteService.createUser("bob", "the builder", "password");

            //remoteService.createUser("rob", "the coder", "password");
            //remoteService.createUser("top", "the coder", "nope");

            //int count = remoteService.getUserCount();
            //System.out.println("size " + count);

            //User user = remoteService.findUserByUserName("top");
            //System.out.println(user.getFirstName() + " " + user.getPassword());
        } catch (JsonRpcClientException jsonEx) {
            System.out.println("code " + jsonEx.getCode());

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.in.read();
    }
}
