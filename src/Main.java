import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcServer;
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
        JsonRpcServer server = new JsonRpcServer(new ObjectMapper(), new UserServiceImpl(), UserService.class);

        Servlet servlet = new UserServiceServlet();
        //servlet.init();

        // create the stream server
        int maxThreads = 50;
        int port = 1420;
        InetAddress bindAddress = InetAddress.getByName("localhost");
        //Service client = ProxyUtil.createClientProxy(this.getClass().getClassLoader(), Service.class, jsonRpcClient, socket);
        ServerSocket socket = ServerSocketFactory.getDefault().createServerSocket(1234, 0, InetAddress.getByName("localhost"));
        StreamServer streamServer = new StreamServer(server, maxThreads, socket);

        // start it, this method doesn't block
        streamServer.start();
    }
}
