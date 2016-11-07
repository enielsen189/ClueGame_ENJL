package clueGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class BoardCell {
	public int row;
	public int column;
	public char initial;
	public DoorDirection direction = DoorDirection.NONE;
	
	//Drawing Variables
	public static final int CELL_LENGTH = 40;
	public static final int DOOR_HEIGHT = 40;
	public static final int DOOR_WIDTH = 5;
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
		g.fillRect(CELL_LENGTH*column, CELL_LENGTH*row, CELL_LENGTH, CELL_LENGTH);
		
		g.setColor(Color.BLACK);
		g.drawRect(CELL_LENGTH*column, CELL_LENGTH*row, CELL_LENGTH, CELL_LENGTH);
		
		if(direction != DoorDirection.NONE){
			color = Color.BLUE;
			g.setColor(color);
			switch (direction){
			case DOWN:
				g.fillRect(column, row, DOOR_HEIGHT, DOOR_WIDTH);
				break;
			case UP:
				/*board[numRows][i].direction = DoorDirection.UP;
				break;
			case LEFT:
				board[numRows][i].direction = DoorDirection.LEFT;
				break;
			case RIGHT:
				board[numRows][i].direction = DoorDirection.RIGHT;
				break;*/
			}
			}
		
		
		
	}
}
