package Poker.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import Base.Card;
import Base.Deck;
import Base.Hand;
import Base.Player;
import Base.Rule;
import Enums.eGame;
import Enums.eRank;


public class PlayGame {
	private eGame gme;
	private ArrayList<Player> players = new ArrayList<Player>();
	Deck d;
	Rule r;
	boolean newGame=false;

	public PlayGame(eGame gme) {
		this.gme = gme;
	}

	public eGame GetGame() {
		return this.gme;
	}

	public void AddPlayer(Player p) {
		players.add(p);
	}

	public void run() {

		startNewGame();

	}

	public void startNewGame() {
		r = new Rule(gme);
		// Set a new deck d
		d = new Deck(r.GetNumberOfJokers(),r.GetRuleCards());
		// play the hand
		playHand();
	}

	public void prePlayHand() {
		if(d.getTotalCards()<r.numberCardsForHand(players.size())|newGame){
			startNewGame();
			newGame=false;
		}else{
			playHand();
		}

	}

	public void playHand(){
		// Reset each player's hand
		for (Player p : players) {
			p.resetHand();
		}

		Hand river = new Hand();
		for(int i=0;i<r.GetCommunityCardsCount();i++){
			Card c = d.drawFromDeck();
			river.AddCardToHand(c);
		}
		players.get(0).getClient().boardUpdated (river.getCards() );

		for (Player p :players){
			// Create a hand for each player
			Hand h = new Hand();
			for (int i = 0; i < r.GetNumberOfCards(); i++) {
				Card c = d.drawFromDeck();
				h.AddCardToHand(c);
			}
			switch(gme){
			case DeucesWild:
			case AcesAndEights:
			case FiveStudOneJoker:
			case FiveStudTwoJoker:
			case FiveStud:
				h.HandleJokerWilds();
				p.SetHand(h);
				break;
			case SevenDraw:
				// eval all possible sets of 5 cards 
				ArrayList<Hand> possibilities = powerSetsOfArrayList(h,5);
				System.out.println("this is " +possibilities.size());
				for(Hand han:possibilities){
					han.EvalHand();
				}
				//possibilities.sort(Hand.HandRank);
				h.setHandRankOff(possibilities.get(0));
				p.SetHand(h);
				break;

			case TexasHoldEm:
				//eval all sets of hands between hand and river
				Hand ahand = new Hand();
				for(Card c:river.getCards()){
					ahand.AddCardToHand(c);
				}
				for(Card c:h.getCards()){
					ahand.AddCardToHand(c);
				}
				ArrayList<Hand> possibilitiesT = powerSetsOfArrayList(ahand,5);
				for(Hand han:possibilitiesT){
					han.EvalHand();
				}
				//possibilitiesT.sort(Hand.HandRank);
				h.setHandRankOff(possibilitiesT.get(0));
				p.SetHand(h);
				break;
				
			case Omaha:
				//create all sets of two in hand 
				//create all sets of three in river 
				//eval all possible combinations of the two sets of sets
				ArrayList<Hand> handposs = powerSetsOfArrayList(h,2);
				ArrayList<Hand> riverposs = powerSetsOfArrayList(river,3);
				ArrayList<Hand> hrposs = new ArrayList<Hand>(); 
				for (Hand riv:riverposs){
					for(Hand handb: handposs){
						Hand temp = new Hand();
						for(Card c1:riv.getCards()){
							temp.AddCardToHand(c1);
						}
						for(Card c2:handb.getCards()){
							temp.AddCardToHand(c2);
						}
						temp.EvalHand();
						hrposs.add(temp);
					}
				}
				//hrposs.sort(Hand.HandRank);
				h.setHandRankOff(hrposs.get(0));
				p.SetHand(h);
				
			default:break;
			}
		}


		// Players have hands, call the playerUpdated method to set the
		// screen
		updatePlayerStatus(players);
	}



	private ArrayList<Hand> powerSetsOfArrayList(Hand hand, int j) {
		ArrayList<Card> h = hand.getCards();
		ArrayList<Hand> arr =new ArrayList<Hand>();
		if(j<h.size()){
			for(int i=0;i<h.size();i++){
				Hand temp = new Hand();
				for(int k=0;k<h.size();k++){
					if(k==i){
					}else{
						temp.AddCardToHand(h.get(k));
					}
				}

				ArrayList<Hand> temp1=powerSetsOfArrayList(temp,j);
				if(temp1!=null){
					System.out.println("this is two "+ temp1.size());
					arr.addAll(temp1);
				}

			}
			return arr;
		}else{
			arr.add(hand);
			return arr;
		}
	}


	private void updatePlayerStatus(ArrayList<Player> players){
		Player.findWinner(players);
		for (Player p : players) {
			p.getClient().playerUpdated(p);
			p.getClient().playerActed(p);
		}
	}

	public void setGme(eGame gme){
		this.gme = gme;
		r= new Rule(gme);
		newGame=true;

	}

}
