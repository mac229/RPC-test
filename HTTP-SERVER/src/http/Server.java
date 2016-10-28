package http;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.sun.net.httpserver.HttpServer;
import http.service.EchoServiceInterface;
import http.handler.ServiceHandler;
import http.service.EchoServiceImpl;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.net.InetSocketAddress;

public  class Server {

    public static void main(String[] args){
        BasicConfigurator.configure();
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(1234), 0);
            ServiceHandler handler = new ServiceHandler();
            httpServer.createContext("/rpc", handler);
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
