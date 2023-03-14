package bj.edem.softcare.common;

import org.springframework.http.HttpStatus;

public class AppRequestException extends RuntimeException {

    private HttpStatus status = HttpStatus.NOT_FOUND;

    public AppRequestException() {
        this("Resource not created!");
    }

    public AppRequestException(String entity, Long id) {
        this(String.format("%s{id: %d} not found !!!", entity, id));
    }

    public AppRequestException(String entity, Integer id) {
        this(String.format("%s{id: %d} not found !!!", entity, id));
    }

    public AppRequestException(final String message) {
        super(message);
    }

    public AppRequestException(String message, HttpStatus status) {
        this(message);
        this.status = status;
    }

    public AppRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppRequestException(String path, String message, Throwable cause) {
        super(path + message, cause);
    }

    public AppRequestException(String path, String message) {
        super(path + message);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
