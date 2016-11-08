package clueGame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ClueGame {
	public static Board board;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Clue Game Board");
		
		frame.setSize(1100, 860);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		board = Board.getInstance();
		board.setConfigFiles("data/ENTM_ClueMap.csv", "data/ENTM_Legend.txt");
		board.setWPConfigFiles("data/ENTM_CluePlayers.txt", "data/ENTM_ClueWeapons.txt");
		board.initialize();
		board.deal();
		
		frame.add(board,BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
}
