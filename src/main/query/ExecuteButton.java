package main.query;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.login.Frame;
import main.queryProcessing.QueryProcessor;
import main.utils.RoundedButton;

public class ExecuteButton extends RoundedButton implements ActionListener{
	
	private QueryProcessor queryProcessor;
	private Frame frame;
	private static ExecuteButton istanza;
	
	private ExecuteButton(String name, int x, int y) {
		super(name, x, y);
		
		this.queryProcessor = new QueryProcessor();
		this.frame = Frame.getIstance();
		addActionListener(this);
	}
	
	public static ExecuteButton getIstance(String name, int x, int y) {
		if(istanza == null)
			istanza = new ExecuteButton(name, x, y);
		return istanza;
	}
	
	public static ExecuteButton getIstance() {
		return istanza;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		execute();
	}
	
	public void execute() {
		ResultPanel bp = ResultPanel.getIstance();
		
		String query = TopLeftPanel.getIstance().getText();
		String strError = queryProcessor.processa(query);
		
		query = query.replaceAll("\n", "\n\t");
		
		if(!strError.isEmpty()) {
			bp.setErrorMessage(strError); 
			return;
		}
		
		String res = "Query:\n\t" + query + "\n\n" + queryProcessor.stampaRisultato();
		System.out.println(res);
		
		
		bp.addText(res, Color.BLACK);	
	}

}
