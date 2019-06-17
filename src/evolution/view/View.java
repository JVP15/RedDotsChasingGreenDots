package evolution.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import evolution.model.Animal;
import evolution.model.Carnivore;

public class View
{
	public View() throws InterruptedException
	{
		frame = new JFrame();
		frame.setSize(400,400);
		Carnivore c = new Carnivore(10, 150, 150);
		
		Carnivore c2 = new Carnivore(10, 250, 250);
		Field f = new Field();
		
		f.add(c);
		f.add(c2);
		
		frame.add(f);
		frame.setVisible(true);
		Timer t = new Timer(100, event -> {c.action(); c2.action(); frame.repaint();} );
		t.start();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private JFrame frame;
}
