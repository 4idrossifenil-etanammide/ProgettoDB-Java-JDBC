package main.query;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.utils.RoundedButton;

public class TopRightPanel extends JPanel{
	
	private static TopRightPanel istanza;
	
	private TopRightPanel() {
		
		setBackground(Color.LIGHT_GRAY);
		
		setLayout(new GridBagLayout());
		
		ExecuteButton eb = ExecuteButton.getIstance("Esegui", 20, 10);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		add(eb, gbc);
	}
	
	public static TopRightPanel getIstance() {
		if(istanza == null)
			istanza = new TopRightPanel();
		return istanza;
	}

}
