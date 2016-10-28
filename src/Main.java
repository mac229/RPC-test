import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.googlecode.jsonrpc4j.RequestInterceptor;
import com.googlecode.jsonrpc4j.StreamServer;
import fc.put.bsr.rpc.http.CustomException;
import org.apache.log4j.BasicConfigurator;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class Main {


    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        BasicConfigurator.configure();

        UserService userService = new UserServiceImpl();

        JsonRpcServer server = new JsonRpcServer(new ObjectMapper(), userService, UserService.class);
        server.setAllowExtraParams(true);
        server.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void interceptRequest(JsonNode jsonNode) throws Throwable {
                if (!jsonNode.get("auth").textValue().equals("secret")) {
                    System.out.println("THROWING");
                    //throw new JsonRpcClientException(1, "bla bla bla", null);
                    throw new CustomException("bla bla bla");
                }
            }
        });

        // create the stream server
        int maxThreads = 50;
        ServerSocket socket = ServerSocketFactory.getDefault().createServerSocket(1234, 0, InetAddress.getByName("localhost"));
        StreamServer streamServer = new StreamServer(server, maxThreads, socket);


        // start it, this method doesn't block
        streamServer.start();

        System.in.read();
        try {
            streamServer.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
