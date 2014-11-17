package Base;
import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import Action.Action;
import Play.Client;


public class Player {
	
	@XmlElement
	private UUID PlayerID;
	@XmlElement
	private int PlayerNbr;
	@XmlElement
	private String PlayerName;
	@XmlElement
	private Hand PlayerHand;
    /** Last action performed. */
    private Action action;  
    private Client client;
    private boolean Winner=false;
    
    
	public Player(String PlayerName, Client client)
	{
		this.PlayerID = UUID.randomUUID();
		this.PlayerName = PlayerName;
		this.client = client;
	}
	
	public UUID GetPlayerID()
	{
		return this.PlayerID;		
	}
	
	public void SetHand(Hand h)
	{
		PlayerHand = h;
	}
	
	public Hand GetHand()
	{
		return PlayerHand;
	}	

	public void SetPlayerNbr(int PlayerNbr)
	{
		this.PlayerNbr = PlayerNbr;
	}
	
	public int GetPlayerNbr()
	{
		return this.PlayerNbr;
	}
	
	public void SetPlayerName(String PlayerName)
	{
		this.PlayerName = PlayerName;
	}
	
	public String GetPlayerName()
	{
		return this.PlayerName;
	}
	

    
    /**
     * Returns the player's most recent action.
     * 
     * @return The action.
     */
    public Action getAction() {
        return action;
    }
    
    /**
     * Sets the player's most recent action.
     * 
     * @param action
     *            The action.
     */
    public void setAction(Action action) {
        this.action = action;
    }
    
    public void resetHand()
    {
    	this.PlayerHand = null;
    }
    
    /**
     * Returns the client.
     * 
     * @return The client.
     */
    public Client getClient() {
        return client;
    }

	public boolean isWinner() {
		return Winner;
	}

	public void setWinner(boolean winner) {
		Winner = winner;
	}
	
	public static void findWinner(ArrayList<Player> players){
		Collections.sort(players,new Comparator<Player>() {
			public int compare(Player p1, Player p2) {
				return Hand.HandRank.compare(p1.GetHand(), p2.GetHand());
			}
		});
		int i=0;
		do{
			players.get(i).setWinner(true);
			i=i+1;
		}while(Hand.HandRank.compare(players.get(i-1).GetHand(),players.get(i).GetHand())==0 && i<(players.size()-1));
		do{
			players.get(i).setWinner(false);
			i+=1;
		}while(i<players.size());
	}
    
    
    
}
