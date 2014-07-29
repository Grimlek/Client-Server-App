package client.gui;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import client.connect.ClientController;
import common.Customer;
import common.Product;
import net.miginfocom.swing.MigLayout;

public final class ClientUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private ClientController client;
 	
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuAbout;

	private JLabel lblHeader;

	private JTable table;

	private JScrollPane scroll;

	private JButton butAdd;
	private JButton butRemove;
	private JButton butEdit;

	ClientUI () {

		client = new ClientController ();

		initComponents ();
	}

	public void initComponents () {

		new JFrame ();
		setLayout (new MigLayout ());
		setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);

		menuBar = new JMenuBar ();
		menuFile = new JMenu ("File");
		menuAbout = new JMenu ("About");

		menuBar.add (menuFile);
		menuBar.add (menuAbout);

		setJMenuBar (menuBar);
		add (new TabPanel ());

		pack ();
		setLocationRelativeTo (null);
		setVisible (true);

	}

	private class TabPanel extends JTabbedPane {

		private static final long serialVersionUID = 1L;

		TabPanel () {

			addTab ("Start Page", new StartPanel ());
			addTab ("Customer", new CustomerPanel ());
			addTab ("Product", new ProductPanel ());
		}

	}

	private class StartPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private JLabel welcome;

		StartPanel () {

			setLayout (new MigLayout ());

			initComponents ();

			add (welcome);
		}

		public void initComponents () {

			welcome = new JLabel ("This product is currently in AlphaBeta");

		}

	}

	private class CustomerPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private final CustomerTableModel custTableModel;

		CustomerPanel () {

			setLayout (new MigLayout ());

			custTableModel = new CustomerTableModel (
					(ArrayList<Customer>) client
							.receiveObject ("Get_Customer_Data"));

			initComponents ();

		}

		private void initComponents () {

			lblHeader = new JLabel ("Customer List: ");

			butAdd = new JButton ("Add");
			butRemove = new JButton ("Remove");
			butEdit = new JButton ("Edit");
			
			butAdd.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed (java.awt.event.ActionEvent evt) {
					
					new AddCustomerDialog ();
					
				}
				
			});
			
			butRemove.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					
					System.out.println("sup");
					
				}
				
			});
			
			butEdit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					
					System.out.println("sup");
					
				}
				
			});

			add (lblHeader, "wrap");

			/*
			 * The line below is setting prodTableModel (A new of Instance of
			 * ProductTableModel which extends AbstractTableModel) and passing
			 * this instance as the parameter for a new JTable instance.
			 */
			table = new JTable (custTableModel);
			table.setFillsViewportHeight (true);
			table.getTableHeader ().setReorderingAllowed (false);
			
			
			// This is setting user selection to a single interval.
			table.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
			scroll = new JScrollPane (table);
			table.getColumnModel ().getColumn (3).setMinWidth (150);
			table.getColumnModel ().getColumn (4).setMinWidth (100);
			
			scroll.requestFocus ();
			add (scroll);
			add (butAdd, "split 3, flowy, top, sgx");
			add (butRemove, "sgx");
			add (butEdit, "sgx");

		}

	}

	private class ProductPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private final ProductTableModel prodTableModel;

		ProductPanel () {

			setLayout (new MigLayout ());

			prodTableModel = new ProductTableModel (
					(ArrayList<Product>) client
							.receiveObject ("Get_Product_Data"));

			initComponents ();

		}

		public void initComponents () {

			lblHeader = new JLabel ("Product List: ");

			butAdd = new JButton ("Add");
			butRemove = new JButton ("Remove");
			butEdit = new JButton ("Edit");
			
			butAdd.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					
					System.out.println("sup");
					
				}
				
			});

			butRemove.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					
					System.out.println("sup");
					
				}
				
			});
			
			butEdit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					
					System.out.println("sup");
					
				}
				
			});

			/*
			 * The line below is setting prodTableModel (A new of Instance of
			 * ProductTableModel which extends AbstractTableModel) and passing
			 * this instance as the parameter for a new JTable instance.
			 */
			table = new JTable (prodTableModel);
			table.setFillsViewportHeight (true);
			table.getTableHeader ().setReorderingAllowed (false);
			table.getColumnModel ().getColumn (1).setMinWidth (150);
			table.getColumnModel ().getColumn (2).setMinWidth (125);
			
			// This is setting user selection to a single interval.
			table.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
			scroll = new JScrollPane (table);

			add (lblHeader, "wrap");
			add (scroll);
			add (butAdd, "split 3, flowy, top, sgx");
			add (butRemove, "sgx");
			add (butEdit, "sgx");

		}

	}
	
	public class AddCustomerDialog extends JDialog{
		
		private static final long serialVersionUID = 1L;

		public AddCustomerDialog () {
			
			setTitle("");
			setLayout (new MigLayout ("", "[100:100:200][200:200:200]"));
			setModalExclusionType (Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
			setLocation (600, 300);
			initComponents ();
			pack ();
			setVisible (true);
			
		}
		
		
		public void initComponents() {
			
			final JTextField firstNameTF;
			final JTextField lastNameTF;
			final JTextField addressTF;
			final JTextField phoneNumTF;
			
			final JLabel firstNameLab;
			final JLabel lastNameLab;
			final JLabel addressLab;
			final JLabel phoneNumLab;
			final JLabel errorLab;
			
			final JButton cancelBut;
			
			firstNameTF = new JTextField();
			lastNameTF = new JTextField();
			addressTF = new JTextField();
		    phoneNumTF = new JTextField();
		    
		    firstNameTF.setEditable(true);
		    lastNameTF.setEditable(true);
		    addressTF.setEditable(true);
		    phoneNumTF.setEditable(true);
		    
		    firstNameLab = new JLabel("First Name: ");
		    lastNameLab = new JLabel("Last Name: ");
		    addressLab = new JLabel("Addres: ");
		    phoneNumLab = new JLabel("Phone Number: ");
		    errorLab = new JLabel ("All fields are required to be filled out!");
		    
		    butAdd = new JButton("Add");
		    
			butAdd.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					
					if ( firstNameTF.getText ().trim ().length () == 0 || lastNameTF.getText().length() == 0 || 
							addressTF.getText().length() == 0 || phoneNumTF.getText().length() == 0) {
					
						errorLab.setVisible(true);
			            

			        } else {
					
					try {
						client.sendMessage("Add_Customer");
						
						client.sendObject(new Customer (0, firstNameTF.getText().trim(), lastNameTF.getText().trim(),
								addressTF.getText().trim(), phoneNumTF.getText().trim()));
						
					} catch (IOException e) {
						System.out.println("Error sending new customer to server!");
					}
					
			        }
					
					dispose();
					
				}
				
			});
			
		    cancelBut = new JButton("Cancel");	 
		    
		    errorLab.setForeground(Color.red);
		    errorLab.setVisible(false);
		    
		    add (errorLab, "wrap");
		    add (firstNameLab);
		    add (firstNameTF, "width 100, wrap");
		    add (lastNameLab);
		    add (lastNameTF, "width 100, wrap");
		    add (addressLab);
		    add (addressTF, "width 100, wrap");
		    add (phoneNumLab);
		    add (phoneNumTF, "width 100, wrap");
		    add (butAdd);
		    add (cancelBut);
		    
		}

		
	}

	public static void main (String [] args) {

		SwingUtilities.invokeLater (new Runnable () {
			public void run () {
				new ClientUI ();
			}
		});
	}

}
