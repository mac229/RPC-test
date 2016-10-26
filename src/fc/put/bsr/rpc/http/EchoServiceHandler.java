package fc.put.bsr.rpc.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;


import java.io.*;
import java.net.HttpURLConnection;


public class EchoServiceHandler implements com.sun.net.httpserver.HttpHandler {

    private EchoService echoService;
    private JsonRpcServer jsonRpcServer;

    public void init() {
        this.echoService = new EchoServiceImpl();
        this.jsonRpcServer = new JsonRpcServer(new ObjectMapper(), this.echoService, EchoService.class);
    }

    private void close(Closeable stream){
        if (stream != null)
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void handle(HttpExchange exchange) {
        System.out.println("Handle");
        if (exchange.getRequestMethod().equals("POST")) {
            OutputStream os = null;
            try {
                Headers responseHeaders = exchange.getResponseHeaders();
                responseHeaders.set("Content-Type", "application/json");

                os = exchange.getResponseBody();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                System.out.println(jsonRpcServer.handleRequest(exchange.getRequestBody(), os));
                exchange.getRequestBody().close();
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                close(os);
            }
        }
    }
}
