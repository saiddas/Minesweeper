package mineSweeperGame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuGUI extends JMenuBar{
	private JMenu game;
	private JMenu help;
	
	public MenuGUI() {
		super();
		game = new JMenu("Game");
		help = new JMenu("Help");
		
		JMenuItem easy = new JMenuItem("New Easy Game");
		JMenuItem normal = new JMenuItem("New Normal Game");
		JMenuItem hard = new JMenuItem("New Hard Game");
		
		JMenuItem gameHelp = new JMenuItem("Game Help");
		JMenuItem codeHelp = new JMenuItem("Source Code Help");
		
		game.add(easy);
		game.add(normal);
		game.add(hard);
		
		help.add(gameHelp);
		help.add(codeHelp);
		
		add(game);
		add(help);
		
		easy.addActionListener(new ButtonHandler(2));
		normal.addActionListener(new ButtonHandler(3));
		hard.addActionListener(new ButtonHandler(4));
		gameHelp.addActionListener(new ButtonHandler(5));
		codeHelp.addActionListener(new ButtonHandler(6));
	}
}
