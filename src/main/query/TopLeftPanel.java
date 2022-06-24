package main.query;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class TopLeftPanel extends JTextPane implements KeyListener{
	
	private static TopLeftPanel istanza;
	
	private TopLeftPanel() {
		this.setBackground(Color.LIGHT_GRAY);
		this.addKeyListener(this);
	}
	
	public static TopLeftPanel getIstance() {
		if(istanza == null)
			istanza = new TopLeftPanel();
		return istanza;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode() == 83) && ((e.getModifiersEx() & 128) == 128)) {
            ExecuteButton.getIstance().execute();
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
