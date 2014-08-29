package client.gui.dialog;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;
import client.connect.ClientController;

public class EditProductDialog extends JDialog implements DocumentListener {

	private static final long serialVersionUID = 1L;

	final JTextField productNameTF;
	final JTextField descriptionTF;
	final JTextField priceTF;
	
	final JButton butEdit;
	
	private final String [] initialValues;
	
	public EditProductDialog (String [] values) {
		setTitle ("Edit Product");
		setLayout (new MigLayout ("", "[100:100:200][200:200:200]"));
		setModalityType (ModalityType.APPLICATION_MODAL);
		setLocation (600, 300);
		
		productNameTF = new JTextField (values [1]);
		descriptionTF = new JTextField (values [2]);
		priceTF = new JTextField (values [3]);
		initialValues = values;
		
	    butEdit = new JButton ("Edit");
	    butEdit.setEnabled (false);
		
		initComponents ();
		pack ();
		setVisible (true);
	}
	
	public void initComponents () {		
		final JLabel productNameLab;
		final JLabel descriptionLab;
		final JLabel priceLab;
		
		final JButton butCancel;
		
		final ClientController client; 
		client= new ClientController ();
	    
		productNameTF.setEditable (true);
		descriptionTF.setEditable (true);
		priceTF.setEditable (true);
	    
	    addDocumentListener (productNameTF);
	    addDocumentListener (descriptionTF);
	    addDocumentListener (priceTF);
	    
	    productNameLab = new JLabel ("Product Name: ");
	    descriptionLab = new JLabel ("Description: ");
	    priceLab = new JLabel ("Price: ");
	    
		butEdit.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (java.awt.event.ActionEvent evt) {
					try {
						client.sendMessage ("Edit_Product");
						
						client.sendObject (getChangedValues());

					} catch (IOException e) {
						System.out.println ("Error sending new product to server!");
					}
				dispose ();
			}
		});
		
	    butCancel = new JButton ("Cancel");	 

	    add (productNameLab);
	    add (productNameTF, "width 100, wrap");
	    add (descriptionLab);
	    add (descriptionTF, "width 100, wrap");
	    add (priceLab);
	    add (priceTF, "width 100, wrap");
	    add (butEdit);
	    add (butCancel);
	    
	}
	
	public String [] getChangedValues () {
		
		String [] values = {initialValues [0], 
				productNameTF.getText ().trim (),
				descriptionTF.getText ().trim (), 
				priceTF.getText ().trim ()};
		
		return values;
	}
	
	public void addDocumentListener (JTextField textField) {
		textField.getDocument ().addDocumentListener (this);
	}

	@Override
	public void changedUpdate (DocumentEvent arg0) {
		checkData ();
	}

	@Override
	public void insertUpdate (DocumentEvent arg0) {
		checkData ();
	}
	
	public Boolean isDataChanged () {
		
		if ( (productNameTF.getText ().trim ().compareTo (initialValues [0]) == 0) || 
				(productNameTF.getText ().trim ().length () <= 2)) {
			return false;
		}
		if ( (descriptionTF.getText ().trim ().compareTo (initialValues [1]) == 0) || 
				(descriptionTF.getText ().trim ().length () <= 2)) {
			return false;
		}
		if ( (priceTF.getText ().trim ().compareTo (initialValues [2]) == 0) || 
				(priceTF.getText ().trim ().length () <= 2)) {
			return false;
		}
		return true;		
	}

	@Override
	public void removeUpdate (DocumentEvent arg0) {
		checkData ();
	}
	
	public void checkData () {
		butEdit.setEnabled (isDataChanged ());
	}
}
