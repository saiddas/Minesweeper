package mineSweeperGame;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class MineSweeper {
	private static int NUM_MINES;													//Selected Game's mines
	private static final int NUM_MINES_EASY = 20;							//Easy game mine number
	private static final int NUM_MINES_NORMAL = 50;					//Normal game mine number
	private static final int NUM_MINES_HARD = 100;						//Hard game mine number
	private static final int SIZE = 20;
	public static JFrame frame;
	private static JPanel oldBackgroundPanel = null;
	
	public static void main(String[] args) {
		 frame = new JFrame("Mine Sweeper | # of mines: " + NUM_MINES_NORMAL);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setLayout(null);
		 
		 startGame(1);
		 
		 frame.setSize(635, 750);
		 frame.setResizable(false);
		 frame.setVisible(true);

	}
	
	public static void startGame(int i) {
		if (i == 0 ) {
			NUM_MINES = NUM_MINES_EASY;
		} 
		else if (i == 1) {
			NUM_MINES = NUM_MINES_NORMAL;
		}
		else if (i == 2) {
			NUM_MINES = NUM_MINES_HARD;
		}
		else {
			System.out.println("MineSweeper.java is broken!!");
		}
		
		if (oldBackgroundPanel != null) { 
			frame.remove(oldBackgroundPanel);
		}
		
		 MenuGUI menu = new MenuGUI();
		 JPanel backgroundPanel = new JPanel();
		 TopPanel topPanel = new TopPanel();
		 MineSweeperGUI mainPanel =new MineSweeperGUI(SIZE, SIZE, NUM_MINES);
		
		 //Absolute Layouts and backGroundPanel's arrangements.
		 backgroundPanel.setLayout(null);
		 backgroundPanel.add(topPanel);
		 backgroundPanel.add(mainPanel);
		 topPanel.setBounds(10, 10, 600, 60);
		 mainPanel.setBounds(10, 80, 600, 600);
		 
		 //Visual Work.
		 topPanel.add(new JLabel("Label")); // This is for noticing the place of the panel. I'll delete this later.
		 backgroundPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
		 topPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		 mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		 
		 //Frame arrangements.
		 frame.setJMenuBar(menu);
		 frame.add(backgroundPanel);
		 backgroundPanel.setBounds(0, 0, 625, 695);
		 
		 //Background Colors
		 frame.setBackground(Color.LIGHT_GRAY);
		 backgroundPanel.setBackground(Color.LIGHT_GRAY);
		 mainPanel.setBackground(Color.LIGHT_GRAY);
		 topPanel.setBackground(Color.LIGHT_GRAY);
		 
		 oldBackgroundPanel = backgroundPanel;
		 
		 frame.validate();

	}
	

	static int getNUM_MINES() {
		return NUM_MINES;
	}
	
	static int getDifficulty() {
		if (NUM_MINES == NUM_MINES_EASY) {
			return 0;
		}
		else if (NUM_MINES == NUM_MINES_NORMAL) {
			return 1;
		}
		else {
			return 2;
		}
	}
}