package frimpz;

import frimpz.exception.*;
import frimpz.exception.AuthorizationException.Type;
import frimpz.exception.BusinessRuleViolation.BusinessRule;


public class Main {
	
	
	public static void madMethod() throws AuthorizationException, BusinessRuleViolation, DuplicateEntityViolation, SearchException, SystemError {
		double seed = Math.random();
		if (seed < 0.15) {
			throw new AuthorizationException(Type.ACCESS_DENIED, new Exception("User crab not allowed"));
		} else if (seed < 0.3) {
			throw new DuplicateEntityViolation("Duplicate issue", new NamedEntity());
		} else if (seed < 0.45) {
			throw new BusinessRuleViolation("Holy crab",BusinessRule.FEE_TOO_SMALL, new NamedEntity());
		} else if (seed < 0.6) {
			throw new SearchException("Holy moly query");
		} else if (seed < 0.75) {
			throw new SystemError("Gosh",new Exception("Crab error"), frimpz.exception.SystemError.Type.TRANSIENT_INSTANCE);
		} 
		System.out.println("Yupee");
	}
	
	/**
	 * Can I simplify this somehow via Visitor?
	 * 
	 * You can expect only there exceptions to show up
	 *  
	 * @param args
	 */
	/*public static void main(String[] args) {
		try {
			madMethod();
		} catch (AuthorizationException e) {
			System.err.println(e.getErrorName() + " " + e.getType());
			e.printStackTrace();
		} catch (BusinessRuleViolation e) {
			System.err.println(e.getErrorName() + " " + e.getBusinessRuleViolated() + " " + e.getCauser().getClass().getSimpleName());
			e.printStackTrace();
		} catch (DuplicateEntityViolation e) {
			System.err.println(e.getErrorName() + " " + e.getDuplicatedEntity().getName());
			e.printStackTrace();
		} catch (SearchException e) {
			System.err.println(e.getErrorName() + " " + e.getMessage());
			e.printStackTrace();
		} catch (SystemError e) {
			System.err.println("Unexpected error" + e.getErrorName() + " " + e.getType());
			e.printStackTrace();
		}
	}*/
	public static void main(String[] args) {
		try {
			madMethod();
		} catch (ServiceException e) {
			//ExceptionHandler.handle(e)
			e.accept(new ExceptionHandlerVisitor());
		}
	}
}
