package evolution.mainloop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JComponent;

import evolution.model.Animal;
import evolution.model.Carnivore;
import evolution.model.Herbivore;

public class Field extends JComponent
{
	public Field()
	{
		animals = new LinkedList<>();
	}
	
	public void add(Animal a)
	{
		animals.add(a);
		animals.sort((a1, a2) -> a2.getReation() - a1.getReation());
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Animal a : animals)
		{
			drawAnimal(a, g2);
		}
	}
	
	public void gameTick()
	{
		ListIterator<Animal> it = animals.listIterator();
		
		while(it.hasNext())
		{
			Animal a = it.next();
			
			if(a.isAlive())
				a.action(nearbyAnimals(a));
			else
				it.remove();
		}
	}
	
	private LinkedList<Animal> animals;
	
	private void drawAnimal(Animal animal, Graphics2D g2)
	{
		Color color;
		if( animal.getClass() == Carnivore.class)
			color = Color.RED;
		else
			color = Color.GREEN;
		
		g2.setColor(color);
		g2.fill(new Ellipse2D.Double(animal.getX() - Animal.ANIMAL_DIAMETER / 2, animal.getY() - Animal.ANIMAL_DIAMETER / 2, Animal.ANIMAL_DIAMETER, Animal.ANIMAL_DIAMETER));

		Color transparentColor = new Color(color.getRed(),  color.getGreen(),color.getBlue(), 60);
		g2.setColor(transparentColor);
		g2.fill(new Ellipse2D.Double(animal.getX() - animal.getVision() / 2, animal.getY() - animal.getVision() / 2, animal.getVision(), animal.getVision()));
	}
	
	private LinkedList<Animal> nearbyAnimals(Animal original)
	{
		LinkedList<Animal> visibleAnimals = new LinkedList<>();
		
		for(Animal a: animals)
		{
			if(original.canSee(a) && original != a)
				visibleAnimals.add(a);
		}
		
		return visibleAnimals;
	}
}
