package evolution.model;

import java.util.LinkedList;
import java.util.Random;

enum HerbivoreState {GRAZING, ROAMING, RUNNING};

public class Herbivore extends Animal
{
	public Herbivore(int m)
	{
		super(m);	
		state = HerbivoreState.GRAZING;
		targetX = getX(); targetY = getY();
	}
	
	public Herbivore(int m, int x, int y)
	{
		super(m, x, y);
		state = HerbivoreState.GRAZING;
		targetX = getX(); targetY = getY();
	}
	
	public void action(LinkedList<Animal> visibleAnimals)
	{
		if(!visibleAnimals.isEmpty())
			state = HerbivoreState.RUNNING;
		else if(getFood() < getMaxFood() * .25)
			state = HerbivoreState.GRAZING;
		
		if(state == HerbivoreState.GRAZING)
			eat();
		
		else if(state == HerbivoreState.ROAMING)
		{
			if(getX() == targetX && getY() == targetY)
			{
				Random r = new Random();
				
				targetX = r.nextInt(300)+50;
				targetY = r.nextInt(300)+50;
			}
			
			moveTowards(targetX, targetY);
		}
		
		else if(state == HerbivoreState.RUNNING)
		{
			if(getX() == targetX && getY() == targetY)
			{
				Random r = new Random();
				
				targetX = r.nextInt(300)+50;
				targetY = r.nextInt(300)+50;
			}
			
			moveTowards(targetX, targetY);
			moveTowards(targetX, targetY);
			state = HerbivoreState.ROAMING;
		}
		
	}
	
	public void eat()
	{
		this.changeFood(getMaxFood() / 4);
		
		if(getFood() == getMaxFood())
			state = HerbivoreState.ROAMING;
		
	}
	
	private HerbivoreState state;
	private int targetX; private int targetY;
	
}
