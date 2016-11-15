package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ClueGame {
	public static Board board;
	private static boolean gameOver = false;

	
	public static void main(String[] args) {
		ClueGame clueGame = new ClueGame();
		
		JFrame frame = new JFrame("Clue Game Board");
		
		frame.setSize(1500, 1000);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		board = Board.getInstance();
		board.setConfigFiles("data/ENTM_ClueMap.csv", "data/ENTM_Legend.txt");
		board.setWPConfigFiles("data/ENTM_CluePlayers.txt", "data/ENTM_ClueWeapons.txt");
		board.initialize();
		board.deal();
		
		//Add File Menu
		JMenu menu = new JMenu("File");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		
		clueGame.createMenuLayout(menu);
		
		frame.setJMenuBar(menuBar);
		
		frame.add(board,BorderLayout.CENTER);
		
		//Add Control Panel GUI
		ControlPanelGUI gui = new ControlPanelGUI(board);
		frame.add(gui,BorderLayout.SOUTH);
		
		//Add Card Panel GUI
		CardGUI cardGui = new CardGUI(board.player.hand);
		frame.add(cardGui,BorderLayout.EAST);

		//Splash Screen to display message when game starts
		JOptionPane splash = new JOptionPane();
		splash.showMessageDialog(splash, "You are " + board.player.getName() + "! Press NextPlayer to begin playing!");
		
		frame.setVisible(true);
	}
	
	public void createMenuLayout(JMenu menu){
		// Create notes button
		JMenuItem notes = new JMenuItem("Show Notes");
		DetectiveNotesGUI gui = new DetectiveNotesGUI(board);
		
		class NotesListener implements ActionListener {
			public void actionPerformed(ActionEvent e){
				gui.setVisible(true);
			}
		}
		notes.addActionListener(new NotesListener());
		
		// Create exit button
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
