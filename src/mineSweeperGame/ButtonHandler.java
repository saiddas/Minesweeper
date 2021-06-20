package mineSweeperGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

class ButtonHandler extends MouseAdapter  {
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

	@Override
	public void mousePressed(MouseEvent event) {
		if (clickSource == 0) {
			if (event.getButton() == MouseEvent.BUTTON1)  {
				if(grid.isMINE(row, col)) {
					openMines();
				}
				else {
					if (event.getSource() instanceof JButton) {
						if (grid.getCellContent(row, col) == 0) {
							openZeroes(row, col);
						}
						else {
							openButton();
						}
					}
				}
			}
			else if (event.getButton() == MouseEvent.BUTTON3) {
				flagButton();
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

	private void flagButton() {
		if (!grid.isOpened(row, col)) {
			if (!grid.isFlagged(row, col)) {
				panel.getButtons()[row][col].setIcon(panel.getIconAt(10));
				grid.flagCell(row, col);
				checkWinStatus();
			}
			else {
				panel.getButtons()[row][col].setIcon(panel.getIconAt(9));
				grid.unflagCell(row, col);
			}
		}
	}

	private void checkWinStatus() {
		if (grid.getTrueFlaggedCellCount() == grid.getMineCount()) {
			JOptionPane.showMessageDialog(null, "You're a genius.");
		}
	}

	private void openZeroes(int r, int c) {
		openButton(r, c);
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!(i == 0 && j == 0) && grid.isInsideGrid(r+i, c+j)) {
					if (grid.getCellContent(r+i, c+j) == 0) {
						openZeroes(r+i, c+j);
					}
					openButton(r+i, c+j);
				}
			}
		}
	}

	private void openButton() {
		if (!grid.isOpened(row, col) && !grid.isFlagged(row, col)) {
			panel.getButtons()[row][col].setIcon(panel.getIconAt(grid.getCellContent(row, col)));
			grid.openCell(row, col);
		}
	}
	
	private void openButton(int r, int c) {
		if (!grid.isOpened(r, c) && !grid.isFlagged(r, c)) {
			panel.getButtons()[r][c].setIcon(panel.getIconAt(grid.getCellContent(r, c)));
			grid.openCell(r, c);
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
				else if (grid.isFlagged(i, j) && grid.isTrueFlagged(i, j)) {
					panel.getButtons()[i][j].setDisabledIcon(panel.getIconAt(10));
				}
				else if (grid.isFlagged(i, j) && !grid.isTrueFlagged(i, j)) {
					panel.getButtons()[i][j].setDisabledIcon(panel.getIconAt(13));
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