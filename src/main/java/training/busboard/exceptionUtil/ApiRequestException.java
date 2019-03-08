package training.busboard.exceptionUtil;

import javax.ws.rs.core.Response;

public class ApiRequestException extends Exception {
    private Response response;

    public ApiRequestException(Throwable cause) {
        super(cause);
    }

    public ApiRequestException(String message, Response response) {
        super(message);
        this.response = response;
    }
}
