package mineSweeperGame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MineSweeper {
	private static final int NUM_MINES = 100;
	private static final int SIZE = 20;
	
	public static void main(String[] args) {
		 JFrame frame = new JFrame("Mine Sweeper | # of mines: " + NUM_MINES);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 MenuGUI menu = new MenuGUI();
		 JPanel backgroundPanel = new JPanel();
		 JPanel topPanel = new JPanel();
		 MineSweeperGUI mainPanel =new MineSweeperGUI(SIZE, SIZE, NUM_MINES);
		
		 backgroundPanel.setLayout(null);
		 backgroundPanel.add(topPanel);
		 backgroundPanel.add(mainPanel);
		 topPanel.setBounds(10, 10, 500, 30);
		 mainPanel.setBounds(10, 50, 500, 500);
		 topPanel.add(new JLabel("Label"));
		 
		 frame.setJMenuBar(menu);
		 frame.add(backgroundPanel);
		 
		 frame.setSize(1000, 600);
		 frame.setVisible(true);
	}
}