package app.modules.router.exeptions;

public class BackException extends Exception{
    public BackException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
