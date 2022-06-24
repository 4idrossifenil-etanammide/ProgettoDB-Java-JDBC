package main.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.query.SplitPanel;
import main.query.TopPanel;
import main.queryProcessing.Connector;
import main.utils.RoundedButton;

public class EnterButton extends RoundedButton implements ActionListener{
	
	RoundedTextField r1;
	RoundedTextField r2;
	RoundedTextField r3;
	
	public EnterButton(String name, int x, int y, RoundedTextField r1, RoundedTextField r2, RoundedTextField r3) {
		super(name, x, y);
		
		addActionListener(this);
		
		this.r1 = r1;
		this.r2 = r2;
		this.r3 = r3;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controllaInput();
	}
	
	public void controllaInput() {
		
		Frame f = Frame.getIstance();
		
		String username = r1.getText();
		String password = r2.getText();
		String database = r3.getText();
		if(username.isEmpty() || password.isEmpty() || database.isEmpty()) {
			f.setErrorLabel("Tutti i campi devono essere completati!");
			return;
		}
		
		String errorMessage = Connector.start(database,username,password);
		if(errorMessage.isEmpty())
			newPanel(f);
		else
			f.setErrorLabel(errorMessage);
	}
	
	private void newPanel(Frame f) {
		SplitPanel sp = new SplitPanel();
		
		f.removeMainPanel();
		f.add(sp);
		((TopPanel)sp.getComponents()[0]).getComponents()[0].requestFocus();
		f.validate();
	}
}
