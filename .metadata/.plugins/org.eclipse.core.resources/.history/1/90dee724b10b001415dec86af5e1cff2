package client;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import common.Customer;
import common.Product;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class Client_UI extends JFrame {

	
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuAbout;
	private Client_Controller client;

	Client_UI() {

		client = new Client_Controller();

		initComponents();
	}

	public void initComponents() {

		new JFrame();
		setLayout(new MigLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuAbout = new JMenu("About");

		menuBar.add(menuFile);
		menuBar.add(menuAbout);

		setJMenuBar(menuBar);
		add(new TabPanel());

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private class TabPanel extends JTabbedPane {

		TabPanel() {

			addTab("Start Page", new StartPanel());
			addTab("Customer", new CustomerPanel());
			addTab("Product", new ProductPanel());
		}

	}

	private class StartPanel extends JPanel {

		private JLabel welcome;

		StartPanel() {

			setLayout(new MigLayout());

			initComponents();

			add(welcome);
		}

		public void initComponents() {

			welcome = new JLabel("This product is currently in AlphaBeta");

		}

	}

	private class CustomerPanel extends JPanel {

		private JLabel lblCust;
		private CustomerTableModel ctm;
		private JScrollPane scroll;
		private JTable table;

		CustomerPanel() {

			setLayout(new MigLayout());

			ctm = new CustomerTableModel(
					(ArrayList<Customer>) client.receiveObject("Get_Customer_Data"));

			initComponents();

		}

		private void initComponents() {

			lblCust = new JLabel("Customer List: ");

			add(lblCust, "wrap");

			table = new JTable(ctm);
			table.setFillsViewportHeight(true);
			scroll = new JScrollPane(table);

			add(scroll);

		}

	}

	private class ProductPanel extends JPanel {

		private JLabel lblProd;
		private JButton butAdd;
		private JButton butRemove;
		private JButton butEdit;
		private ProductTableModel ptm;
		private JScrollPane scroll;
		private JTable table;

		ProductPanel() {

			setLayout(new MigLayout("debug"));

			ptm = new ProductTableModel(
					(ArrayList<Product>) client.receiveObject("Get_Product_Data"));
	
			initComponents();

		}

		public void initComponents() {

			lblProd = new JLabel ("Product List: ");
			
			butAdd = new JButton ("Add");
			butRemove = new JButton ("Remove");
			butEdit = new JButton ("Edit");
			

			table = new JTable(ptm);
			table.setFillsViewportHeight(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			scroll = new JScrollPane(table);

			
			add(lblProd, "wrap");
			add(scroll);
			add(butAdd, "split 3, flowy, top, sgx");
	        add(butRemove, "sgx");
	        add(butEdit, "sgx");        
			

		}

	}
	
	public static void main (String[] args) {
		

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Client_UI();
			}
		});
	}

}

	
	

