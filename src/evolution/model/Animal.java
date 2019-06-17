package evolution.model;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

public abstract class Animal
{
	public static final int ANIMAL_DIAMETER = 20;
	
	public Animal(int m)
	{
		food = 0; 
		movement = m;
		x = 0; y = 0;
		vision = 60;
	}
	
	public Animal(int m, int x, int y)
	{
		food = 0; 
		movement = m;
		this.x = x; this.y = x;
		vision = 60;
	}
	
	public abstract void action();
	
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
	}
	
	public void move()
	{
		x = x + movement;
	}
	
	public int getFood()
	{
		return food;
	}

	public void setFood(int food)
	{
		this.food = food;
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
	
	
	private int food;
	private int maxFood;
	private int movement; 
	private int vision;
	private int x; private int y;
}

