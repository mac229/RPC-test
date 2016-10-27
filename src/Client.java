import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcClient;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.log4j.BasicConfigurator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by maciej on 26.10.16.
 */
public class Client {
//zmiana !!
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        BasicConfigurator.configure();

        JsonRpcClient client = null;
        try {
            URL serviceUrl = new URL("http://127.0.0.1:1234");
            client = new JsonRpcHttpClient(serviceUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        OutputStream os = new ByteOutputStream();
        try {
            client.invoke("createUser", new Object[]{"bob", "the builder", "password"}, os);
            System.out.println(os.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
