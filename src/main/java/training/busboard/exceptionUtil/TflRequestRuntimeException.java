package training.busboard.exceptionUtil;

import javax.ws.rs.core.Response;

public class TflRequestRuntimeException extends RuntimeException {
    private Response response;

    public TflRequestRuntimeException(Throwable cause) {
        super(cause);
    }

}
