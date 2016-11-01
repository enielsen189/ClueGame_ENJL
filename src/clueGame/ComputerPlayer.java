package clueGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import clueGame.Board;

public class ComputerPlayer extends Player {
	public Set<Card> seenPeopleCards = new HashSet<Card>();
	public Set<Card> seenWeaponCards = new HashSet<Card>();
	public Set<BoardCell> visitedRooms = new HashSet<BoardCell>();
	private ArrayList<BoardCell> doorsInRange = new ArrayList<BoardCell>();
	private ArrayList<BoardCell> allLocations = new ArrayList<BoardCell>();
	
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		doorsInRange.clear();
		allLocations.clear();
		for (BoardCell bc : targets) {
			if (bc.isDoorway()) {
				doorsInRange.add(bc);
			}
		}
		
		if (doorsInRange.size() == 1) {		//Only 1 door that the computer player could get through
			if (!visitedRooms.contains(doorsInRange.get(0))) {	//if the player has not visited this room before, add it to totalRooms
				allLocations.add(doorsInRange.get(0));
			}
			else {
				for (BoardCell c: targets) {
					allLocations.add(c);							//if the player has visited this room before, all the targets should be added to allLocations so that they can all be randomly selected from
				}
			}
		}
		
		else if(doorsInRange.size() > 1) {		//if there is more than 1 door that the computer player can get through, check to see which ones have been visited before
			boolean doesContain = true;
			for (int i = 0; i < doorsInRange.size(); i++) {
				if (visitedRooms.contains(doorsInRange.get(i))) {
					//do nothing, continue through the loop
				}
				else {
					doesContain = false;
				}
			}
			if(doesContain) {	//if all of doorsInRange is included in visitedRooms, add all of the targets to allLocations
				for (BoardCell c: targets) {
					allLocations.add(c);		//if both rooms have been visited before, 
				}
			}
			//This next part MUST assume that there are only 2 doors in range, otherwise other else if statements must be added
			else if(visitedRooms.contains(doorsInRange.get(0))) {		//if only one door has been visited, add the OTHER one to the allLocations
				allLocations.add(doorsInRange.get(1));
			}
			else if(visitedRooms.contains(doorsInRange.get(1))) {
				allLocations.add(doorsInRange.get(0));
			}
			else {		//Will enter this if neither door has been entered. In that case, add both of them to allLocations
				allLocations.add(doorsInRange.get(0));
				allLocations.add(doorsInRange.get(1));
			}
		}
		
		else {		//if the size of doorsInRange is not 1 or greater than 1 (ie: there are no doors within reach of the player), add all locations to allLocations
			for (BoardCell c: targets) {
				allLocations.add(c);		//all targets should be in allLocations
			}
		}
		//Pick randomly from allLocations to get a cell to return. If there is only one item in allLocations, it will 'randomly' pick that one point since it is
		//the only point to pick from. However, if more than one point is in allLocations, one point is randomly chosen from the entire array.
		Random rand = new Random();
		BoardCell returnCell = allLocations.get(rand.nextInt(allLocations.size()));
		row = returnCell.row;
		column = returnCell.column;
		return returnCell;
	}
	
	public Solution makeAccusation(String person, String room, String weapon) {
		return new Solution(person, room, weapon);
	}
	
	public void createSuggestion(Board board) {
		if(board.getCellAt(row, column).isDoorway()) {
			Random rand = new Random();
			ArrayList<String> allNonSeenPeople = new ArrayList<String>();
			ArrayList<String> allNonSeenWeapons = new ArrayList<String>();
			String accusedPerson = "";
			String accusedWeapon = "";
			String accusedRoom = "";
			
			//set room to the room you are in
			char r;
			r = board.getCellAt(row, column).getInitial();
			accusedRoom = board.getLegend().get(r);
			
			//Check People
			for (Card c : board.personCards) {
				if (!seenPeopleCards.contains(c)){
					allNonSeenPeople.add(c.getName());
				}
			}
			if (allNonSeenPeople.size() == 1) {
				accusedPerson = allNonSeenPeople.get(0);
			}
			else if (allNonSeenPeople.size() > 1) {
				accusedPerson = allNonSeenPeople.get(rand.nextInt(allNonSeenPeople.size()));
			}
			
			//Check Weapons
			for (Card c : board.weaponCards) {
				if (!seenWeaponCards.contains(c)){
					allNonSeenWeapons.add(c.getName());
				}
			}
			if (allNonSeenWeapons.size() == 1) {
				accusedWeapon = allNonSeenWeapons.get(0);
			}
			else if (allNonSeenWeapons.size() > 1) {
				accusedWeapon = allNonSeenWeapons.get(rand.nextInt(allNonSeenWeapons.size()));
			}
			
			board.suggestion = new Solution(accusedPerson, accusedRoom, accusedWeapon);
		}
	}
}
