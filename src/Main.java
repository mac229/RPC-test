import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Main {

    static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("Hello World!");
        BasicConfigurator.configure();
        JsonRpcServer server = new JsonRpcServer(new ObjectMapper(), new UserServiceImpl(), UserService.class);
        server.
    }
}
