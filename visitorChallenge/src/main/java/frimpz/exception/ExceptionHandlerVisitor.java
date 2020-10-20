package frimpz.exception;


/**
 * This class is the concrete visitor which implements
 * the operations specified in the visitor interface
 */
public class ExceptionHandlerVisitor implements ExceptionHandlerVisitorInterface{

    public void visit(AuthorizationException authoriztionException) {
        System.err.println(authoriztionException.getErrorName() + " " + authoriztionException.getType());
        authoriztionException.printStackTrace();
    }

    public void visit(BusinessRuleViolation businessRuleViolation) {
        System.err.println(businessRuleViolation.getErrorName() + " " + businessRuleViolation.getBusinessRuleViolated() + " " + businessRuleViolation.getCauser().getClass().getSimpleName());
        businessRuleViolation.printStackTrace();
    }

    public void visit(DuplicateEntityViolation duplicateEntityViolation) {
        System.err.println(duplicateEntityViolation.getErrorName() + " " + duplicateEntityViolation.getDuplicatedEntity().getName());
        duplicateEntityViolation.printStackTrace();
    }

    public void visit(SearchException searchException) {
        System.err.println(searchException.getErrorName() + " " + searchException.getMessage());
        searchException.printStackTrace();
    }


    public void visit(SystemError systemError) {
        System.err.println("Unexpected error" + systemError.getErrorName() + " " + systemError.getType());
        systemError.printStackTrace();
    }



}
