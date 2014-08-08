package client.gui;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import client.connect.ClientController;
import client.gui.dialog.AddCustomerDialog;
import client.gui.dialog.AddProductDialog;
import client.gui.dialog.EditCustomerDialog;
import client.gui.model.CustomerTableModel;
import client.gui.model.ProductTableModel;
import common.Customer;
import common.Product;
import net.miginfocom.swing.MigLayout;

public final class ClientUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private ClientController client;
 	
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuAbout;
	
	private BufferedImage logo;

	private JLabel headerLab;

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
		private JLabel picLab;

		StartPanel () {

			setLayout (new MigLayout ());

			initComponents ();

			add (welcome);
		}

		public void initComponents () {

			welcome = new JLabel ("This product is currently in AlphaBeta");
			
			try {
				logo = ImageIO.read (new File ("C:/Users/Charles/Desktop/Eclipse Workspace/ServerTec.png"));
			} catch (IOException ex) {
				System.out.println("Image didn't load!");
			}		
			
			picLab = new JLabel (new ImageIcon(logo));
			
			add (picLab, "width 536, height 250, wrap");

		}

	}

	private class CustomerPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private final CustomerTableModel custTableModel;
		
		private JTable table;

		CustomerPanel () {

			setLayout (new MigLayout ());

			custTableModel = new CustomerTableModel (
					(ArrayList<Customer>) client
							.receiveObject ("Get_Customer_Data"));

			initComponents ();

		}

		private void initComponents () {

			headerLab = new JLabel ("Customer List: ");

			butAdd = new JButton ("Add");
			butRemove = new JButton ("Remove");
			butEdit = new JButton ("Edit");
			
			butAdd.addActionListener (new ActionListener () {

				@Override
				public void actionPerformed (java.awt.event.ActionEvent evt) {
					
					new AddCustomerDialog ();
					
				}
				
			});
			
			butRemove.addActionListener (new ActionListener () {

				@Override
				public void actionPerformed (java.awt.event.ActionEvent evt) {
					int selectedRow = table.getSelectedRow ();
					
					String [] values = custTableModel.getRowValues (selectedRow);
					
					JOptionPane.showConfirmDialog (null, "Are you sure that you want to remove customer?",
							"Warning", JOptionPane.YES_NO_OPTION);
					
						
						if (JOptionPane.YES_NO_OPTION == JOptionPane.YES_OPTION) {
					
							try {
							
							client.sendMessage ("Remove_Customer");
							
							client.sendMessage (values [0]);
							
							custTableModel.removeRow (selectedRow);

							} catch (IOException ex) {
								System.out.println("Error sending the selected customer" 
										+ " to remove from the client to server!");
							}		
						}
				}
			});
			
			butEdit.addActionListener (new ActionListener () {

				@Override
				public void actionPerformed (java.awt.event.ActionEvent evt) {
					
					int selectedRow = table.getSelectedRow ();
					
					String [] values = custTableModel.getRowValues (selectedRow);
					
					EditCustomerDialog editDialog = new EditCustomerDialog (values);
					
					custTableModel.updateRows(selectedRow, editDialog.getChangedValues());
				}
				
			});

			add (headerLab, "wrap");

			table = new JTable (custTableModel);
			table.setFillsViewportHeight (true);
			table.getTableHeader ().setReorderingAllowed (false);
			
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
		
		private JTable table;

		private final ProductTableModel prodTableModel;

		ProductPanel () {

			setLayout (new MigLayout ());

			prodTableModel = new ProductTableModel (
					(ArrayList<Product>) client
							.receiveObject ("Get_Product_Data"));

			initComponents ();

		}

		public void initComponents () {

			headerLab = new JLabel ("Product List: ");

			butAdd = new JButton ("Add");
			butRemove = new JButton ("Remove");
			butEdit = new JButton ("Edit");
			
			butAdd.addActionListener (new ActionListener () {

				@Override
				public void actionPerformed (java.awt.event.ActionEvent evt) {
					
					new AddProductDialog ();
					
				}
				
			});

			butRemove.addActionListener (new ActionListener () {

				@Override
				public void actionPerformed (java.awt.event.ActionEvent evt) {
					
					int selectedRow = table.getSelectedRow ();
					
					String [] values = prodTableModel.getRowValues (selectedRow);
					
					JOptionPane.showConfirmDialog (null, "Are you sure that you want to remove this product?",
							"Warning", JOptionPane.YES_NO_OPTION);
					
						
						if (JOptionPane.YES_NO_OPTION == JOptionPane.YES_OPTION) {
					
							try {
							
							client.sendMessage ("Remove_Product");
							
							client.sendMessage (values [0]);
							
							prodTableModel.removeRow (selectedRow);
							
							} catch (IOException ex) {
								System.out.println("Error sending the selected product" 
										+ " to remove from the client to server!");
							}		
						}
				}
			});
			
			butEdit.addActionListener (new ActionListener() {

				@Override
				public void actionPerformed (java.awt.event.ActionEvent evt) {
					
					System.out.println ("sup");
					
				}
				
			});

			table = new JTable (prodTableModel);
			table.setFillsViewportHeight (true);
			table.getTableHeader ().setReorderingAllowed (false);
			table.getColumnModel ().getColumn (1).setMinWidth (150);
			table.getColumnModel ().getColumn (2).setMinWidth (125);
			
			// This is setting user selection to a single interval.
			table.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
			scroll = new JScrollPane (table);

			add (headerLab, "wrap");
			add (scroll);
			add (butAdd, "split 3, flowy, top, sgx");
			add (butRemove, "sgx");
			add (butEdit, "sgx");

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
