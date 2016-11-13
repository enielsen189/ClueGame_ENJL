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
	private static boolean humanFinished;
	private static int playerIndex = 0;
	
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
		
		// Mouse click cycles through players
		class NextPlayerListener implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent event) {
				if (playerIndex == 0){
					humanFinished = false;
					humanNext(board);
				}
				else
				{
					computerNext(board);
				}
				
				//Increment player index
				if(playerIndex == 5){
					playerIndex = 0;
				}
				else{
					playerIndex++;	
				}
				
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		}
		
		//File Menu
		JMenu menu = new JMenu("File");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		
		createMenuLayout(menu);
		
		frame.setJMenuBar(menuBar);
		
		frame.add(board,BorderLayout.CENTER);
		frame.setVisible(true);
		
		//Add control panel
		JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new GridLayout(2,3));

		//WhoseTurn
		JPanel whoseTurn = new JPanel();
		whoseTurn.setLayout(new GridLayout(3,1));
		JLabel turnLabel = new JLabel("Whose turn?");
		JTextField turnText = new  JTextField(50);
		whoseTurn.add(turnLabel);
		whoseTurn.add(turnText);
		
		//Die
		JPanel die = new JPanel();
		JLabel rollLabel = new JLabel("Roll");
		JTextField rollText = new  JTextField(10);
		die.add(rollLabel);
		die.add(rollText);
		die.setBorder(new TitledBorder (new EtchedBorder(), "Die"));
		
		//Guess
		JPanel guess = new JPanel();
		//guess.setLayout(new GridLayout(2,1));
		JLabel guessLabel = new JLabel("Guess");
		JTextField guessText = new  JTextField(20);
		guess.add(guessLabel);
		guess.add(guessText);
		guess.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
		
		//GuessResult
		JPanel guessResult = new JPanel();
		//guessResult.setLayout(new GridLayout(2, 1));
		JLabel resultLabel = new JLabel("Response");
		JTextField resultText = new  JTextField(20);
		guessResult.add(resultLabel);
		guessResult.add(resultText);
		guessResult.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));

		
		//add buttons to the button panel
		JButton nextPlayerButton = new JButton("Next player");
		nextPlayerButton.addMouseListener(new NextPlayerListener());
		masterPanel.add(nextPlayerButton);
		JButton accusationButton = new JButton("Make an accusation");
		masterPanel.add(accusationButton);
		
		//add Labels to the button Panel
		masterPanel.add(whoseTurn);
		masterPanel.add(die);
		masterPanel.add(guess);
		masterPanel.add(guessResult);
		frame.add(masterPanel, BorderLayout.SOUTH);
		
		
		//Add shown cards
		CardGUI cardGui = new CardGUI(board.player.hand);
		frame.add(cardGui, BorderLayout.EAST);
		
		//Splash Screen to display message when game starts
		JOptionPane splash = new JOptionPane();
		splash.showMessageDialog(splash, "You are " + board.player.getName() + "! Press NextPlayer to begin playing!");
		
		
		
		/*while game not over
		while (gameOver == false) {
			System.out.println();			
		}*/
		
		
		
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
	
	public static void humanNext(Board board){
		System.out.println("Human");
	}
	
	public static void computerNext(Board board){
		System.out.println("Computer");
	}
	
	
	
}
