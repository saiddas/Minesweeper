package mineSweeperGame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TopPanel extends JPanel {
	private Chronometer chronometer;
	private static MineCounter mineCounter;
	private static JButton smiley;
	
	public TopPanel() {

		setLayout(null);
		smiley = new JButton();
		chronometer = new Chronometer();
		mineCounter = new MineCounter();
		
		//Smiley Setup
		setHappyFace();
		add(smiley);
		smiley.setBounds(260, 10, 40, 40);
		smiley.addMouseListener(new ButtonHandler(1));
		
		//Chronometer Setup
		add(chronometer);
		chronometer.setBounds(10, 10, 63, 40);
		
		//MineCounter Setup
		add(mineCounter);
		mineCounter.setBounds(525, 10, 63, 40);
		
	}
	
	public static void setLostFace() {
		smiley.setIcon(new ImageIcon("src/iconSource/lost.jpg"));
		smiley.setPressedIcon(new ImageIcon("src/iconSource/lost.jpg"));
	}
	
	public static void setWonFace() {
		smiley.setIcon(new ImageIcon("src/iconSource/won.jpg"));
	}
	
	public static void setClickHoldFace() {
		smiley.setIcon(new ImageIcon("src/iconSource/click.jpg"));
	}
	
	public static void setHappyFace() {
		smiley.setIcon(new ImageIcon("src/iconSource/happy.jpg"));
		smiley.setPressedIcon(new ImageIcon("src/iconSource/happyPressed.jpg"));
	}
}