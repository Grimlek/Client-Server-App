package client;

import java.awt.Dimension;
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

	@SuppressWarnings("serial")
	private class TabPanel extends JTabbedPane {

		TabPanel() {

			addTab("Start Page", new StartPanel());
			addTab("Customer", new CustomerPanel());
			addTab("Product", new ProductPanel());
		}

	}

	@SuppressWarnings("serial")
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

	@SuppressWarnings("serial")
	private class CustomerPanel extends JPanel {

		private JLabel lblCust;
		private Customer_Table_Model ctm;
		private JScrollPane scroll;
		private JPanel buttonPanel;
		private JTable table;

		CustomerPanel() {

			setLayout(new MigLayout());

			ctm = new Customer_Table_Model(
					(ArrayList<Customer>) client
							.receiveObject("Get_Customer_Data"));

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

	@SuppressWarnings("serial")
	private class ProductPanel extends JPanel {

		private JLabel lblProd;
		private JButton butAdd;
		private JButton butRemove;
		private JButton butEdit;
		private Product_Table_Model ptm;
		private JScrollPane scroll;
		private JPanel buttonPanel;
		private JTable table;

		ProductPanel() {

			setLayout(new MigLayout("debug"));

			ptm = new Product_Table_Model(
					(ArrayList<Product>) client
							.receiveObject("Get_Product_Data"));

			initComponents();

		}

		public void initComponents() {

			lblProd = new JLabel ("Product List: ");
			
			buttonPanel = new JPanel (new MigLayout());
			butAdd = new JButton ("Add");
			butRemove = new JButton ("Remove");
			butEdit = new JButton ("Edit");
			buttonPanel.add(butAdd, "cell 0 0");
			buttonPanel.add(butRemove, "cell 0 1");
			buttonPanel.add(butEdit, "cell 0 2");

			butAdd.setPreferredSize(new Dimension(40, 50));
			
			add(lblProd, "wrap");

			table = new JTable(ptm);
			table.setFillsViewportHeight(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			scroll = new JScrollPane(table);

			add(scroll);
			add(buttonPanel);
			

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

	
	

