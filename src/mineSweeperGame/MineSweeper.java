package mineSweeperGame;

import javax.swing.JFrame;

public class MineSweeper {
	private static final int NUM_MINES = 100;
	private static final int SIZE = 20;
	
	public static void main(String[] args) {
		 JFrame frame = new JFrame("Mine Sweeper | # of mines: " + NUM_MINES);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 MineSweeperGUI mainPanel =new MineSweeperGUI(SIZE, SIZE, NUM_MINES);
		 MenuGUI menu = new MenuGUI();
		 
		 frame.setJMenuBar(menu);
		 frame.add(mainPanel);
		 
		 frame.setSize(1000, 600);
		 frame.setVisible(true);
	}
}