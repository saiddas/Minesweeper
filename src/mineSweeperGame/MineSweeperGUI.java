package mineSweeperGame;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

class MineSweeperGUI extends JPanel {
	private MineGrid grid;
	private JButton[][] buttons;
	
	public MineSweeperGUI(int numRows, int numCols, int numMines) {
		
		grid = new MineGrid(numRows, numCols, numMines);
		buttons = new JButton[numRows][numCols];
		
		setLayout(new GridLayout(numRows, numCols));
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				JButton button = new JButton();
				add(button);
				buttons[i][j] = button;
				button.setBackground(Color.LIGHT_GRAY);
				button.setBorder(new BevelBorder(BevelBorder.RAISED));
				button.addActionListener(new ButtonHandler(i, j, grid, this));
			}
		}
	}
	
	JButton[][] getButtons() {
		return this.buttons;
	}
}