package evolution.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import evolution.mainloop.App;
import evolution.mainloop.Field;
import evolution.model.Animal;
import evolution.model.Carnivore;
import evolution.model.Herbivore;

public class View
{
	public View() throws InterruptedException
	{
		frame = new JFrame();
		frame.setSize(App.TOTAL_WIDTH, App.TOTAL_HEIGHT);
		
		Field f = new Field();
		
		f.add(new Carnivore(500));
		//f.add(new Carnivore(100));
		
		for(int i = 0; i < 5; i++)
			f.add(new Herbivore(200));
		
		frame.add(f);
		frame.setVisible(true);
		Timer t = new Timer(100, event -> {f.gameTick(); frame.repaint();} );
		t.start();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private JFrame frame;
}
