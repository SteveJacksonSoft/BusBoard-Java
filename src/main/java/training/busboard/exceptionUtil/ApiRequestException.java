package training.busboard.exceptionUtil;

import javax.ws.rs.core.Response;

public class ApiRequestException extends Exception {
    private Response response;

    public ApiRequestException() {
    }

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiRequestException(Throwable cause) {
        super(cause);
    }

    public ApiRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ApiRequestException(Response response) {
        this.response = response;
    }

    public ApiRequestException(String message, Response response) {
        super(message);
        this.response = response;
    }
}
