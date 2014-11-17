package Poker.GUI;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
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
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		JRadioButtonMenuItem rdbtnmntmCardJoker = new JRadioButtonMenuItem("5 Card Joker");
		rdbtnmntmCardJoker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// set new rules and restart game
			}
		});
		mnGame.add(rdbtnmntmCardJoker);

		JRadioButtonMenuItem rdbtnmntmCardDueces = new JRadioButtonMenuItem("5 Card Dueces Wild");
		rdbtnmntmCardDueces.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// set new rules and restart game
				
			}
		});
		mnGame.add(rdbtnmntmCardDueces);
		
		JRadioButtonMenuItem rdbtnmntmCardDraw = new JRadioButtonMenuItem("5 Card Draw");
		rdbtnmntmCardDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// set new rules and restart game
			}
		});
		mnGame.add(rdbtnmntmCardDraw);
		

		ButtonGroup menuGroup = new ButtonGroup();
		menuGroup.add(rdbtnmntmCardDraw);
		menuGroup.add(rdbtnmntmCardJoker);

	}

}
/*
//5 card joker
JRadioButtonMenuItem rdbtnmntmCardJoker = new JRadioButtonMenuItem("5 Card Joker");
rdbtnmntmCardJoker.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {

	}
});
mnGame.add(rdbtnmntmCardJoker);

//5 card Dueces Wild
JRadioButtonMenuItem rdbtnmntmCardDueces = new JRadioButtonMenuItem("5 Card Dueces Wild");
rdbtnmntmCardDueces.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		// set new rules and restart game

	}
});
mnGame.add(rdbtnmntmCardDueces);


//5 card Draw
JRadioButtonMenuItem rdbtnmntm5CardDraw = new JRadioButtonMenuItem("5 Card Draw");
rdbtnmntm5CardDraw.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		// set new rules and restart game
	}
});
mnGame.add(rdbtnmntm5CardDraw);

//7 card Draw
JRadioButtonMenuItem rdbtnmntm7CardDraw = new JRadioButtonMenuItem("7 Card Draw");
rdbtnmntm7CardDraw.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		// set new rules and restart game
	}
});
mnGame.add(rdbtnmntm7CardDraw);

//Texas Hold 'em
JRadioButtonMenuItem rdbtnmntmTexas = new JRadioButtonMenuItem("Texas Hold 'em");
rdbtnmntmTexas.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		// set new rules and restart game
	}
});
mnGame.add(rdbtnmntmTexas);

//Texas Hold 'em
JRadioButtonMenuItem rdbtnmntmOmaha = new JRadioButtonMenuItem("Omaha");
rdbtnmntmOmaha.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		// set new rules and restart game
	}
});
mnGame.add(rdbtnmntmOmaha);

ButtonGroup menuGroup = new ButtonGroup();
menuGroup.add(rdbtnmntmCardDueces);
menuGroup.add(rdbtnmntmCardJoker);
 */
