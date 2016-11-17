package clueGame;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlPanelGUI extends JPanel{
	private Board board;
	private static int playerIndex;
	private static int rollNumber;
	static JTextField turnText = new JTextField(10);
	static JTextField rollText = new  JTextField(10);
	static JTextField resultText;
	
	public ControlPanelGUI(Board board) {
		this.board = board;
		playerIndex = board.totalPlayers.size() - 1;
		
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
		resultText = new  JTextField(20);
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
		public void mouseClicked(MouseEvent event) {
			//Increment player index
			playerIndex = (playerIndex + 1) % board.totalPlayers.size();
			//Initiate turn
			if(board.totalPlayers.get(0).isFinished){
				nextPlayer(board);
			}
			else{
				JOptionPane.showMessageDialog(null, "Select a valid location.", "Your turn is not over!", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
			
	// Roll die, update GUI text fields, and initiate turn for player.
	public void nextPlayer(Board board){
		//Roll die
		rollNumber = board.roll();
		
		//Update Text
		turnText.setText(board.totalPlayers.get(playerIndex).getName());
		rollText.setText(Integer.toString(rollNumber));

		
		//Take player turn
		board.turn(rollNumber, playerIndex);
		if (board.getCurrentShownCard() != null) {
			resultText.setText(board.getCurrentShownCard().getName());
		}
		else {
			resultText.setText("");
		}
	}
}
