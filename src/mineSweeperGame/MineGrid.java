package mineSweeperGame;

import java.util.Random;

class MineGrid {
	private int[][] mineInformation;
	private int flaggedCellCount, trueFlaggedCellCount, mineCount;
	private static final int MINE = -1;
	final int FLAGGED = -10;
	final int OPENED = 10;
	//If a button is opened or flagged it's cell content will be updated with adding these variables.
	//This way I can save the memory!
	
	public MineGrid(int numRows, int numCols, int numMines) {
		mineInformation = new int[numRows][numCols];
		initializeCells();
		mineCount = numMines;
	}
	
	boolean isInsideGrid(int i, int j) {
		return (i >= 0 && i < mineInformation.length) && (j >= 0 && j < mineInformation[0].length);
	}
	
	void setMineInformation() {
		for (int i = 0; i < mineInformation.length; i++) {
			for (int j = 0; j < mineInformation[0].length; j++) {
				if (mineInformation[i][j] == MINE) {
					//previous row
					incrementMineCountAt(i-1, j-1);
					incrementMineCountAt(i-1, j);
					incrementMineCountAt(i-1, j+1);
					
					//left & right columns
					incrementMineCountAt(i, j-1);
					incrementMineCountAt(i, j+1);
					
					//next row
					incrementMineCountAt(i+1, j-1);
					incrementMineCountAt(i+1, j);
					incrementMineCountAt(i+1, j+1);
				}
			}
		}
	}

	private void incrementMineCountAt(int i, int j) {
		if (isInsideGrid(i, j) && !isMINE(i, j)) {
			mineInformation[i][j]++;
		}
	}

	boolean isMINE(int i, int j) {
		return mineInformation[i][j] == MINE;
	}

	void placeMines(int numMines, int row, int col) {
		Random random = new Random();
		for (int i = 0; i < numMines; i++) {
			int r = random.nextInt(mineInformation.length);
			int c = random.nextInt(mineInformation[0].length);
			if (mineInformation[r][c] != MINE && !(r==row && c==col)) {
				mineInformation[r][c] = MINE;
			}
			else {
				i--;
			}
		}
	}

	private void initializeCells() {
		for (int i = 0; i < mineInformation.length; i++) {
			for (int j = 0; j < mineInformation[0].length; j++) {
				mineInformation[i][j] = 0;
			}
		}
	}
	
	int[][] getMineInformation() {
		return this.mineInformation;
	}

	int getCellContent(int row, int col) {
		return mineInformation[row][col];
	}
	
	void openCell(int i, int j) {
		mineInformation[i][j] += OPENED;
	}
	
	void flagCell(int i, int j) {
		if(isMINE(i, j)) {
			trueFlaggedCellCount++;
		}
		mineInformation[i][j] += FLAGGED;
		flaggedCellCount++;
	
		MineCounter.setCount(mineCount-flaggedCellCount);
	}
	
	void unflagCell(int i, int j) {
		mineInformation[i][j] -= FLAGGED;
		if (isMINE(i, j)) {
			trueFlaggedCellCount--;
		}
		flaggedCellCount--;
		
		MineCounter.setCount(mineCount-flaggedCellCount);
	}
	
	boolean isOpened(int i, int j) {
		return getCellContent(i, j) > 8;
	}
	
	boolean isTrueFlagged(int i, int j) {
		return (getCellContent(i, j)-FLAGGED == MINE);
	}
	
	boolean isFlagged(int i, int j) {
		return getCellContent(i, j) < MINE;
	}

	int getFlaggedCellCount() {
		return flaggedCellCount;
	}
	
	int getTrueFlaggedCellCount() {
		return trueFlaggedCellCount;
	}

	int getMineCount() {
		return this.mineCount;
	}
}