package ebidar.com.minioms.exception;

public class WalletNoBalanceException extends Exception {
    public WalletNoBalanceException() {
        super();
    }

    public WalletNoBalanceException(String message) {
        super(message);
    }

    public WalletNoBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}