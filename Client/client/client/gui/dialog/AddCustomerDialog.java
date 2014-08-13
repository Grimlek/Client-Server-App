package client.gui.dialog;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import client.connect.ClientController;
import net.miginfocom.swing.MigLayout;
import common.Customer;

public class AddCustomerDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;

	public AddCustomerDialog () {
		
		setTitle ("Add Customer");
		setLayout (new MigLayout ("", "[100:100:200][200:200:200]"));
		setModalityType (ModalityType.APPLICATION_MODAL);
		setLocation (600, 300);
		initComponents ();
		pack ();
		setVisible (true);		
	}

	public void initComponents () {
		
		final JTextField firstNameTF;
		final JTextField lastNameTF;
		final JTextField addressTF;
		final JTextField phoneNumTF;
		
		final JLabel firstNameLab;
		final JLabel lastNameLab;
		final JLabel addressLab;
		final JLabel phoneNumLab;
		final JLabel errorLab;
		
		final JButton butCancel;
		final JButton butAdd;
		
		final ClientController client; 
		client= new ClientController ();
		
		firstNameTF = new JTextField ();
		lastNameTF = new JTextField ();
		addressTF = new JTextField ();
	    phoneNumTF = new JTextField ();
	    
	    firstNameTF.setEditable (true);
	    lastNameTF.setEditable (true);
	    addressTF.setEditable (true);
	    phoneNumTF.setEditable (true);
	    
	    firstNameLab = new JLabel ("First Name: ");
	    lastNameLab = new JLabel ("Last Name: ");
	    addressLab = new JLabel ("Address: ");
	    phoneNumLab = new JLabel ("Phone Number: ");
	    errorLab = new JLabel ("All fields are required to be filled out!");
	    
	    butAdd = new JButton ("Add");
	    butCancel = new JButton ("Cancel");
	    
		butAdd.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (java.awt.event.ActionEvent evt) {

				if (firstNameTF.getText ().trim ().length () == 0
						|| lastNameTF.getText ().length () == 0
						|| addressTF.getText ().length () == 0
						|| phoneNumTF.getText ().length () == 0) {

					errorLab.setVisible (true);

				} else {

					try {
						client.sendMessage ("Add_Customer");

						client.sendObject (new Customer (0, 
								firstNameTF.getText ().trim (),
								lastNameTF.getText ().trim (),
								addressTF.getText ().trim (),
								phoneNumTF.getText ().trim ()));

					} catch (IOException e) {
						System.out.println ("Error sending new customer to server!");
					}
				}
				dispose ();
			}
		});
		
		butCancel.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (java.awt.event.ActionEvent evt) {
				dispose ();
			}
		}); 
	   
	    errorLab.setForeground (Color.red);
	    errorLab.setVisible (false);
	    
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
	    add (butCancel);
	}	
}