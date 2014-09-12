package client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.connect.ClientController;
import client.gui.dialog.AddCustomerDialog;
import client.gui.dialog.AddProductDialog;
import client.gui.dialog.EditCustomerDialog;
import client.gui.dialog.EditProductDialog;
import client.gui.dialog.SearchCustDialog;
import client.gui.dialog.SearchProdDialog;
import client.gui.model.CustomerTableModel;
import client.gui.model.ProductTableModel;
import common.Customer;
import common.Product;
import net.miginfocom.swing.MigLayout;

public final class ClientUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private ClientController client;

	ClientUI () {
		
		client = new ClientController ();

		initComponents ();
	}

	public void initComponents () {

		final JMenuBar menuBar;
		final JMenu menuFile;
		final JMenu menuAbout;
		final JMenu menuSearch;
		final JMenuItem menuClose;
		final JMenuItem menuServerTec;
		final JMenuItem menuCustomer;
		final JMenuItem menuProduct;
		
		new JFrame ();
		setLayout (new MigLayout ());
		
		addWindowListener (new WindowAdapter () {
		    public void windowClosing (WindowEvent e) {
		    	try {
					client.closeConnection();
					setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
				} catch (IOException ex) {
					ex.printStackTrace ();
				}
		    }});

		menuBar = new JMenuBar ();
		menuFile = new JMenu ("File");
		menuSearch = new JMenu ("Search");
		menuAbout = new JMenu ("About");
		menuClose = new JMenuItem ("Close");
		menuServerTec = new JMenuItem ("Server Tec");
		menuCustomer = new JMenuItem ("Customers");
		menuProduct = new JMenuItem ("Products");
		
		menuClose.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent arg0) {
				try {
					client.closeConnection();
					dispose ();	
				} catch (IOException ex) {
					ex.printStackTrace ();
				}
			}
		});
		
		menuServerTec.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				JOptionPane.showMessageDialog (null, "Server Tec Software System\n" +
						"Written By: Charles A. Sexton Jr.\n" +
						"Version: 2.4");
			}
		});
		
		menuCustomer.addActionListener (new ActionListener () {			
			@Override
			public void actionPerformed (ActionEvent arg0) {
				new SearchCustDialog ();
			}
		});
		
		menuProduct.addActionListener (new ActionListener () {			
			@Override
			public void actionPerformed (ActionEvent e) {
				new SearchProdDialog ();
			}
		});
				
		menuSearch.add (menuCustomer);
		menuSearch.add (menuProduct);
		menuBar.add (menuFile);
		menuFile.add (menuSearch);
		menuFile.add (menuClose);
		menuBar.add (menuAbout);
		menuAbout.add (menuServerTec);
		
		setJMenuBar (menuBar);
		add (new TabPanel ());

		pack ();
		setLocationRelativeTo (null);
		setVisible (true);
	}

	private class TabPanel extends JTabbedPane {

		private static final long serialVersionUID = 1L;

		TabPanel () {
			try {
				addTab ("Start Page", new StartPanel ());
				addTab ("Customer", new CustomerPanel ());
				addTab ("Product", new ProductPanel ());
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace ();
			}
		}
	}

	private class StartPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private JLabel picLab;
		private BufferedImage logo;

		StartPanel () {
			
			setLayout (new MigLayout ());

			initComponents ();
		}

		public void initComponents () {			
			try {
				logo = ImageIO.read (new File (
						"C:/Users/Charles/Desktop/Eclipse Workspace/ServerTec.png"));
			} catch (IOException ex) {
				System.out.println ("Image didn't load!");
			}		
			
			picLab = new JLabel (new ImageIcon (logo));
			
			add (picLab, "width 536, height 400, wrap");
		}
	}

	private class CustomerPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private final CustomerTableModel custTableModel;
				
		private JTable table;

		CustomerPanel () throws IOException, ClassNotFoundException {

			setLayout (new MigLayout ());

			client.sendMessage ("Get_Customer_Data");
			
			custTableModel = new CustomerTableModel (
					(ArrayList <Customer>) client
							.readObject ());

			initComponents ();
		}

		private void initComponents () {
			
			final JButton butRemove;
			final JButton butEdit;
			final JButton butAdd;
			final JLabel headerLab;
			final JScrollPane scroll;

			headerLab = new JLabel ("Customer List: ");

			butAdd = new JButton ("Add");
			butRemove = new JButton ("Remove");
			butRemove.setEnabled (false);
			butEdit = new JButton ("Edit");
			butEdit.setEnabled (false);
			
			butAdd.addActionListener (new ActionListener () {
				@Override
				public void actionPerformed (java.awt.event.ActionEvent evt) {
					new AddCustomerDialog ();	
				}
			});
			
			butRemove.addActionListener (new ActionListener () {
				@Override
				public void actionPerformed (java.awt.event.ActionEvent evt) {
					final int selectedRow = table.getSelectedRow ();
					
					final String [] values = custTableModel.getRowValues (selectedRow);
					
					final int reply = JOptionPane.showConfirmDialog (null, "Are you sure that you want to remove customer?",
							"Warning", JOptionPane.YES_NO_OPTION);
					
						if (reply == JOptionPane.YES_OPTION) {
					
							try {
							
							client.sendMessage ("Remove_Customer");
							
							client.sendMessage (values [0]);
							
							custTableModel.removeRow (selectedRow);

							} catch (IOException ex) {
								System.out.println ("Error sending the selected customer" 
										+ " to remove from the client to server!");
							}		
						}
				}
			});
			
			butEdit.addActionListener (new ActionListener () {
				@Override
				public void actionPerformed (java.awt.event.ActionEvent evt) {
					
					final int selectedRow = table.getSelectedRow ();
					
					final String [] values = custTableModel.getRowValues (selectedRow);
					
					final EditCustomerDialog editDialog = new EditCustomerDialog (values);
					
					editDialog.addWindowListener (new WindowAdapter () {
					    @Override
					    public void windowClosed (WindowEvent e) {
					    	custTableModel.updateRow (selectedRow, editDialog.getChangedValues());
					    }
					});
				}
			});

			add (headerLab, "wrap");

			table = new JTable (custTableModel);			
			table.setFillsViewportHeight (true);
			table.getTableHeader ().setReorderingAllowed (false);
			
			table.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
			table.getSelectionModel ().addListSelectionListener (new ListSelectionListener () {
				@Override
				public void valueChanged (ListSelectionEvent e) {
					int row = table.getSelectedRow ();
					if (row != -1) {
						butEdit.setEnabled (true);
						butRemove.setEnabled (true);
					}
			}});
			
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

		ProductPanel () throws IOException, ClassNotFoundException {

			setLayout (new MigLayout ());

			client.sendMessage("Get_Product_Data");
			
			prodTableModel = new ProductTableModel (
					(ArrayList <Product>) client.readObject ());

			initComponents ();

		}

		public void initComponents () {
			
			final JButton butRemove;
			final JButton butEdit;
			final JButton butAdd;
			final JLabel headerLab;
			final JScrollPane scroll;

			headerLab = new JLabel ("Product List: ");

			butAdd = new JButton ("Add");
			butRemove = new JButton ("Remove");
			butRemove.setEnabled(false);
			butEdit = new JButton ("Edit");
			butEdit.setEnabled(false);
			
			butAdd.addActionListener (new ActionListener () {
				@Override
				public void actionPerformed (java.awt.event.ActionEvent evt) {
					new AddProductDialog ();	
				}
			});

			butRemove.addActionListener (new ActionListener () {
				@Override
				public void actionPerformed (java.awt.event.ActionEvent evt) {
					
					final int selectedRow = table.getSelectedRow ();
					
					final String [] values = prodTableModel.getRowValues (selectedRow);
					
					final int reply = JOptionPane.showConfirmDialog (null,
							"Are you sure that you want to remove this product?",
							"Warning", JOptionPane.YES_NO_OPTION);
					
						
						if (reply == JOptionPane.YES_OPTION) {
					
							try {
							
							client.sendMessage ("Remove_Product");
							
							client.sendMessage (values [0]);
							
							prodTableModel.removeRow (selectedRow);
							
							} catch (IOException ex) {
								System.out.println ("Error sending the selected product" 
										+ " to remove from the client to server!");
							}
						}
				}
			});
			
			butEdit.addActionListener (new ActionListener () {
				@Override
				public void actionPerformed (java.awt.event.ActionEvent evt) {
					
					final int selectedRow = table.getSelectedRow ();
					
					final String [] values = prodTableModel.getRowValues (selectedRow);
					
					final EditProductDialog editDialog = new EditProductDialog (values);
					
					editDialog.addWindowListener (new WindowAdapter () {
					    @Override
					    public void windowClosed (WindowEvent e) {
					    	prodTableModel.updateRow (selectedRow, editDialog.getChangedValues());
					    }
					});
				}
			});

			table = new JTable (prodTableModel);
			table.setFillsViewportHeight (true);
			table.getTableHeader ().setReorderingAllowed (false);
			table.getColumnModel ().getColumn (1).setMinWidth (150);
			table.getColumnModel ().getColumn (2).setMinWidth (125);
			
			table.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
			table.getSelectionModel ().addListSelectionListener (new ListSelectionListener () {
				@Override
				public void valueChanged (ListSelectionEvent e) {
					int row = table.getSelectedRow ();
					if (row != -1) {
						butEdit.setEnabled (true);
						butRemove.setEnabled (true);
					}
			}});
			
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