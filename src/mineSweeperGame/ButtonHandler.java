package mineSweeperGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;

class ButtonHandler implements ActionListener  {
	private int row, col;
	private MineGrid grid;
	private int clickSource;
	private static int firstMineX , firstMineY;
	private static boolean firstMine = true;
	private MineSweeperGUI panel;
	// If click's source is a normal cell clickSource will remain 0
	// for smiley it's 1, for 'new easy game' it's 2, for 'new normal game' it's 3, for 'new hard game' it's 4, for 'game help' it's 5, for 'code help' it's 6.
	
	
	public ButtonHandler(int x, int y, MineGrid g, MineSweeperGUI p) {
		row = x;
		col = y;
		grid = g;
		clickSource = 0;
		panel = p;
	}
	
	public ButtonHandler(int i) {
		this.clickSource = i;
	}

	public void actionPerformed(ActionEvent event) {
		if (clickSource == 0) {
			if(grid.isMINE(row, col)) {
				openMines();
			}
			else {
				if (event.getSource() instanceof JButton) {
					if (grid.getCellContent(row, col) == 0) {
						openZeroes();
					}
					else {
						openButton();
					}
				}
			}
		} 
		
		//HERE IS FOR WHEN THE SMILEY IS CLICKED
		else if (clickSource == 1) {
			
		}
	
		//HERE IS FOR WHEN THE MENU --> NEW GAME  IS CLICKED
		else if (clickSource > 1 && clickSource < 5) {
			
		}
		//HERE IS FOR WHEN THE MENU --> HELP IS CLICKED
		else if (clickSource == 5 || clickSource == 6) {
			
		}
		else {
			System.out.println("ERROR YOU SHOULD NOT HAVE SEEN THIS LINE!");
		}
	}

	private void openZeroes() {
		openButton();
	}

	private void openButton() {
		if (!grid.isOpened(row, col)) {
			panel.getButtons()[row][col].setIcon(panel.getIconAt(grid.getCellContent(row, col)));
			grid.openCell(row, col);
		}
	}

	private void openMines() {
		if (firstMine) {
			openFirstMine();
		}
		
		for (int i = 0; i < grid.getMineInformation().length; i++) {
			for (int j = 0; j < grid.getMineInformation()[0].length; j++) {
				panel.getButtons()[i][j].setEnabled(false);
				if (grid.isMINE(i, j) && !( i==firstMineX  && j==firstMineY  )) {
					panel.getButtons()[i][j].setDisabledIcon(new ImageIcon("src/iconSource/otherMine.jpg"));
				}
				else if ( i==firstMineX && j == firstMineY ) {
					panel.getButtons()[i][j].setDisabledIcon(new ImageIcon("src/iconSource/thatMine.jpg"));
				}
				else if (grid.isOpened(i, j)) {
					panel.getButtons()[i][j].setDisabledIcon(panel.getIconAt(grid.getCellContent(i, j)-grid.OPENED));
				}
				else if (grid.isFlagged(i, j)) {
					panel.getButtons()[i][j].setDisabledIcon(panel.getIconAt(grid.getCellContent(i, j)-grid.FLAGGED));
				}
				else {
					panel.getButtons()[i][j].setDisabledIcon(panel.getIconAt(9));
				}
			}
		}
		
	}

	private void openFirstMine() {
		firstMineX = row;
		firstMineY = col;
		firstMine = false;
	}
}