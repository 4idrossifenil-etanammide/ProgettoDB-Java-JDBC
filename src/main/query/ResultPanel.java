package main.query;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class ResultPanel extends JTextPane{

	StyleContext sc = StyleContext.getDefaultStyleContext();
    AttributeSet aset;
    
    private static ResultPanel istanza; 
	
	private ResultPanel() {
		this.setBackground(Color.LIGHT_GRAY);
		this.setEditable(false);
		this.setFont(new Font("Consolas", Font.PLAIN, 12));
	}
	
	public static ResultPanel getIstance() {
		if(istanza == null)
			istanza = new ResultPanel();
		return istanza;
	}
	
	public void setErrorMessage(String strError) {
		addText(strError, Color.RED);
	}
	
	public void addText(String text, Color c) {
		this.aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
		this.setCharacterAttributes(aset, false);
		this.setText(text);
	}
	
}
