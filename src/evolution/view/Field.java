package evolution.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

import evolution.model.Animal;

public class Field extends JComponent
{
	Field()
	{
		animals = new ArrayList<>();
	}
	
	public void add(Animal a)
	{
		animals.add(new AnimalGraphic(a));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (AnimalGraphic a : animals)
		{
			a.draw(g2);
		}
	}
	
	private ArrayList<AnimalGraphic> animals;
}
