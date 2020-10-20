package mvc.example;

//Model doesn't know about View or Controller

public class Model extends java.util.Observable {

	private int counter;
	private Person person;


	public Model() {
		System.out.println("Model()");

	}

	public int getValue() {
		return counter;
	}

	public void setValue(int value) {
		this.counter = value;
		doNotify();

	}

	public String getName() {
		return person.getName();
	}

	public void setName(String name) {
		this.person.setName(name);
		doNotify();

	}

	public String getEmail() {
		return person.getEmail();
	}

	public void setEmail(String email) {
		this.person.setEmail(email);
		doNotify();

	}


	public String getDOB() {
		return person.getDOB();
	}

	public void setDOB(String dob) {
		this.person.setDOB(dob);
		doNotify();

	}

	public String getInstitution() {
		return person.getInstitution();
	}

	public void setInstitution(String institution) {
		this.person.setInstitution(institution);
		doNotify();

	}

	public void incrementValue() {
		++counter;
		doNotify();

	}

	public void decrementValue() {
		--counter;
		doNotify();
		
	}

	private void doNotify() {
		System.out.println("Model     : counter = " + counter);
		setChanged();
		// if Push - send counter as part of the message
		//notifyObservers(counter);
		// if Pull, then can use notifyObservers()
		 notifyObservers();
	}


	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}


}
