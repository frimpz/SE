package frimpz.exception;


public abstract class ServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorName(){
    	return this.getClass().getName();
    }


    public void accept(ExceptionHandlerVisitorInterface exceptionHandler) {

    }
}
