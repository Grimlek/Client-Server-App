import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class SoftwareSystemGUI {

	JButton testButton = new JButton ();
	
	public SoftwareSystemGUI (){
	
		JFrame frame = new JFrame ();
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setVisible (true);
		
		JPanel panel = new JPanel ();
		panel.setBackground (Color.blue);
		panel.setVisible (true);
		
		frame.add (panel);
		
		panel.add (testButton);
		
		
	}
	
	public static void main(String[] args){
		
		new SoftwareSystemGUI();
	}
}
