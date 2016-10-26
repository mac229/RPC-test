package fc.put.bsr.rpc.http;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public  class Server {

    public static void main(String[] args){
        EchoService echoService = new EchoServiceImpl();
        JsonRpcServer server = new JsonRpcServer(new ObjectMapper(), echoService, EchoService.class);

        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(1234), 0);
            EchoServiceHandler handler = new EchoServiceHandler();
            handler.init();
            httpServer.createContext("/rpc", handler);
            httpServer.setExecutor(null); // creates a default executor
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
