package mineSweeperGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;

public class Chronometer extends JTextField{
	private Font font;
	private int second;
	
	public Chronometer() {
		try {	//I have taken some of these lines from internet, but modified most of them.
            font = Font.createFont(Font.TRUETYPE_FONT,  new File("src/iconSource/digitalDismay.otf")).deriveFont(65f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/iconSource/digitalDismay.otf")));
        } catch (IOException | FontFormatException e) {
        	System.out.println("An exception happened! (ChronometerClass)");
        }
		
		setSize(75, 40);
		setFont(font);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		
		
		Timer timer = new Timer();	
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				second++;
//				rightLabel.setText(String.format("%03d",secondsPassed));    //format is used for making this 000 before that i used string.value
			}
		};
		
		timer.scheduleAtFixedRate(timerTask, 1000, 1000);

	}
}