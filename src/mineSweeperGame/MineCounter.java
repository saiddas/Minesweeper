package mineSweeperGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class MineCounter extends JLabel {
	private Font font;
	private static JTextField textField;
	
	public MineCounter() {
		try {	
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
		
		String substr = String.format("%3d", 1000+MineSweeper.getNUM_MINES()).substring(1, 4); //I add 1000 to get zeroes in a two digit count.
		textField = new JTextField(substr);
		textField.setFocusable(false);
		textField.setOpaque(false);
		textField.setFont(font);
		textField.setForeground(Color.RED);
		textField.setBorder(null);

		add(textField);
		textField.setBounds(2, 4, 75, 40);
	}
	
	public static void setCount(int i) {
		if (i >= 0) {
			textField.setText(String.valueOf(1000+i).substring(1, 4));
		}
		else if (i < 0 && i > -99) {
			textField.setText(String.valueOf(i));
		}
	}
}