package client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.AttributeSet;
import javax.swing.text.AbstractDocument;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import common.Product;
import client.ClientController;
import net.miginfocom.swing.MigLayout;

public class SearchProdDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTextField idSearchTF;

	public SearchProdDialog () {
		setTitle ("Search Products");
		setLayout(new MigLayout ("", "[100:100:200][200:200:200]"));
		setModalityType (ModalityType.APPLICATION_MODAL);
		setLocation (600, 300);

		initComponents ();
		pack ();
		setVisible (true);
	}

	private void initComponents () {

		final JLabel idSearchLab;
		final JButton butCancel;
		final JButton butSearch;

		idSearchTF = new JTextField ();
		idSearchLab = new JLabel ("ID: ");

		butCancel = new JButton ("Cancel");
		butSearch = new JButton ("Search");

		butCancel.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				dispose ();
			}
		});

		( (AbstractDocument) idSearchTF.getDocument ())
				.setDocumentFilter (new DocumentFilter () {
					@Override
					public void replace(DocumentFilter.FilterBypass fb,
							int offset, int length, String text,
							AttributeSet attrs) throws BadLocationException {
						if (text.matches("[0-9]+")) {
							super.replace (fb, offset, length, text, attrs);
						} else {
							JOptionPane.showMessageDialog (null,
									"You must enter an ID!");
						}
					}

					@Override
					public void remove (DocumentFilter.FilterBypass fb,
							int offset, int length) throws BadLocationException {
						super.remove (fb, offset, length);
					}

					@Override
					public void insertString (DocumentFilter.FilterBypass fb,
							int offset, String string, AttributeSet attr)
							throws BadLocationException {
						super.insertString (fb, offset, string, attr);
					}
				});

		butSearch.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent arg0) {
				ClientController client = new ClientController ();
				
				if (idSearchTF.getText ().trim ().length () > 0) {
					try {
						client.sendMessage ("Search_Product");
						client.sendMessage (idSearchTF.getText ().trim ());
					} catch (IOException ex) {
						ex.printStackTrace ();
					}
				} else {
					JOptionPane.showMessageDialog (null,
							"You must enter an ID number!");
				}
				dispose ();
				
				try {
					ArrayList <Product> product = (ArrayList <Product>) client.readObject ();
					JOptionPane.showMessageDialog(null, "Product ID: " + product.get (0).getId () +
							"\nProduct Name: " + product.get (0).getProductName () +
							"\nDescription: " + product.get (0).getDescription () +
							"\nPrice: " + product.get (0).getPrice ());
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace ();
				} catch (IndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "Product doesn't exist in database!");
				}
			}
		});

		add (idSearchLab);
		add (idSearchTF, "width 100, wrap");
		add (butSearch);
		add (butCancel);
	}
}
