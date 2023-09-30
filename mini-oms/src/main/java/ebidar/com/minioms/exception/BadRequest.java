package ebidar.com.minioms.exception;

public class BadRequest extends Exception {
    public BadRequest() {
        super();
    }

    public BadRequest(String message) {
        super(message);
    }

    public BadRequest(String message, Throwable cause) {
        super(message, cause);
    }
}