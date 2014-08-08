package client.gui.dialog;

import java.awt.Dialog;
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

public class EditCustomerDialog extends JDialog implements DocumentListener{
	
	private static final long serialVersionUID = 1L;

	final JTextField firstNameTF;
	final JTextField lastNameTF;
	final JTextField addressTF;
	final JTextField phoneNumTF;
	
	final JButton butEdit;
	
	private final String [] initialValues;
	
	public EditCustomerDialog (String [] values) {
		setTitle ("Edit Custoemr");
		setLayout (new MigLayout ("", "[100:100:200][200:200:200]"));
		setModalExclusionType (Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
		setLocation (600, 300);
		
		firstNameTF = new JTextField (values [1]);
		lastNameTF = new JTextField (values [2]);
		addressTF = new JTextField (values [3]);
		phoneNumTF = new JTextField (values [4]);
		initialValues = values;
		
	    butEdit = new JButton ("Edit");
	    butEdit.setEnabled(false);
		
		initComponents ();
		pack ();
		setVisible (true);
	}
	
	public void initComponents () {		
		final JLabel firstNameLab;
		final JLabel lastNameLab;
		final JLabel addressLab;
		final JLabel phoneNumLab;
		
		final JButton butCancel;
		
		final ClientController client; 
		client= new ClientController ();
	    
	    firstNameTF.setEditable (true);
	    lastNameTF.setEditable (true);
	    addressTF.setEditable (true);
	    phoneNumTF.setEditable (true);
	    
	    addDocumentListener (firstNameTF);
	    addDocumentListener (lastNameTF);
	    addDocumentListener (addressTF);
	    addDocumentListener (phoneNumTF);
	    
	    firstNameLab = new JLabel ("First Name: ");
	    lastNameLab = new JLabel ("Last Name: ");
	    addressLab = new JLabel ("Address: ");
	    phoneNumLab = new JLabel ("Phone Number: ");
	    
		butEdit.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (java.awt.event.ActionEvent evt) {
					try {
						client.sendMessage ("Edit_Customer");
						
						client.sendObject (getChangedValues());

					} catch (IOException e) {
						System.out.println ("Error sending new customer to server!");
					}
				dispose ();
			}
		});
		
	    butCancel = new JButton ("Cancel");	 

	    add (firstNameLab);
	    add (firstNameTF, "width 100, wrap");
	    add (lastNameLab);
	    add (lastNameTF, "width 100, wrap");
	    add (addressLab);
	    add (addressTF, "width 100, wrap");
	    add (phoneNumLab);
	    add (phoneNumTF, "width 100, wrap");
	    add (butEdit);
	    add (butCancel);
	    
	}
	
	public String [] getChangedValues (){
		
		String [] values = {initialValues [0], 
				firstNameTF.getText ().trim (),
				lastNameTF.getText ().trim (), 
				addressTF.getText ().trim (),
				phoneNumTF.getText ().trim ()};
		
		return values;
	}
	
	public void addDocumentListener (JTextField textField){
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
	
	public Boolean isDataChanged (){
		System.out.println("System has entered data changed");
		
		if ( (firstNameTF.getText ().trim ().contains (initialValues[0])) || 
				(firstNameTF.getText ().trim ().length () <= 2)) {
			System.out.println("first name tf");	
			return false;
		}
		if ((lastNameTF.getText ().trim ().contains (initialValues[1])) || 
				(lastNameTF.getText ().trim ().length () <= 2)) {
			System.out.println("last name tf");
			return false;
		}
		if ((addressTF.getText ().trim ().contains (initialValues[2])) || 
				(addressTF.getText ().trim ().length () <= 2)) {
			System.out.println("address tf");
			return false;
		}
		if ((phoneNumTF.getText ().trim ().contains (initialValues[3])) || 
				(phoneNumTF.getText ().trim ().length () <= 2)) {
			System.out.println("phone num tf");
			return false;
		}
		return true;		
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		checkData ();
	}
	
	public void checkData () {
		butEdit.setEnabled (isDataChanged ());
	}
}