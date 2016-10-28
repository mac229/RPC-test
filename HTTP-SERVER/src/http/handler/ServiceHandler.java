package http.handler;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.googlecode.jsonrpc4j.ErrorResolver;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.googlecode.jsonrpc4j.ProxyUtil;
import com.googlecode.jsonrpc4j.Util;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import http.service.BankService;
import http.service.EchoServiceImpl;
import http.service.EchoServiceInterface;
import http.service.IBankService;
import sun.misc.IOUtils;


import java.io.*;
import java.net.HttpURLConnection;


public class ServiceHandler implements com.sun.net.httpserver.HttpHandler {

    private EchoServiceInterface echoService;
    private IBankService bankService;
    private JsonRpcServer jsonRpcServer;

    public ServiceHandler(){
        super();
        this.bankService = new BankService();
        this.echoService = new EchoServiceImpl();

        Object compositeService = ProxyUtil.createCompositeServiceProxy(
                this.getClass().getClassLoader(),
                new Object[] { this.bankService, this.echoService},
                new Class<?>[] { IBankService.class, EchoServiceInterface.class},
                true);
        ObjectMapper om = new ObjectMapper();
       // om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        this.jsonRpcServer = new JsonRpcServer(compositeService);
    }

    @Override
    public void handle(HttpExchange exchange) {
        System.out.println("Handle");
        if (exchange.getRequestMethod().equals("POST")) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            OutputStream resultStream = null;
            try {
                Headers responseHeaders = exchange.getResponseHeaders();
                responseHeaders.set("Content-Type", "application/json");
               // os = exchange.getResponseBody();
                jsonRpcServer.handleRequest(exchange.getRequestBody(), os);

               if (getErrorCode(os) == -32601)
                   exchange.sendResponseHeaders(404, 0);
                else
                   exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);


                exchange.getRequestBody().close();
                resultStream = exchange.getResponseBody();
                os.writeTo(resultStream);
                resultStream = exchange.getResponseBody();
                resultStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                close(resultStream);
            }
        }
    }

    private void close(Closeable stream){
        if (stream != null)
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private int getErrorCode(ByteArrayOutputStream stream) throws IOException {
        JsonNode error = http.util.Util.error(stream);
        if (error != null) {
            error = http.util.Util.errorCode(error);
            return error.intValue();
        }
        return 0;
    }
}
