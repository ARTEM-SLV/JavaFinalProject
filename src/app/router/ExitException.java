package app.router;

public class ExitException extends Exception {
    public ExitException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}