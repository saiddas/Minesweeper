package mineSweeperGame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class TopPanel extends JPanel {
	private Chronometer chronometer;
	private MineCounter mineCounter;
	private JButton smiley;
	
	public TopPanel() {
		//Smiley Setup
		smiley = new JButton(new ImageIcon("src/iconSource/happy.jpg"));
		smiley.setPressedIcon(new ImageIcon("src/iconSource/happyPressed.jpg"));
		setLayout(null);
		add(smiley);
		smiley.setBounds(260, 10, 40, 40);
		smiley.addMouseListener(new ButtonHandler(1));
		
	}
}