package mineSweeperGame;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class ButtonHandler extends MouseAdapter  {
	private int row, col;
	private MineGrid grid;
	private int firstMineX , firstMineY;
	private boolean firstMine = true;
	static boolean won = false;
	static boolean lost = false;
	private MineSweeperGUI panel;
	private int clickSource;
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
				if (!grid.isFlagged(row, col) && !grid.isOpened(row, col) && !won && !lost) {
					TopPanel.setClickHoldFace();
				}
			}
		}
		
		//HERE IS FOR WHEN THE SMILEY IS CLICKED
		else if (clickSource == 1) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				int difficulty = MineSweeper.getDifficulty();
				newGame(difficulty);
			}
		}
	
		//HERE IS FOR WHEN THE MENU --> NEW GAME  IS CLICKED
		else if (clickSource > 1 && clickSource < 5) {
			int difficulty;
			if (clickSource == 2) {
				difficulty = 0;
			}
			else if (clickSource == 3) {
				difficulty = 1;
			}
			else {
				difficulty = 2;
			}
			newGame(difficulty);
		}
		//HERE IS FOR WHEN THE MENU --> HELP IS CLICKED
		else if (clickSource == 5 || clickSource == 6) {
			if (clickSource == 5) {
				JOptionPane.showMessageDialog(null, "Click a cell to open it. A cell's number show how many mines are nearby."
																		+ "\nIf you click on a mined cell you'll die. Otherwise you will open other cell(s)"
																		+ "\nFlag the cells you think are mines. You can flag by right clicking on a cell."
																		+ "\nIf you manage to flag all mines or open all non-mine cells you will win."
																		+ "\nIf you are unsure to flag a mine, you can question-marl that cell by middle clicking."
																		+ "\nBe careful not to flag a normal cell, if there is more flag than mines you will not win.");
			}
			else {
				JTextArea jtext = new JTextArea("Find the code of this game at:\ngithub.com/saiddas/Minesweeper");
				jtext.setEditable(false);
				jtext.setBackground(null);
				JOptionPane.showMessageDialog(null, jtext,"Source Code", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else {
			System.out.println("ERROR YOU SHOULD NOT HAVE SEEN THIS LINE!");
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		if (clickSource == 0) {
			if (event.getButton() == MouseEvent.BUTTON1)  {
				
				if (MineSweeperGUI.getFirstClick()) {
					grid.placeMines(grid.getMineCount(), row, col);
					grid.setMineInformation();
					MineSweeperGUI.setFirstClick(false);
				
				} else {
					if(grid.isMINE(row, col)) {
						openMines();		//Opening a mine cell.
					}
					else {
						if (event.getSource() instanceof JButton) {
							if (grid.getCellContent(row, col) == 0) {
								openZeroes(row, col); //Opening a zero cell.
							}
							else {
								openButton(); //Opening a normal cell.
							}
						}
					}
				}
			}
			else if (event.getButton() == MouseEvent.BUTTON3) {
				flagButton(); //Flagging a cell.
			}
			else {
				questionmarkButton();
			}
		} 
	}
	
	private void questionmarkButton() {
		if (!grid.isOpened(row, col) && !lost && !won) {
			if (!grid.isFlagged(row, col)) {
				panel.getButtons()[row][col].setIcon(panel.getIconAt(14));
				panel.getButtons()[row][col].setPressedIcon(panel.getIconAt(15));
				grid.flagCell(row, col);
				checkWinStatus();
			}
			else {
				panel.getButtons()[row][col].setIcon(panel.getIconAt(9));
				panel.getButtons()[row][col].setPressedIcon(panel.getIconAt(9));
				grid.unflagCell(row, col);
				checkWinStatus();
			}
		}	
	}

	private void newGame(int difficulty) {
		lost = false;
		won = false;
		MineSweeper.startGame(difficulty);
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		if(clickSource == 0) {
			if (!won && !lost) {
				TopPanel.setHappyFace();
			}
		}
	}

	private void flagButton() {
		if (!grid.isOpened(row, col) && !lost && !won) {
			if (!grid.isFlagged(row, col)) {
				panel.getButtons()[row][col].setIcon(panel.getIconAt(10));
				panel.getButtons()[row][col].setPressedIcon(panel.getIconAt(10));

				grid.flagCell(row, col);
				checkWinStatus();
			}
			else {
				panel.getButtons()[row][col].setIcon(panel.getIconAt(9));
				panel.getButtons()[row][col].setPressedIcon(panel.getIconAt(9));
				grid.unflagCell(row, col);
				checkWinStatus();
			}
		}
	}

	private void checkWinStatus() {
		if (grid.getTrueFlaggedCellCount() == grid.getMineCount() && grid.getTrueFlaggedCellCount() == grid.getFlaggedCellCount() && !won && !lost) {
			TopPanel.setWonFace();
			won = true;
			JOptionPane.showMessageDialog(null, "You're a genius.");
			openAll();
		}
	}

	private void openZeroes(int r, int c) {
		if (!won && !lost) {
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
	}

	private void openButton() {
		if (!grid.isOpened(row, col) && !grid.isFlagged(row, col) && !won && !lost) {
			panel.getButtons()[row][col].setIcon(panel.getIconAt(grid.getCellContent(row, col)));
			grid.openCell(row, col);
			checkWinStatus();
		}
	}
	
	private void openButton(int r, int c) {
		if (!grid.isOpened(r, c) && !grid.isFlagged(r, c) && !won && !lost) {
			panel.getButtons()[r][c].setIcon(panel.getIconAt(grid.getCellContent(r, c)));
			grid.openCell(r, c);
			checkWinStatus();
		}
	}

	private void openMines() {
		if (firstMine) {
			openFirstMine();
		}
		TopPanel.setLostFace();
		openAll();
		lost = true;
	}

	private void openFirstMine() {
		firstMineX = row;
		firstMineY = col;
		firstMine = false;
	}

	private void openAll() {
		for (int i = 0; i < grid.getMineInformation().length; i++) {
			for (int j = 0; j < grid.getMineInformation()[0].length; j++) {
				panel.getButtons()[i][j].setEnabled(false);
				if (grid.isMINE(i, j) && !( i==firstMineX  && j==firstMineY  ) && !won) {
					panel.getButtons()[i][j].setDisabledIcon(new ImageIcon("src/iconSource/otherMine.jpg"));
				}
				else if ( i==firstMineX && j == firstMineY  && !won ) {
					panel.getButtons()[i][j].setDisabledIcon(new ImageIcon("src/iconSource/thatMine.jpg"));
				}
				else if (grid.isOpened(i, j)) {
					panel.getButtons()[i][j].setDisabledIcon(panel.getIconAt(grid.getCellContent(i, j)-grid.OPENED));
				}
				else if (grid.isFlagged(i, j) && grid.isTrueFlagged(i, j)) {
					panel.getButtons()[i][j].setDisabledIcon(panel.getIconAt(10));
				}
				else if (grid.isFlagged(i, j) && !grid.isTrueFlagged(i, j) && !won) {
					panel.getButtons()[i][j].setDisabledIcon(panel.getIconAt(13));
				}
				else {
					if(!won) {
						panel.getButtons()[i][j].setDisabledIcon(panel.getIconAt(9));
					}
				}
			}	
		}
	}
}