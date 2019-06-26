package evolution.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import evolution.app.App;

enum State {HUNTING, CHASING};

public class Carnivore extends Animal
{
	private static int BUFFER = 50;
	
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
			
			targetX = r.nextInt(App.TOTAL_WIDTH - 2 * BUFFER)+BUFFER;
			targetY = r.nextInt(App.TOTAL_HEIGHT- 2 * BUFFER)+BUFFER;
		}
		
		moveTowards(targetX, targetY);
	}
	
	private State state;
	private int targetX; private int targetY;
	
	
}
