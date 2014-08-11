package client.gui.dialog;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import client.connect.ClientController;
import net.miginfocom.swing.MigLayout;
import common.Product;

public class AddProductDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;

	public AddProductDialog () {
		
		setTitle ("Add Product");
		setLayout (new MigLayout ("", "[100:100:200][200:200:200]"));
		setModalExclusionType (Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
		setLocation (600, 300);
		initComponents ();
		pack ();
		setVisible (true);	
	}
	
	public void initComponents () {
		
		final JTextField productNameTF;
		final JTextField descriptionTF;
		final JTextField priceTF;
		
		final JLabel productNameLab;
		final JLabel descriptionLab;
		final JLabel priceLab;
		final JLabel errorLab;
		
		final JButton butCancel;
		final JButton butAdd;
		
		final ClientController client;
		client = new ClientController ();
		
		productNameTF = new JTextField ();
		descriptionTF = new JTextField ();
		priceTF = new JTextField ();
	    
	    productNameTF.setEditable (true);
	    descriptionTF.setEditable (true);
	    priceTF.setEditable (true);
	    
	    productNameLab = new JLabel ("Product Name: ");
	    descriptionLab = new JLabel ("Description: ");
	    priceLab = new JLabel ("Price: ");
	    errorLab = new JLabel ("All fields are required to be filled out!");
	    
	    butAdd = new JButton ("Add");
	    butCancel = new JButton ("Cancel");
	    
		butAdd.addActionListener (new ActionListener () {

			@Override
			public void actionPerformed (java.awt.event.ActionEvent evt) {

				if (productNameTF.getText ().trim ().length () == 0
						|| descriptionTF.getText ().length () == 0
						|| priceTF.getText ().length () == 0) {

					errorLab.setVisible (true);

				} else {

					try {
						client.sendMessage ("Add_Product");

						client.sendObject (new Product (0, 
								productNameTF.getText ().trim (),
								descriptionTF.getText ().trim (),
								Double.parseDouble (priceTF.getText ().trim ())));

					} catch (IOException e) {
						System.out.println ("Error sending new customer to server!");
					}
				}
				dispose ();
			}
		});
		
		butCancel.addActionListener (new ActionListener() {
			@Override
			public void actionPerformed (java.awt.event.ActionEvent evt) {
				dispose ();
			}
		});
	    
	    errorLab.setForeground (Color.red);
	    errorLab.setVisible (false);
	    
	    add (errorLab, "wrap");
	    add (productNameLab);
	    add (productNameTF, "width 100, wrap");
	    add (descriptionLab);
	    add (descriptionTF, "width 100, wrap");
	    add (priceLab);
	    add (priceTF, "width 100, wrap");
	    add (butAdd);
	    add (butCancel);   
	}	
}