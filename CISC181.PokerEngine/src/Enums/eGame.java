package Enums;

import java.util.ArrayList;

public enum eGame {

	FiveStud(1,"Five Card Stud"),
	FiveStudOneJoker(2,"Five Card Stud One Joker"),
	FiveStudTwoJoker(3,"Five Card Stud Two Jokers"),
	TexasHoldEm(4,"Texas Hold 'em"),
	Omaha(5,"Omaha"),
	DeucesWild(6,"5 Card Deuces Wild"),
	AcesAndEights(7,"5 Card Aces and Eights"),
	SevenDraw(8,"Seven Card Draw");
	
	private int gameNbr;
	private String name;

	private eGame(final int gameNbr,final String name){
		this.gameNbr = gameNbr;
		this.setName(name);
	}
	
	public int getGame(){
		return gameNbr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
