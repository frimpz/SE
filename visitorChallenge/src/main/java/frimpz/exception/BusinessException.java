package frimpz.exception;


public abstract class BusinessException extends ServiceException {

    private static final long serialVersionUID = 8704445053554754870L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
