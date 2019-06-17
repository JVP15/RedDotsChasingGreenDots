package evolution.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

import evolution.model.Animal;
import evolution.model.Carnivore;

public class AnimalGraphic
{
	AnimalGraphic(Animal a)
	{
		animal = a;
		if( a.getClass() == Carnivore.class)
			color = Color.RED;
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(color);
		g2.fill(new Ellipse2D.Double(animal.getX() - Animal.ANIMAL_DIAMETER / 2, animal.getY() - Animal.ANIMAL_DIAMETER / 2, Animal.ANIMAL_DIAMETER, Animal.ANIMAL_DIAMETER));

		Color transparentColor = new Color(color.getRed(), color.getBlue(), color.getGreen(), 60);
		g2.setColor(transparentColor);
		g2.fill(new Ellipse2D.Double(animal.getX() - animal.getVision() / 2, animal.getY() - animal.getVision() / 2, animal.getVision(), animal.getVision()));
	}
	
	
	private Animal animal;
	private Color color;
}
