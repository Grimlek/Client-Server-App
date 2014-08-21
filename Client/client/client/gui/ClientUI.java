package client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
		final JMenuItem menuClose;
		
		new JFrame ();
		setLayout (new MigLayout ());
		
		addWindowListener (new WindowAdapter () {
		    public void windowClosing (WindowEvent e) {
		    	try {
					client.sendMessage ("Disconnect");
					setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
				} catch (IOException ex) {
					ex.printStackTrace ();
				}
		    }});

		menuBar = new JMenuBar ();
		menuClose = new JMenuItem ("Close");
		menuFile = new JMenu ("File");
		menuAbout = new JMenu ("About");
		
		menuClose.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent arg0) {
				try {
					client.sendMessage("Disconnect");
					dispose ();	
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		menuAbout.addMouseListener (new MouseListener () {
			@Override
			public void mouseClicked (MouseEvent arg0) {
				JOptionPane.showMessageDialog (null, "Server Tec Software System\n" +
						"Written By: Charles A. Sexton Jr.\n" +
						"Version: 2.4");
			}
			@Override
			public void mouseEntered (MouseEvent arg0) { }
			@Override
			public void mouseExited (MouseEvent arg0) { }
			@Override
			public void mousePressed (MouseEvent arg0) { }
			@Override
			public void mouseReleased (MouseEvent arg0) { }			
		});
				
		menuFile.add (menuClose);
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
		private BufferedImage logo;

		StartPanel () {
			
			setLayout (new MigLayout ());

			initComponents ();

			add (welcome);
		}

		public void initComponents () {

			welcome = new JLabel ("This product is currently in AlphaBeta");
			
			try {
				logo = ImageIO.read (new File (
						"C:/Users/Charles/Desktop/Eclipse Workspace/ServerTec.png"));
			} catch (IOException ex) {
				System.out.println("Image didn't load!");
			}		
			
			picLab = new JLabel (new ImageIcon (logo));
			
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
								System.out.println("Error sending the selected customer" 
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

		ProductPanel () {

			setLayout (new MigLayout ());

			prodTableModel = new ProductTableModel (
					(ArrayList<Product>) client
							.receiveObject ("Get_Product_Data"));

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