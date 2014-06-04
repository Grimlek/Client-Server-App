import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;


public class Client_UI {
	
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuAbout;

	Client_UI(){
		
		frame = new JFrame();
		frame.setLayout(new MigLayout());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuAbout = new JMenu("About");
		
		menuBar.add(menuFile);
		menuBar.add(menuAbout);
		
		frame.setJMenuBar(menuBar);
		frame.add(new TabPanel());
		
		frame.pack ();
		frame.setLocationRelativeTo (null);
		frame.setVisible (true);
		
	}

	@SuppressWarnings("serial")
	private class TabPanel extends JTabbedPane {
		
		TabPanel (){
			
			addTab("Start Page", new StartPanel());
			addTab("Customer" , new CustomerPanel());
			addTab("Product", new ProductPanel());
		}
		
	}
	
	@SuppressWarnings("serial")
	private class StartPanel extends JPanel {
		
		private JLabel welcome;
		
		StartPanel (){
			
			setLayout(new MigLayout());
		
			initComponents();
		
			add(welcome);
		}
		
		public void initComponents (){
			
			welcome = new JLabel("This product is currently in AlphaBeta");
			
		}
		
	}
	
	@SuppressWarnings("serial")
	private class CustomerPanel extends JPanel{
		
		private JLabel lblCust;
		
		CustomerPanel (){
			
			setLayout (new MigLayout ());
			
			

			
			
			initComponents ();
			
		}
		
		private void initComponents(){
			
			lblCust = new JLabel("Customer List: ");
			
			add(lblCust);
			
			
			
		}
		
	}
	
	@SuppressWarnings("serial")
	private class ProductPanel extends JPanel {
		
		private JLabel lblProd;
		
		ProductPanel (){
			
			setLayout (new MigLayout ());

			setBackground (Color.WHITE);
			
			initComponents ();
			
		}
		
		public void initComponents (){
			
			lblProd = new JLabel ("Product List: ");
			
			add(lblProd);
			
		}
		
	}
	
	public static void main (String[] args){
		

		        
		        Client_Controller client = new Client_Controller();
		        
		        client.sendMessage("Server are you connected?");
		        String input = client.receiveMessage();
		        System.out.println(input);
		        
	}

	}
	

