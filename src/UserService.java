import com.googlecode.jsonrpc4j.*;
import fc.put.bsr.rpc.http.CustomException;

/**
 * Created by maciej on 26.10.16.
 */
@JsonRpcService("/jsonrpc")
public interface UserService {

    @JsonRpcMethod("createUser")
    @JsonRpcErrors({ @JsonRpcError(exception = CustomException.class, code = -5678, message = "The message", data = "The data") })
    User createUser(@JsonRpcParam(value = "theUserName") String userName, @JsonRpcParam(value = "firstName") String firstName, @JsonRpcParam(value = "password") String password) throws JsonRpcClientException;

    @JsonRpcMethod("findUserByUserName")
    User findUserByUserName(String userName);

    @JsonRpcMethod("getUserCount")
    int getUserCount();
}
