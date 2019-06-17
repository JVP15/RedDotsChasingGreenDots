package evolution.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

enum State {HUNTING, CHASING};

public class Carnivore extends Animal
{
	public Carnivore(int m)
	{
		super(m);	
		state = State.HUNTING;
		targetX = getX(); targetY = getY();
	}
	
	public Carnivore(int m, int x, int y)
	{
		super(m, x, y);
		state = State.HUNTING;
		targetX = getX(); targetY = getY();
	}
	
	public void action()
	{
		if(getX() == targetX && getY() == targetY)
		{
			Random r = new Random();
			
			targetX = r.nextInt(300)+50;
			targetY = r.nextInt(300)+50;
		}
		
		moveTowards(targetX, targetY);
	}
	
	public void draw(Graphics2D g2)
	{
		g2.setColor(new Color(255, 0, 0, 60));
		g2.fill(new Ellipse2D.Double(super.getX()-60, super.getY()-60, 120, 120));
		g2.setColor(Color.RED);
		g2.fill(new Ellipse2D.Double(super.getX()-10, super.getY()-10, 20, 20));
	}
	
	private State state;
	private int targetX; private int targetY;
}
