package fc.put.bsr.rpc.http;

/**
 * Created by Marcin on 26.10.2016.
 */
public class EchoServiceImpl implements EchoService {
    @Override
    public String print() {
        return "Hello World";
    }
}
