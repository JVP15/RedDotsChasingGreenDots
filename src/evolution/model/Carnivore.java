package evolution.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.Random;

import evolution.mainloop.App;

enum CarnivoreState {HUNTING, CHASING, RESTING};

public class Carnivore extends Animal
{
	
	public Carnivore(int m)
	{
		super(m);	
		setFood(getMaxFood());
		state = CarnivoreState.HUNTING;
		target = null;
		targetX = getX(); targetY = getY();
	}
	
	public Carnivore(int m, int x, int y)
	{
		super(m, x, y);
		state = CarnivoreState.HUNTING;
		targetX = getX(); targetY = getY();
	}
	
	public void action(LinkedList<Animal> visibleAnimals)
	{
		for(Animal a: visibleAnimals)
		{
			if(a.getClass() == Herbivore.class && target == null)
			{
				target = a;
				state = CarnivoreState.CHASING;
				break;
			}	
		}
		
		if(getFood() > getMaxFood() * .75 || (target != null && !this.canSee(target)))
		{
			state = CarnivoreState.HUNTING;
			target = null;
		}
		else if(target == null && getFood() < getMaxFood() * .25)
		{
			state = CarnivoreState.RESTING;
		}
		
		if(state == CarnivoreState.HUNTING)
		{
			if(getX() == targetX && getY() == targetY)
			{
				Random r = new Random();
				
				targetX = r.nextInt(App.TOTAL_WIDTH - 2 * Animal.BUFFER) + Animal.BUFFER;
				targetY = r.nextInt(App.TOTAL_HEIGHT- 2 * Animal.BUFFER) + Animal.BUFFER;
			}
			moveTowards(targetX, targetY);
		}
		
		else if(state == CarnivoreState.CHASING)
		{
			targetX = target.getX();
			targetY = target.getY();
			
			moveTowards(targetX, targetY);
			moveTowards(targetX, targetY);
			
			if(getX() == targetX && getY() == targetY)
			{
				eat(target);
				state = CarnivoreState.HUNTING;
			}
		}
		
	}
	
	private CarnivoreState state;
	private Animal target; 
	private int targetX; private int targetY;

	private void eat(Animal a)
	{
		this.changeFood(a.getMaxFood() / 3);
		a.kill();
		target = null;
	}
	
	
}
