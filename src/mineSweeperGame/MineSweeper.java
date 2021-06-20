package mineSweeperGame;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class MineSweeper {
	private static final int NUM_MINES = 20;
	private static final int SIZE = 20;
	public static JFrame frame;
	
	public static void main(String[] args) {
		 frame = new JFrame("Mine Sweeper | # of mines: " + NUM_MINES);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 MenuGUI menu = new MenuGUI();
		 JPanel backgroundPanel = new JPanel();
		 TopPanel topPanel = new TopPanel();
		 MineSweeperGUI mainPanel =new MineSweeperGUI(SIZE, SIZE, NUM_MINES);
		
		 //Absolute Layouts and backGroundPanel's arrangements.
		 frame.setLayout(null);
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
		 
		 frame.setSize(635, 750);
		 frame.setResizable(false);
		 frame.setVisible(true);
	}
}