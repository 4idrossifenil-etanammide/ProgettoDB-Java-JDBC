package main.login;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.Shape;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;

public class RoundedTextField extends JTextField implements FocusListener, KeyListener{
	
	private Shape shape;
	private String backgroundText;
	private boolean booleanText = true;
	private boolean finalField = false;
	
	private EnterButton equivalentComponent;
	private RoundedTextField previous;
	private RoundedTextField next;
	
	public RoundedTextField(String backgroundText) {
		super(20);
		setOpaque(false);
		setBackground(new Color(150,150,150));
		this.backgroundText = backgroundText;
		setText(backgroundText);
		super.addFocusListener(this);
		super.addKeyListener(this);
	}
	
	public void addNearTextField(RoundedTextField previous, RoundedTextField next) {
		this.previous = previous;
		this.next = next;
	}
	
	public void setFinalField(boolean finalField, EnterButton equivalentComponent) {
		this.finalField = finalField;
		this.equivalentComponent = equivalentComponent;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        super.paintComponent(g);
    }
	
	@Override
	protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
	
	@Override
	public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
   }

	@Override
	public void focusGained(FocusEvent e) {
		if(this.getText().isEmpty()) {
		      super.setText("");
		      booleanText = false;
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(this.getText().isEmpty()) {
		    super.setText(backgroundText);
		    booleanText = true;
		}
	}
	
	@Override
	public String getText() {
	  return booleanText ? "" : super.getText();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode() + " " + e.getModifiersEx());
		switch (e.getKeyCode()) {
		case 38:
			previous.requestFocus(); break;
		case 10:
			if(finalField && !getText().isEmpty()) {
				equivalentComponent.controllaInput(); break;
			}
		case 40:
			next.requestFocus(); break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
