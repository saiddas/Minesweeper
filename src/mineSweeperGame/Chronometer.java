package mineSweeperGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Chronometer extends JLabel {
	private Font font;
	private int second;
	private JTextField textField;
	
	public Chronometer() {
		try {	//I have taken some of these lines from internet, but modified most of them.
            font = Font.createFont(Font.TRUETYPE_FONT,  new File("src/iconSource/digitalDismay.otf")).deriveFont(45f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/iconSource/digitalDismay.otf")));
        } catch (IOException | FontFormatException e) {
        	System.out.println("An exception happened! (ChronometerClass)");
        }
		
		setLayout(null);
		setSize(75, 40);
		setBackground(Color.BLACK);
		setOpaque(true);
		
		textField =  new JTextField("000");
		textField.setFocusable(false);
		textField.setOpaque(false);
		textField.setFont(font);
		textField.setForeground(Color.RED);
		textField.setBorder(null);
		
		add(textField);	
		textField.setBounds(2, 4, 75, 40);
		
		Timer timer = new Timer();	
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() { // I took this part from internet too
				if (!ButtonHandler.lost && !ButtonHandler.won && !(second>998)) {	
					second++;
					textField.setText(String.format("%03d", second));
				}
			}
		};
		
		timer.scheduleAtFixedRate(timerTask, 1000, 1000);
	}
}