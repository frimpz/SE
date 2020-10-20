package frimpz.exception;

public interface ExceptionHandlerVisitorInterface {

    /**
     * This class is the visitor interface
     * @param authorizationException
     */

    public void visit(AuthorizationException authorizationException);
    public void visit(BusinessRuleViolation businessRuleViolation);
    public void visit(DuplicateEntityViolation duplicateEntityViolation);
    public void visit(SearchException searchException);
    public void visit(SystemError systemError);
}
