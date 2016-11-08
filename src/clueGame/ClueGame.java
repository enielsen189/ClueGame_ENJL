package clueGame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
		
		//File Menu
		JMenu menu = new JMenu("File");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		
		createMenuLayout(menu);
		
		frame.setJMenuBar(menuBar);
		
		frame.add(board,BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	public static void createMenuLayout(JMenu menu){
		// Create notes
		JMenuItem notes = new JMenuItem("Show Notes");
		DetectiveNotesGUI gui = new DetectiveNotesGUI(board);
		
		class NotesListener implements ActionListener {
			public void actionPerformed(ActionEvent e){
				gui.setVisible(true);
			}
		}
		notes.addActionListener(new NotesListener());
		
		// Create exit
		JMenuItem exit = new JMenuItem("Exit");
		class ExitListener implements ActionListener {
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		}
		exit.addActionListener(new ExitListener());
		
		menu.add(notes);
		menu.add(exit);
	}
}
