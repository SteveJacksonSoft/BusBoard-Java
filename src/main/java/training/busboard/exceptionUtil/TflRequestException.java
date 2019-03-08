package training.busboard.exceptionUtil;

import javax.ws.rs.core.Response;

public class TflRequestException extends ApiRequestException {
    public TflRequestException() {
    }

    public TflRequestException(String message, Response response) {
        super(message, response);
    }

    public TflRequestException(String message) {
        super(message);
    }

    public TflRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public TflRequestException(Throwable cause) {
        super(cause);
    }

    public TflRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
