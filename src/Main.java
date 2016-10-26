import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.googlecode.jsonrpc4j.ProxyUtil;
import com.googlecode.jsonrpc4j.StreamServer;
import org.apache.log4j.BasicConfigurator;

import javax.net.ServerSocketFactory;
import javax.servlet.Servlet;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class Main {


    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        BasicConfigurator.configure();

        UserService userService = new UserServiceImpl();

        JsonRpcServer server = new JsonRpcServer(new ObjectMapper(), userService, UserService.class);

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
