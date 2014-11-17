package Poker.GUI;

import java.awt.EventQueue;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

import Action.Action;
import Base.Card;
import Base.Player;
import Base.Rule;
import Base.Table;
import Enums.eGame;
import Play.Client;

public class Main extends JFrame implements Client {

	/** The table. */
	private Table tbl;

	private Map<String, Player> players;

	/** The GridBagConstraints. */
	private GridBagConstraints gc;

	/** The board panel. */
	private BoardPanel boardPanel;

	/** The control panel. */
	private ControlPanel controlPanel;

	/** The player panels. */
	private Map<String, PlayerPanel> playerPanels;

	/** The menu bar */
	private JMenuBar menuBar;

	/** The menu */
	private JMenu mnGame;

	private PlayGame pGame;

	private Rule rle;

	private eGame game;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Main window = new Main();
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		tbl = new Table();
		initializeMenu();

		game=eGame.FiveStudTwoJoker;
		setWindowForGame();
		runGameTime();

	}

	private void setWindowForGame() {
		rle = new Rule(game);
		pGame = new PlayGame(game);


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(UIConstants.TABLE_COLOR);
		setLayout(new GridBagLayout());




		gc = new GridBagConstraints();
		controlPanel = new ControlPanel();

		boardPanel = new BoardPanel(controlPanel, rle);
		addComponent(boardPanel, 1, 1, 1, 1);

		players = new LinkedHashMap<String, Player>();
		Player p1 = new Player("Ham", this);
		players.put(p1.GetPlayerID().toString(), p1);

		Player p2 = new Player("Jao", this);
		players.put(p2.GetPlayerID().toString(), p2);

		Player p3 = new Player("Issa", this);
		players.put(p3.GetPlayerID().toString(), p3);

		Player p4 = new Player("Kass", this);
		players.put(p4.GetPlayerID().toString(), p4);

		for (Player player : players.values()) {
			pGame.AddPlayer(player);
		}

		playerPanels = new HashMap<String, PlayerPanel>();

		int i = 0;
		for (Player player : players.values()) {
			PlayerPanel panel = new PlayerPanel(tbl, rle, player);
			playerPanels.put(player.GetPlayerName(), panel);
			switch (i++) {
			case 0:
				
				addComponent(panel, 1, 0, 1, 1);
				break;
			case 1:
				
				addComponent(panel, 2, 1, 1, 1);
				break;
			case 2:
				
				addComponent(panel, 1, 2, 1, 1);
				break;
			case 3:
				
				addComponent(panel, 0, 1, 1, 1);
				break;
			default:
				
			}
		}

		// Show the frame.
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void runGameTime(){
		pGame.run();
		while(true){
			Action selectedAction = controlPanel.waitForAction();
			switch(selectedAction.getName()){
			case "Start":
				pGame.prePlayHand();
				break;
			case "End":
				System.exit(0);
				break;
			default:
				System.out.println("something is wrong");
				break;
			}

		}
	}

	private void initializeMenu() {
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		ButtonGroup menuGroup = new ButtonGroup();

		for(final eGame gme:eGame.values()){
			JRadioButtonMenuItem rdbtnmntmCardJoker = new JRadioButtonMenuItem(gme.getName());
			rdbtnmntmCardJoker.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					game = gme;
					rle = new Rule(gme);
					pGame.setGme(gme);
					resetFrame();
					repaint();
				}
			});
			mnGame.add(rdbtnmntmCardJoker);
			menuGroup.add(rdbtnmntmCardJoker);
		}

	}

	private void resetFrame() {
		boardPanel.updateCardNum(rle.GetCommunityCardsCount());
		for(PlayerPanel p:playerPanels.values()){
			p.updateCardNum(rle.GetNumberOfCards());
			p.setWinnerVisible(false);
			p.deleteLabel();
		}

	}

	private void addComponent(Component component, int x, int y, int width,
			int height) {
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = width;
		gc.gridheight = height;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		gc.weightx = 0.0;
		gc.weighty = 0.0;
		getContentPane().add(component, gc);
	}

	@Override
	public void messageReceived(String message) {
		boardPanel.setMessage(message);
		boardPanel.waitForUserInput();

	}

	@Override
	public void joinedTable(List<Player> players) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handStarted(Player dealer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actorRotated(Player actor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerUpdated(Player player) {
		System.out.println("PlayerUpdated in Main");
		PlayerPanel playerPanel = playerPanels.get(player.GetPlayerName());
		if (playerPanel != null) {
			playerPanel.update(player);
		}

	}

	@Override
	public void boardUpdated(List<Card> cards) {
		if(cards!=null){
			boardPanel.updatePanel(cards);
		}

	}

	@Override
	public void playerActed(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public Action act(Set<Action> allowedActions) {
		// TODO Auto-generated method stub
		return null;
	}


}
