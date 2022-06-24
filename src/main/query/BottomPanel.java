package main.query;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class BottomPanel extends JScrollPane{
	
	public BottomPanel() {
		this.getViewport().add(ResultPanel.getIstance());
	}
	
}
