package mvc.example;

import java.awt.TextField;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ItemListener;


public class Controller implements ActionListener,KeyListener,ItemListener {

	private Model model;
	private View view;

	private int localValue = 1;
	
	public Controller() {	
		System.out.println ("Controller()");
	} 

	public void actionPerformed(java.awt.event.ActionEvent e){

		if("Increase".equals(e.getActionCommand())) {
			model.incrementValue();
		}
		if("Decrease".equals(e.getActionCommand())) {
			model.decrementValue();
		}
		if("Error".equals(e.getActionCommand())) {
			view.setError("Error message: #"+localValue++);
		}
		if("Reset".equals(e.getActionCommand())) {
			view.setValue(model.getValue()+"");
		}


	}

	public void addModel(Model m){
		System.out.println("Controller: adding model");
		this.model = m;
	} 

	public void addView(View v){
		System.out.println("Controller: adding view");
		this.view = v;
	} 

	public void initModel(int x){
		model.setValue(x);
	}

	@Override
	public void keyTyped(KeyEvent e) {


	}

	@Override
	public void keyPressed(KeyEvent e) {


	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println ("Controller: Event " + e.getComponent().getName());

		if("name".equals(e.getComponent().getName())) {
			model.setName(((TextField)e.getComponent()).getText());
		}
		if("dob".equals(e.getComponent().getName())) {
			model.setDOB(((TextField)e.getComponent()).getText());
		}
		if("email".equals(e.getComponent().getName())) {
			model.setEmail(((TextField)e.getComponent()).getText());
		}
		if("oldInputField".equals(e.getComponent().getName())) {
			System.out.println(e.getComponent().getName());
			if(isInteger(((TextField)e.getComponent()).getText())) {
				model.setValue(Integer.parseInt(((TextField) e.getComponent()).getText()));
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		model.setInstitution(String.valueOf(e.getItem()));
		System.out.println(e.getItem());
	}

	//Method is used to check if the input in the previous textfield is still an integer
	public boolean isInteger(String value){
		try {
			Integer.parseInt(value);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
}
