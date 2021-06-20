package mineSweeperGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;

public class MineCounter extends JTextField{
	private Font font;
	
	public MineCounter() {
		try {	
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
	}
}
