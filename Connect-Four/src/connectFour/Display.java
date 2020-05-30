package connectFour;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;

//Leave this alone for now
public class Display {

	Game game = Launcher.game;
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display window = new Display();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Display() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		ImageIcon arrow = new  ImageIcon("iconSmall.png"); //This is the arrow image
		
		//Initialize the JFrame 
		frame = new JFrame();
		frame.setBackground(new Color(102, 153, 204));
		frame.setResizable(false);
		frame.getContentPane().setForeground(new Color(153, 102, 255));
		frame.setBounds(100, 100, 729, 584);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//This is the main Pane
		frame.getContentPane().setBackground(new Color(53,65,81));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{48, 77, 310, 87, 0};
		gridBagLayout.rowHeights = new int[]{35, 29, 424, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		
		//This is the button Pane 
		JPanel buttonPanel = new JPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		buttonPanel.setBackground(new Color(0, 0, 0, 0));
		buttonPanel.setForeground(new Color(0, 0, 0, 0));
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.insets = new Insets(0, 0, 5, 0);
		gbc_buttonPanel.gridwidth = 3;
		gbc_buttonPanel.gridx = 1;
		gbc_buttonPanel.gridy = 1;
		frame.getContentPane().add(buttonPanel, gbc_buttonPanel);
		
		//Button for each column !!Starts at 0
		JButton btnColumn0 = new JButton("");
		btnColumn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.run(0); //Calls run with colum 0
			}
		});
		btnColumn0.setOpaque(false);
		btnColumn0.setIcon(arrow);
		btnColumn0.setBackground(new Color(0, 0, 0, 0));
		buttonPanel.add(btnColumn0);
		
		JButton btnColumn1 = new JButton("");
		buttonPanel.add(btnColumn1);
		btnColumn1.setBackground(new Color(0, 0, 0, 0));
		btnColumn1.setOpaque(false);
		btnColumn1.setIcon(arrow);
		
		JButton btnColumn2 = new JButton("");
		buttonPanel.add(btnColumn2);
		btnColumn2.setBackground(new Color(0, 0, 0, 0));
		btnColumn2.setOpaque(false);
		btnColumn2.setIcon(arrow);
		
		JButton btnColumn3 = new JButton("");
		buttonPanel.add(btnColumn3);
		btnColumn3.setBackground(new Color(0, 0, 0, 0));
		btnColumn3.setOpaque(false);
		btnColumn3.setIcon(arrow);
		
		JButton btnColumn4 = new JButton("");
		buttonPanel.add(btnColumn4);
		btnColumn4.setBackground(new Color(0, 0, 0, 0));
		btnColumn4.setOpaque(false);
		btnColumn4.setIcon(arrow);
		
		JButton btnColumn5 = new JButton("");
		buttonPanel.add(btnColumn5);
		btnColumn5.setBackground(new Color(0, 0, 0, 0));
		btnColumn5.setOpaque(false);
		btnColumn5.setIcon(arrow);
		
		JButton btnColumn6 = new JButton("");
		buttonPanel.add(btnColumn6);
		btnColumn6.setBackground(new Color(0, 0, 0, 0));
		btnColumn6.setOpaque(false);
		btnColumn6.setIcon(arrow);
		
		
		//This is where the board will be displayed
		JPanel boardPane = new JPanel();
		boardPane.setBackground(new Color(0, 0, 0, 0));
		boardPane.setForeground(new Color(0, 0, 0, 0));
		GridBagConstraints gbc_boardPane = new GridBagConstraints();
		gbc_boardPane.fill = GridBagConstraints.BOTH;
		gbc_boardPane.insets = new Insets(0, 0, 5, 0);
		gbc_boardPane.gridwidth = 3;
		gbc_boardPane.gridx = 1;
		gbc_boardPane.gridy = 2;
		frame.getContentPane().add(boardPane, gbc_boardPane);
		
		//New Game button 
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		//This should display the players turn 
		JLabel lblNewLabel = new JLabel("Player's Turn ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 3;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		frame.getContentPane().setBackground(new Color(53,65,81));
	}
	

	
}


