package clueGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;

public class BoardCell {
	public int row;
	public int column;
	private char initial;
	public DoorDirection direction = DoorDirection.NONE;
	
	//Drawing Variables
	public static final int CELL_LENGTH = 40;
	public static final int DOOR_HEIGHT = 40;
	public static final int DOOR_WIDTH = 5;
	private Color color;
	private Boolean highlight = false;
	
	public void setLocation(int i, int j) {
		row = i;
		column = j;
		
	}
	
	public BoardCell(){
		super();
	}
	
	public BoardCell(int row, int col){
		super();
		this.row = row;
		column = col;
	}
	
	@Override
	public String toString(){
		return "[" + row + ", " + column + "]";
	}
	
	public boolean isDoorway(){
		if (this.direction == DoorDirection.NONE){
			//System.out.println("Returning false");
			return false;
		}
		//System.out.println("Returning true");
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
			g.setColor(Color.WHITE);
			g.fillRect(CELL_LENGTH*column, CELL_LENGTH*row, CELL_LENGTH, CELL_LENGTH);
			g.setColor(Color.BLACK);
			g.drawRect(CELL_LENGTH*column, CELL_LENGTH*row, CELL_LENGTH, CELL_LENGTH);
			}
		else{
			g.setColor(Color.GRAY);
			g.fillRect(CELL_LENGTH*column, CELL_LENGTH*row, CELL_LENGTH, CELL_LENGTH);
		}
		
		if(highlight){
			g.setColor(Color.CYAN);
			g.fillRect(CELL_LENGTH*column, CELL_LENGTH*row, CELL_LENGTH, CELL_LENGTH);
		}

		
		if(direction != DoorDirection.NONE){
			color = Color.BLUE;
			g.setColor(color);
			switch (direction){
			case DOWN:
				g.fillRect(CELL_LENGTH*column, CELL_LENGTH*row+(CELL_LENGTH-DOOR_WIDTH), DOOR_HEIGHT, DOOR_WIDTH);
				break;
			case UP:
				g.fillRect(CELL_LENGTH*column, CELL_LENGTH*row, DOOR_HEIGHT, DOOR_WIDTH);
				break;
			case LEFT:
				g.fillRect(CELL_LENGTH*column, CELL_LENGTH*row, DOOR_WIDTH, DOOR_HEIGHT);
				break;
			case RIGHT:
				g.fillRect(CELL_LENGTH*column+(CELL_LENGTH-DOOR_WIDTH), CELL_LENGTH*row, DOOR_WIDTH, DOOR_HEIGHT);
				break;
			}
		}
	}
	
	public boolean isWithin(int x, int y) {
		Rectangle rect = new Rectangle(CELL_LENGTH*column, CELL_LENGTH*row, CELL_LENGTH, CELL_LENGTH);
		return (rect.contains(new Point(x, y)));

	}
	
	public void updateHighlight(Boolean b){
		highlight = b;
	}

	public void setInitial(char charAt) {
		initial = charAt;
		
	}
	
}
