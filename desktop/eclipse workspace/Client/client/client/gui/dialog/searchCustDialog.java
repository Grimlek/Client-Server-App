package client.gui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.AttributeSet;
import javax.swing.text.AbstractDocument;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import net.miginfocom.swing.MigLayout;

public class searchCustDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	public JTextField idSearchTF;
	public JTextField nameSearchTF;
	
	public searchCustDialog () {
		setTitle ("Search Customer");
		setLayout (new MigLayout ("", "[100:100:200][200:200:200]"));
		setModalityType (ModalityType.APPLICATION_MODAL);
		setLocation (600, 300);		
		
		initComponents ();
		pack ();
		setVisible (true);
	}

	private void initComponents () {
		
		final JLabel idSearchLab;
		final JLabel nameSearchLab;
		
		final JButton butCancel;
		final JButton butSearch;
		
		idSearchTF = new JTextField ();
		nameSearchTF = new JTextField ();
		
		idSearchLab = new JLabel ("ID: ");
		nameSearchLab = new JLabel ("Name: ");
		
	    butCancel = new JButton ("Cancel");
	    butSearch = new JButton ("Search");
	    	    
	    butCancel.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				dispose ();
			}
		});

		( (AbstractDocument) nameSearchTF.getDocument ())
				.setDocumentFilter (new DocumentFilter () {

					@Override
					public void replace (DocumentFilter.FilterBypass fb,
							int offset, int length, String text,
							AttributeSet attrs) throws BadLocationException {
						if (text.matches ("[a-zA-Z ]+")) {
							super.replace (fb, offset, length, text, attrs);
						} else {
							JOptionPane.showMessageDialog (null,
									"You must enter a name!");
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

		( (AbstractDocument) idSearchTF.getDocument ())
				.setDocumentFilter (new DocumentFilter () {
					@Override
					public void replace (DocumentFilter.FilterBypass fb,
							int offset, int length, String text,
							AttributeSet attrs) throws BadLocationException {
						if (text.matches ("[0-9]+")) {
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
				
			}
		});
	    
	    add (nameSearchLab);
	    add (nameSearchTF, "width 100, wrap");
	    add (idSearchLab); 
	    add (idSearchTF, "width 100, wrap");
	    add (butSearch);
	    add (butCancel);
	}
}
