package evolution.model;

import java.util.Random;

public class Herbivore extends Animal
{
	public Herbivore(int m)
	{
		super(m);	
		state = State.HUNTING;
		targetX = getX(); targetY = getY();
	}
	
	public Herbivore(int m, int x, int y)
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
	
	private State state;
	private int targetX; private int targetY;
}
