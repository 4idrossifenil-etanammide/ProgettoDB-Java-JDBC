package main.login;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Frame extends JFrame{

	private static Frame istanza;
	JPanel p;
	ErrorLabel el;
	
	private Frame() {
		super("Centro Giustizia");
		
		this.p = new JPanel();
		this.p.setBackground(Color.LIGHT_GRAY);
		this.p.setLayout(new GridBagLayout());
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		Title title = new Title();
		
		RoundedTextField r1 = new RoundedTextField("Inserisci utente...");
		RoundedTextField r2 = new RoundedTextField("Inserisci password...");
		RoundedTextField r3 = new RoundedTextField("Inserisci database...");
		
		r1.addNearTextField(r3, r2);
		r2.addNearTextField(r1, r3);
		r3.addNearTextField(r2, r1);
		
		EnterButton rb = new EnterButton("Invio", 20, 10, r1, r2, r3);
		
		this.el = new ErrorLabel();
		r3.setFinalField(true, rb);
		
 		GridBagConstraints gbc = new GridBagConstraints();
 		gbc.insets = new Insets(5,5,5,5);
 		
 		gbc.gridx = 0;
 		gbc.gridy = 0;
 		this.p.add(title, gbc);
 		
 		gbc.gridx = 0;
 		gbc.gridy = 1;
 		this.p.add(r1, gbc);
 		
 		gbc.gridx = 0;
 		gbc.gridy = 2;
 		this.p.add(r2, gbc);
 		
 		gbc.gridx = 0;
 		gbc.gridy = 3;
 		this.p.add(r3, gbc);
 		
 		gbc.gridy = 4;
 		this.p.add(rb, gbc);
 		
 		gbc.gridy = 5;
 		this.p.add(el, gbc);
 		
 		this.add(p);
		this.setVisible(true);
	}
	
	public static Frame getIstance() {
		if(istanza == null)
			istanza = new Frame();
		return istanza;
	}
	
	public void removeMainPanel() {
		this.remove(p);
		this.repaint();
	}
	
	public void setErrorLabel(String errorString) {
		el.setText(errorString);
	}
}
