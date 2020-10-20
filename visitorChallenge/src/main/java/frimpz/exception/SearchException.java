package frimpz.exception;


public class SearchException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public SearchException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchException(String message) {
        super(message);
    }

    @Override
    public void accept(ExceptionHandlerVisitorInterface exceptionHandler) {
        exceptionHandler.visit(this);
    }
}
