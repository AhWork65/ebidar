package ebidar.com.minioms.exception;

public class NotValidException extends Exception {
    public NotValidException() {
        super();
    }

    public NotValidException(String message) {
        super(message);
    }

    public NotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}