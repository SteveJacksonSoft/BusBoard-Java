package training.busboard.exceptionUtil;

import javax.ws.rs.core.Response;

public class PostcodeRequestRuntimeException extends RuntimeException {
    private Response response;

    public PostcodeRequestRuntimeException() {
    }

    public PostcodeRequestRuntimeException(String message) {
        super(message);
    }

    public PostcodeRequestRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostcodeRequestRuntimeException(Throwable cause) {
        super(cause);
    }

    public PostcodeRequestRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PostcodeRequestRuntimeException(Response response) {
        this.response = response;
    }

    public PostcodeRequestRuntimeException(String message, Response response) {
        super(message);
        this.response = response;
    }

}
