package clueGame;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlPanelGUI extends JPanel{
	private Board board;
	private static boolean humanFinished = true;
	private static int playerIndex = 0;
	static JTextField turnText = new JTextField(10);
	static JTextField rollText = new  JTextField(10);
	private static int rollNumber;
	
	public ControlPanelGUI(Board board) {
		this.board = board;
		
		JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new GridLayout(2,3));

		//WhoseTurn
		JPanel whoseTurn = new JPanel();
		whoseTurn.setLayout(new GridLayout(3,1));
		JLabel turnLabel = new JLabel("Whose turn?");
		whoseTurn.add(turnLabel);
		whoseTurn.add(turnText);
		
		//Die
		JPanel die = new JPanel();
		JLabel rollLabel = new JLabel("Roll");
		die.add(rollLabel);
		die.add(rollText);
		die.setBorder(new TitledBorder (new EtchedBorder(), "Die"));
		
		//Guess
		JPanel guess = new JPanel();
		JLabel guessLabel = new JLabel("Guess");
		JTextField guessText = new  JTextField(20);
		guess.add(guessLabel);
		guess.add(guessText);
		guess.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
		
		//GuessResult
		JPanel guessResult = new JPanel();
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
		add(masterPanel);
		
		//add Labels to the button Panel
		masterPanel.add(whoseTurn);
		masterPanel.add(die);
		masterPanel.add(guess);
		masterPanel.add(guessResult);
	}
	
	// Mouse click cycles through players
			class NextPlayerListener implements MouseListener{

				@Override
				public void mouseClicked(MouseEvent event) {
					if (playerIndex == 0){
						if(humanFinished == false){
							//ERROR!
						}
						else{
						humanNext(board);
						}
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
						System.out.println(playerIndex);
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
			
			public static void humanNext(Board board){
				System.out.println("Human");
				humanFinished = false;
				turnText.setText(board.totalPlayers.get(playerIndex).getName());
				rollNumber = board.roll();
				rollText.setText(Integer.toString(rollNumber));
				System.out.println(playerIndex + "TURN");
				board.turn(rollNumber, playerIndex);
				
				//DO STUFF
				
				humanFinished = true;
			}
			
			public static void computerNext(Board board){
				System.out.println("Computer");
				turnText.setText(board.totalPlayers.get(playerIndex).getName());
				rollNumber = board.roll();
				rollText.setText(Integer.toString(rollNumber));
				System.out.println(playerIndex + "TURN");
				board.turn(rollNumber, playerIndex);
				System.out.println(board.totalPlayers.get(playerIndex).row + " " + board.totalPlayers.get(playerIndex).column);
			}
}
