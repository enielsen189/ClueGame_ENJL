package clueGame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ClueGame {
	public static Board board;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Clue Game Board");
		
		frame.setSize(1500, 1000);
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
		
		//Add control panel
		ControlPanelGUI controlGui = new ControlPanelGUI();
		frame.add(controlGui, BorderLayout.SOUTH);
		
		//Add shown cards
		CardGUI cardGui = new CardGUI(board.player.hand);
		frame.add(cardGui, BorderLayout.WEST);
		
		//Splash Screen to display message when game starts
		JOptionPane splash = new JOptionPane();
		splash.showMessageDialog(splash, "You are " + board.player.getName() + "! Press NextPlayer to begin playing!");
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
