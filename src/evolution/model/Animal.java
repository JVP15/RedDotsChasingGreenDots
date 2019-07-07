package evolution.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.Icon;

import evolution.mainloop.App;

public abstract class Animal
{
	public static final int ANIMAL_DIAMETER = 20;
	public static int BUFFER = 50;
	
	public Animal(int evolutionPoints)
	{
		Random rand = new Random();
		
		x = rand.nextInt(App.TOTAL_WIDTH - 2 * BUFFER)+BUFFER;
		y = rand.nextInt(App.TOTAL_HEIGHT- 2 * BUFFER)+BUFFER;
		alive = true;
		
		allocateStats(evolutionPoints);
	}
	
	public Animal(int evolutionPoints, int x, int y)
	{
		alive = true;
		this.x = x; this.y = x;
		
		allocateStats(evolutionPoints);
	}
	
	public abstract void action(LinkedList<Animal> visibleAnimals);
	
	
	public void moveTowards(int x1, int y1)
	{
		double diffX = x1 - x; 
		double diffY = y1 - y;
		double tan = Math.sqrt(diffX * diffX + diffY * diffY);
		
		int movX = (int) (movement * diffX / tan);
		int movY = (int) (movement * diffY / tan);
		
		if(Math.abs(diffX) < movX)
			x = x1;
		else
			x = x + movX;
		
		if(Math.abs(diffY) < movY)
			y = y1;
		else
			y = y + movY;
		
		changeFood(-movement / 4);
	}

	public int getFood()
	{
		return food;
	}

	public void setFood(int food)
	{
		this.food = food;
	}
	
	public void changeFood(int food)
	{
		if(this.food + food > maxFood)
			this.food = maxFood;
		else if(this.food + food < 0 - maxFood / 2)
			this.kill();
		else
			this.food += food;
	}

	public int getMovement()
	{
		return movement;
	}

	public void setMovement(int movement)
	{
		this.movement = movement;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getVision()
	{
		return vision;
	}
		
	public int getMaxFood()
	{
		return maxFood;
	}
	
	public int getReation()
	{
		return REACTION;
	}
		
	public boolean isAlive()
	{
		return alive;
	}

	public void kill()
	{
		alive = false;
	}

	public boolean canSee(Animal other)
	{
		if(Math.sqrt( Math.pow( x - other.x, 2) + Math.pow(y - other.y, 2) ) <= vision)
			return true;
		
		return false;
	}
	
	private int food;
	private int maxFood;
	private int movement; 
	private int vision;
	private boolean alive;
	private int x; private int y;
	
	private final int BASE_MOVEMENT = 10;
	private final int BASE_VISION = 180;
	private final int BASE_MAX_FOOD = 1000;
	private final int REACTION = 10 * movement - maxFood;
	
	private void allocateStats(int evolutionPoints)
	{
		Random rand = new Random();
		
		int pointChange = rand.nextInt(evolutionPoints / 2);
		evolutionPoints = evolutionPoints - pointChange;
		vision = (int) (BASE_VISION * 1 + pointChange / 100.0);
		
		pointChange = rand.nextInt((evolutionPoints + pointChange) / 2);
		evolutionPoints = evolutionPoints - pointChange;
		movement = (int) (BASE_MOVEMENT * 1 + pointChange / 100.0);
		
		maxFood =  (int) (BASE_MAX_FOOD * 1 + evolutionPoints / 100.0);
	}
}

