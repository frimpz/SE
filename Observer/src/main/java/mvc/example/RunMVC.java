package mvc.example;

public class RunMVC {


	private int start_value = 10;	

	public RunMVC() {

		//create Model and View
		Model model 	= new Model();

		View view 	= new View(50);
		View view2 	= new View(250);
		View view3 	= new View(450);
		
		//Add listeners
		model.addObserver(view);
		model.addObserver(view2);
		model.addObserver(view3);
		

		Controller controller = new Controller();
		controller.addModel(model);
		controller.addView(view);


		Controller controller2 = new Controller();
		controller2.addModel(model);
		controller2.addView(view2);

		Controller controller3 = new Controller();
		controller3.addModel(model);
		controller3.addView(view3);

		view.addController(controller);
		view2.addController(controller2);
		view3.addController(controller3);

		Person person = new Person();
		person.setName("Frimpong");
		person.setEmail("boadu@gmail.com");
		person.setInstitution("Baylor");
		person.setDOB("01/01/1990");
		model.setPerson(person);
		model.setValue(start_value);
	} 

} 
