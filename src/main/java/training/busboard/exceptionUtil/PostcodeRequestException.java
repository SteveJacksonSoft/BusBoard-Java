package training.busboard.exceptionUtil;

import javax.ws.rs.core.Response;

public class PostcodeRequestException extends ApiRequestException {

    public PostcodeRequestException() {
    }

    public PostcodeRequestException(String message, Response response) {
        super(message, response);
    }

    public PostcodeRequestException(String message) {
        super(message);
    }

    public PostcodeRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostcodeRequestException(Throwable cause) {
        super(cause);
    }

    public PostcodeRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
