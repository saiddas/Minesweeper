package mineSweeperGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

class ButtonHandler implements ActionListener  {
	private int row, col;
	private MineGrid grid;
	private int clickSource;
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
				JOptionPane.showMessageDialog(null, "OOOPS!!");
				JButton button = (JButton) event.getSource();
				button.setIcon(new ImageIcon("src/iconSource/thatMine.jpg"));
				openMines();
			}
			else {
				if (event.getSource() instanceof JButton) {
					JButton button = (JButton) event.getSource();
					button.setText(String.valueOf(grid.getCellContent(row, col)));
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

	private void openMines() {
		// TODO Auto-generated method stub
		
	}
}