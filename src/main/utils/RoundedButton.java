package main.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class RoundedButton extends JButton{

	private Shape shape;
	
	public RoundedButton(String name, int x, int y) {
		super(name);
		setSize(x,y);
		setOpaque(false);
		setBackground(new Color(150,150,150));
		setFocusPainted(false);
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
}