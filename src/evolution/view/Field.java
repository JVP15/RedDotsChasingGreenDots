package evolution.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JComponent;

import evolution.model.Animal;
import evolution.model.Carnivore;
import evolution.model.Herbivore;

public class Field extends JComponent
{
	Field()
	{
		animals = new ArrayList<>();
	}
	
	public void add(Animal a)
	{
		animals.add(a);
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
		for(Animal a: animals)
		{
			a.action();
		}
	}
	
	private ArrayList<Animal> animals;
	
	private void drawAnimal(Animal animal, Graphics2D g2)
	{
		Color color;
		if( animal.getClass() == Carnivore.class)
			color = Color.RED;
		else
			color = Color.GREEN;
		
		g2.setColor(color);
		g2.fill(new Ellipse2D.Double(animal.getX() - Animal.ANIMAL_DIAMETER / 2, animal.getY() - Animal.ANIMAL_DIAMETER / 2, Animal.ANIMAL_DIAMETER, Animal.ANIMAL_DIAMETER));

		Color transparentColor = new Color(color.getRed(), color.getBlue(), color.getGreen(), 60);
		g2.setColor(transparentColor);
		g2.fill(new Ellipse2D.Double(animal.getX() - animal.getVision() / 2, animal.getY() - animal.getVision() / 2, animal.getVision(), animal.getVision()));
	}
	
	
}
