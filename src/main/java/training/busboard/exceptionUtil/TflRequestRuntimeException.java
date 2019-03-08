package training.busboard.exceptionUtil;

import javax.ws.rs.core.Response;

public class TflRequestRuntimeException extends RuntimeException {
    private Response response;

    public TflRequestRuntimeException() {
    }

    public TflRequestRuntimeException(String message) {
        super(message);
    }

    public TflRequestRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TflRequestRuntimeException(Throwable cause) {
        super(cause);
    }

    public TflRequestRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public TflRequestRuntimeException(Response response) {
        this.response = response;
    }

    public TflRequestRuntimeException(String message, Response response) {
        super(message);
        this.response = response;
    }

}
