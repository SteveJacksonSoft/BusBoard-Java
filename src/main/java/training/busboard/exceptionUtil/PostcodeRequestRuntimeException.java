package training.busboard.exceptionUtil;

import javax.ws.rs.core.Response;

public class PostcodeRequestRuntimeException extends RuntimeException {
    private Response response;

    public PostcodeRequestRuntimeException(Throwable cause) {
        super(cause);
    }
}
