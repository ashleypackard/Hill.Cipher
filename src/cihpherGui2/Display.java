/* Ashley Packard
 * & Beck Price
 * November 2012
 */

package cihpherGui2;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class Display extends JFrame implements ActionListener
{
	
	private JLabel lblTitle, lblEnterMess, lblMatrix, lblInverse, lblEncryptMess, lblDecryptMess;
	private JTextPane jpnMatrix, jpnInverse;
	private JScrollPane scrPnEnMess, scrPne, scrPnDeMess;
	private JTextArea txtAreaEnter, txtAreaEnMess, txtAreaDeMess, txtAreaMatrixSize, txtAreaAuthor;
	private JButton btnEncrypt, btnDecrypt, btnClear;
	private JSpinner spnMatrixSize;
	
	public Display()
	{
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		setSize(815, 723);
		setAlwaysOnTop(true);
		getContentPane().setBackground(new Color(32, 178, 170));
		setTitle("A Hill Cihper Example");
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		lblTitle = new JLabel("The Hill Cipher");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Mathematica7", Font.BOLD, 60));
		lblTitle.setBounds(0, 0, 809, 91);
		getContentPane().add(lblTitle);
		
		lblEnterMess = new JLabel("Please enter a message:");
		lblEnterMess.setFont(new Font("Constantia", Font.PLAIN, 15));
		lblEnterMess.setBounds(105, 104, 169, 28);
		getContentPane().add(lblEnterMess);
		
		btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setPreferredSize(new Dimension(71, 25));
		btnEncrypt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEncrypt.setBounds(596, 137, 89, 28);
		btnEncrypt.setActionCommand("encrypt");		
		btnEncrypt.setMnemonic(KeyEvent.VK_E);	
		btnEncrypt.addActionListener(this);
		getContentPane().add(btnEncrypt);
		
		btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setEnabled(false);
		btnDecrypt.setPreferredSize(new Dimension(71, 25));
		btnDecrypt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDecrypt.setName("btnDecrypt");
		btnDecrypt.setBounds(596, 171, 89, 28);
		btnDecrypt.setActionCommand("decrypt");		
		btnDecrypt.setMnemonic(KeyEvent.VK_D);	
		btnDecrypt.addActionListener(this);
		getContentPane().add(btnDecrypt);
		
		btnClear = new JButton("Clear");
		btnClear.setPreferredSize(new Dimension(71, 25));
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClear.setBounds(596, 205, 89, 28);
		btnClear.setActionCommand("clear");		
		btnClear.setMnemonic(KeyEvent.VK_C);	
		btnClear.addActionListener(this);
		getContentPane().add(btnClear);
		
		lblMatrix = new JLabel("Matrix:");
		lblMatrix.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatrix.setFont(new Font("Constantia", Font.PLAIN, 15));
		lblMatrix.setBounds(12, 258, 381, 28);
		getContentPane().add(lblMatrix);
		
		lblInverse = new JLabel("Inverse Matrix:");
		lblInverse.setHorizontalAlignment(SwingConstants.CENTER);
		lblInverse.setFont(new Font("Constantia", Font.PLAIN, 15));
		lblInverse.setBounds(405, 258, 385, 28);
		getContentPane().add(lblInverse);
		
		lblEncryptMess = new JLabel("Encrypted Message:");
		lblEncryptMess.setFont(new Font("Constantia", Font.PLAIN, 15));
		lblEncryptMess.setBounds(105, 486, 146, 28);
		getContentPane().add(lblEncryptMess);
		
		lblDecryptMess = new JLabel("Decrypted Message:");
		lblDecryptMess.setFont(new Font("Constantia", Font.PLAIN, 15));
		lblDecryptMess.setBounds(105, 558, 146, 28);
		getContentPane().add(lblDecryptMess);
		
		jpnMatrix = new JTextPane();
		jpnMatrix.setBackground(new Color(32, 178, 170));
		jpnMatrix.setFont(new Font("Constantia", Font.PLAIN, 15));
		jpnMatrix.setEditable(false);
		jpnMatrix.setBounds(12, 282, 381, 208);
		getContentPane().add(jpnMatrix);
		
		jpnInverse = new JTextPane();
		jpnInverse.setBackground(new Color(32, 178, 170));
		jpnInverse.setFont(new Font("Constantia", Font.PLAIN, 15));
		jpnInverse.setEditable(false);
		jpnInverse.setBounds(405, 282, 392, 208);
		getContentPane().add(jpnInverse);
		
		scrPne = new JScrollPane();
		scrPne.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrPne.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrPne.setAutoscrolls(true);
		scrPne.setBounds(109, 136, 456, 97);
		getContentPane().add(scrPne);
		
		txtAreaEnter = new JTextArea();
		scrPne.setViewportView(txtAreaEnter);
		txtAreaEnter.setWrapStyleWord(true);
		txtAreaEnter.setLineWrap(true);
		txtAreaEnter.setFont(new Font("Monospaced", Font.PLAIN, 13));
		
		scrPnEnMess = new JScrollPane();
		scrPnEnMess.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrPnEnMess.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrPnEnMess.setAutoscrolls(true);
		scrPnEnMess.setBounds(105, 520, 514, 37);
		getContentPane().add(scrPnEnMess);
		
		txtAreaEnMess = new JTextArea();
		scrPnEnMess.setViewportView(txtAreaEnMess);
		txtAreaEnMess.setEditable(false);
		txtAreaEnMess.setWrapStyleWord(true);
		txtAreaEnMess.setLineWrap(true);
		txtAreaEnMess.setFont(new Font("Monospaced", Font.PLAIN, 13));
		
		scrPnDeMess = new JScrollPane();
		scrPnDeMess.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrPnDeMess.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrPnDeMess.setAutoscrolls(true);
		scrPnDeMess.setBounds(103, 591, 516, 91);
		getContentPane().add(scrPnDeMess);
		
		txtAreaDeMess = new JTextArea();
		scrPnDeMess.setViewportView(txtAreaDeMess);
		txtAreaDeMess.setEditable(false);
		txtAreaDeMess.setLineWrap(true);
		txtAreaDeMess.setWrapStyleWord(true);
		txtAreaDeMess.setFont(new Font("Monospaced", Font.PLAIN, 13));
		
		spnMatrixSize = 
				
				new JSpinner();
		spnMatrixSize.setToolTipText("Min = 3; Max = 10");
		spnMatrixSize.setModel(new SpinnerNumberModel(3, 3, 10, 1));
		spnMatrixSize.setBounds(312, 246, 39, 23);
		getContentPane().add(spnMatrixSize);
		
		txtAreaMatrixSize = new JTextArea();
		txtAreaMatrixSize.setBackground(new Color(32, 178, 170));
		txtAreaMatrixSize.setFont(new Font("Constantia", Font.PLAIN, 15));
		txtAreaMatrixSize.setWrapStyleWord(true);
		txtAreaMatrixSize.setLineWrap(true);
		txtAreaMatrixSize.setText("Please choose a matrix size:");
		txtAreaMatrixSize.setBounds(105, 241, 223, 28);
		getContentPane().add(txtAreaMatrixSize);
		
		txtAreaAuthor = new JTextArea();
		txtAreaAuthor.setFont(new Font("Constantia", Font.PLAIN, 13));
		txtAreaAuthor.setBackground(new Color(32, 178, 170));
		txtAreaAuthor.setText("Created By: Ashley Packard & Becky Price November 2012");
		txtAreaAuthor.setWrapStyleWord(true);
		txtAreaAuthor.setLineWrap(true);
		txtAreaAuthor.setBounds(698, 610, 99, 72);
		getContentPane().add(txtAreaAuthor);
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) 
	{

		String message = txtAreaEnter.getText();
    	
		int matrixSize = (Integer) spnMatrixSize.getValue();
		Encrypt coder = new Encrypt(message, matrixSize);
    	ArrayList<Double> vector3 = coder.getorig();
    	
    	coder.chop();
    	
		coder.matrixMultiplication();
		
		double[][] matrixA = coder.getMatrix();
		ArrayList<Double> vector = coder.getEncryptedVector();
		int groups = coder.getGroups();
		int addChars = coder.getAdditionalChars();
	
		
	    if("encrypt".equals(event.getActionCommand())) 
	    {
	    
			
			jpnMatrix.setText(coder.printMatrix());
			txtAreaEnMess.setText(coder.printEncryptedMessage());
				
			btnEncrypt.setEnabled(false);
			btnDecrypt.setEnabled(true);
			spnMatrixSize.setEnabled(false);
			txtAreaEnter.setEditable(false);
			
			
			getContentPane().validate();
			getContentPane().repaint();
	    	
	    }
	    if("decrypt".equals(event.getActionCommand())) 
	    {
	    	Decrypt decoder = new Decrypt(vector, matrixA, matrixSize, groups, addChars);
	    	
			decoder.determineInversesOfIndex();
			decoder.matrixOperations();			
			decoder.inverseMatrixMultiplication();
	    					
			jpnInverse.setText(decoder.printIdentityMatrix());
			txtAreaDeMess.setText(decoder.printDecryptedMessage());
			
			btnDecrypt.setEnabled(false);
			spnMatrixSize.setEnabled(false);
			
			getContentPane().validate();
			getContentPane().repaint();
	    }
	    if("clear".equals(event.getActionCommand())) 
	    {
	    	btnEncrypt.setEnabled(true);
	    	btnDecrypt.setEnabled(false);
	    	spnMatrixSize.setEnabled(true);
	    	txtAreaEnter.setText(null);	
	    	jpnMatrix.setText(null);
	    	txtAreaEnMess.setText(null);
	    	jpnInverse.setText(null);
	    	txtAreaDeMess.setText(null);
			txtAreaEnter.setEditable(true);

	    	
			getContentPane().validate();
			getContentPane().repaint();
	    }
	    	  
	}
}
