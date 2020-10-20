package frimpz.exception;


public class AuthorizationException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public enum Type {
        USER_NOT_LOGGED_IN, ACCESS_DENIED
    }

    private Type type;

    public AuthorizationException(Type errorType,
            Throwable aThrow) {
        super(errorType.toString(), aThrow);
        type = errorType;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String getErrorName() {
        return super.getErrorName() + "." + type;
    }

    @Override
    public void accept(ExceptionHandlerVisitorInterface exceptionHandler) {
        exceptionHandler.visit(this);
    }
}
