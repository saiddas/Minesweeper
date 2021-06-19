package mineSweeperGame;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

class MineSweeperGUI extends JPanel {
	private MineGrid grid;
	
	public MineSweeperGUI(int numRows, int numCols, int numMines) {
		
		grid = new MineGrid(numRows, numCols, numMines);
		
		setLayout(new GridLayout(numRows, numCols));
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				JButton button = new JButton();
				add(button);
				button.setBackground(Color.LIGHT_GRAY);
				button.setBorder(new BevelBorder(BevelBorder.RAISED));
				button.addActionListener(new ButtonHandler(i, j, grid));
			}
		}
	}
}