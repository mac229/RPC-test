package http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import http.service.Account;
import org.apache.log4j.BasicConfigurator;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Marcin on 27.10.2016.
 */
public class Client {
    public static void main(String[] args) throws MalformedURLException {
        BasicConfigurator.configure();
        JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://localhost:1234/rpc"));

        try {
            client.invoke("createAccount", new Object[]{new Account("asd", 12.2)});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
