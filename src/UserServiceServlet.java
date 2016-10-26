import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcServer;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by maciej on 26.10.16.
 */
public class UserServiceServlet extends HttpServlet {

    private UserService userService;
    private JsonRpcServer jsonRpcServer;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            jsonRpcServer.handle(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(ServletConfig config) {
        this.userService = new UserServiceImpl();
        this.jsonRpcServer = new JsonRpcServer((ObjectMapper) this.userService, UserService.class);
    }

}
