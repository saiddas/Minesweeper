package mineSweeperGame;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

class MineSweeperGUI extends JPanel {
	private MineGrid grid;
	private JButton[][] buttons;
	final Icon[] ICONS = new ImageIcon[14];
	
	public MineSweeperGUI(int numRows, int numCols, int numMines) {
		
		grid = new MineGrid(numRows, numCols, numMines);
		buttons = new JButton[numRows][numCols];
		setICONS();
		
		setLayout(new GridLayout(numRows, numCols));
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				JButton button = new JButton();
				add(button);
				buttons[i][j] = button;
				button.setBackground(Color.LIGHT_GRAY);
				button.setIcon(getIconAt(9));
				button.addMouseListener(new ButtonHandler(i, j, grid, this));
			}
		}
	}
	
	JButton[][] getButtons() {
		return this.buttons;
	}
	
	
	//ICONS
	Icon getIconAt(int i) {
		return ICONS[i];
	}
	
	Icon[] getICONS() {
		return ICONS;
	}
	
	private void setICONS() {
		ICONS[0] = new ImageIcon("src/iconSource/zero.jpg");
		ICONS[1] = new ImageIcon("src/iconSource/one.jpg");
		ICONS[2] = new ImageIcon("src/iconSource/two.jpg");
		ICONS[3] = new ImageIcon("src/iconSource/three.jpg");
		ICONS[4] = new ImageIcon("src/iconSource/four.jpg");
		ICONS[5] = new ImageIcon("src/iconSource/five.jpg");
		ICONS[6] = new ImageIcon("src/iconSource/six.jpg");
		ICONS[7] = new ImageIcon("src/iconSource/seven.jpg");
		ICONS[8] = new ImageIcon("src/iconSource/eight.jpg");
		ICONS[9] = new ImageIcon("src/iconSource/unopened.jpg");
		ICONS[10] = new ImageIcon("src/iconSource/flag.jpg");
		ICONS[11] = new ImageIcon("src/iconSource/click.jpg");
		ICONS[12] = new ImageIcon("src/iconSource/lost.jpg");
		ICONS[13] = new ImageIcon("src/iconSource/wrongMine.jpg");
	}
}