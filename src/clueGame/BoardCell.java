package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class BoardCell {
	public int row;
	public int column;
	public char initial;
	public DoorDirection direction = DoorDirection.NONE;
	
	//Drawing Variables
	public static final int CELL_LENGTH = 40;
	private Color color;
	
	public void setLocation(int i, int j) {
		row = i;
		column = j;
		
	}
	
	public BoardCell(){
		super();
	}
	
	@Override
	public String toString(){
		return "[" + row + ", " + column + "]";
	}
	
	public boolean isDoorway(){
		if (this.direction == DoorDirection.NONE){
			return false;
		}
		return true;
	}
	
	public DoorDirection getDoorDirection(){
		return direction;
	}
	
	public char getInitial() {
		return initial; 
	}
	
	// This method will draw a board cell
	public void draw(Graphics g){
		// Remove this line and add the color to when the board cell is read in
		if(initial == 'W'){
			color = Color.YELLOW;}
		else{
			color = Color.BLACK;
		}
		
		g.setColor(color);
		g.fillRect(CELL_LENGTH*row, CELL_LENGTH*column, CELL_LENGTH, CELL_LENGTH);
		
	}
}
