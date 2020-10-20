package mvc.example;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.event.*;
import java.util.EventListener;
import java.util.Observable;

public class View implements java.util.Observer {

	private TextField inputTextField;
	private TextField nameTextField;
	private TextField DOBTextField;
	private Choice institutionField;
	private TextField emailTextField;
	private TextField errTextField;
	private Button buttonInc;
	private Button buttonDec;
	private Button buttonErr;
	private Button buttonReset;



	private boolean error = false;

	String[] institutions = { "Baylor University", "Rice University", "Southern Methodist","UT Arlington","UT Austin", "UT Dallas", "UT Tyler" };
	
	public View(int verticalPosition) {
		System.out.println("View()");

		Frame frame = new Frame("simple MVC");
		frame.setLayout(new GridLayout(5,1));
		frame.add(new Label("counter"));

		inputTextField = new TextField();
		inputTextField.setName("oldInputField");
		frame.add(inputTextField);

		Panel myPanel = new Panel();
		myPanel.setLayout(new GridLayout(1,2));

		nameTextField = new TextField();
		nameTextField.setName("name");
		myPanel.add( nameTextField);

		DOBTextField = new TextField();
		DOBTextField.setName("dob");
		myPanel.add(DOBTextField);

		frame.add(myPanel);


		myPanel = new Panel();
		myPanel.setLayout(new GridLayout(1,2));

		emailTextField = new TextField();
		emailTextField.setName("email");
		myPanel.add(emailTextField);



		institutionField = new Choice();
		institutionField.setName("institution");
		//A loop to populate institution combobox
		for (String institution : institutions){
			institutionField.add(institution);
		}
		myPanel.add(institutionField);

		frame.add(myPanel);

		Panel panel = new Panel();
		buttonInc = new Button("Increase");
		panel.add(buttonInc);
		buttonDec = new Button("Decrease");
		panel.add(buttonDec);
		buttonErr = new Button("Error");
		panel.add(buttonErr);
		buttonReset = new Button("Reset");
		panel.add(buttonReset);
		
		frame.add( panel);
		

		frame.addWindowListener(new CloseListener());
		frame.setSize(350, 200);
		frame.setLocation(100, verticalPosition);
		frame.setVisible(true);

	}

	public void update(Observable obs, Object obj) {
		
		
		if (!error) {
			// System.out.println ("View      : Observable is " + obs.getClass() +
			// ", object passed is " + obj.getClass());
	
			// uncomment next line to do Model Pull
			inputTextField.setText("" + ((Model)obs).getValue());
			nameTextField.setText("" + ((Model) obs).getPerson().getName());
			DOBTextField.setText("" + ((Model) obs).getPerson().getDOB());
			emailTextField.setText("" + ((Model) obs).getPerson().getEmail());
			institutionField.select(getIndex("" + ((Model) obs).getPerson().getInstitution()));

			// if Push
			//inputTextField.setText("" + ((Integer) obj).intValue());
		}

	} 

	public void addController(EventListener controller) {
		System.out.println("View      : adding controller");
		buttonInc.addActionListener((ActionListener) controller);
		buttonDec.addActionListener((ActionListener) controller);
		buttonErr.addActionListener((ActionListener) controller);
		inputTextField.addActionListener((ActionListener) controller);
		buttonReset.addActionListener((ActionListener) controller);
		nameTextField.addKeyListener((KeyListener) controller);
		DOBTextField.addKeyListener((KeyListener) controller);
		emailTextField.addKeyListener((KeyListener) controller);
		institutionField.addItemListener((ItemListener) controller);
		inputTextField.addKeyListener((KeyListener) controller);
	} 

	
	public void setValue(String v) {
		error = false;
		buttonInc.setEnabled(true);
		buttonDec.setEnabled(true);
		inputTextField.setText(v);
	}

	public void setError(String v) {
		error = true;
		buttonInc.setEnabled(false);
		buttonDec.setEnabled(false);
		inputTextField.setText(v);
	} 

	public static class CloseListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			System.exit(0);
		} 
	}

	//Method is used to get the index of an institution in the institution array
	public int getIndex(String institution){
		for (int i=0; i<institutions.length;i++){
			if(institution.equals(institutions[i]))
				return i;
		}
		return 0;
	}

} 
