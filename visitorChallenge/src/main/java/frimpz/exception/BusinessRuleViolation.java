package frimpz.exception;


public class BusinessRuleViolation extends BusinessException {

    private static final long serialVersionUID = 1L;

    public static enum BusinessRule {
        FEE_TOO_SMALL,
        TOO_LATE_TO_PAY
    }
    
    private BusinessRule businessRuleViolated;
    
    private Object causer;
    
    public BusinessRuleViolation(String message) {
        super(message);
    }

    public BusinessRuleViolation(String message, BusinessRule businessRule,
            Object causer) {
        super(message);
        this.businessRuleViolated = businessRule;
        this.causer = causer;
    }
    @Override
    public String getErrorName() {
        return super.getErrorName() + "." + businessRuleViolated;
    }
    public Object getCauser() {
        return causer;
    }
    public BusinessRule getBusinessRuleViolated() {
        return businessRuleViolated;
    }

    @Override
    public void accept(ExceptionHandlerVisitorInterface exceptionHandler) {
        exceptionHandler.visit(this);
    }
}
