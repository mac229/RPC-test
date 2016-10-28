package fc.put.bsr.rpc.http;

/**
 * Created by maciej on 28.10.16.
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomException() {
    }

    public CustomException(String msg) {
        super(msg);
    }
}