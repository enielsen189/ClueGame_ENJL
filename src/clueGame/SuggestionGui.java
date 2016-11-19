package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.ControlPanelGUI.NextPlayerListener;

public class SuggestionGui extends JDialog{
	private JComboBox<String> personGuess, weaponGuess;
	private JTextField currentRoom;
	private JButton submitButton;
	private JButton cancelButton;
	public boolean cancel =  false;
	private String personString;
	private String weaponString;
	private String roomString;
	private Solution guess;
	private Card shownCard;
	
	private Board board;
	
	public SuggestionGui(Board board){
		this.board = board;
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		createLayout();
	}
		
	private void createLayout() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		
		
		//Drop Downs
		JPanel personGuessPanel = new JPanel();
		personGuess = new JComboBox<String>();
		for(int i=0; i<board.personCards.size(); i++){
			personGuess.addItem(board.personCards.get(i).getName());
		}
		personGuessPanel.add(personGuess);
		personGuessPanel.setBorder(new TitledBorder (new EtchedBorder(), "Person"));
		
	    personGuess.addActionListener(
	        new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	                JComboBox combo = (JComboBox)e.getSource();
	                personString = (String)combo.getSelectedItem();
	            }
	        }            
	);
		
		
		JPanel weaponGuessPanel = new JPanel();
		weaponGuess = new JComboBox<String>();
		for(int i=0; i<board.weaponCards.size(); i++){
			weaponGuess.addItem(board.weaponCards.get(i).getName());
		}
		weaponGuessPanel.add(weaponGuess);
		weaponGuessPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapon"));
	    weaponGuess.addActionListener(
		        new ActionListener(){
		            public void actionPerformed(ActionEvent e){
		                JComboBox combo = (JComboBox)e.getSource();
		                weaponString = (String)combo.getSelectedItem();
		            }
		        }            
		);
		
		//Room text field
		currentRoom = new JTextField();
		currentRoom.setBorder(new TitledBorder (new EtchedBorder(), "Room"));
		char i;
		i = board.getCellAt(board.totalPlayers.get(board.playerIndex).getRow(),board.totalPlayers.get(board.playerIndex).getColumn()).getInitial();
		currentRoom.setText(board.getRooms().get(i));
		roomString = board.getRooms().get(i);
		
		//Add Buttons
		submitButton = new JButton("Submit");
		submitButton.addMouseListener(new SubmitListener());
		cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new CancelListener());
		
		
		
		panel.add(currentRoom);
		panel.add(personGuessPanel);
		panel.add(weaponGuessPanel);
		panel.add(submitButton);
		panel.add(cancelButton);
		
		add(panel);
	}
	
	public Card getShownCard() {
		return shownCard;
	}
	
	class SubmitListener implements MouseListener{
		public void mouseClicked(MouseEvent event) {
			//submit the suggestion displayed
			//handle suggestion
			guess = new Solution(personString, roomString, weaponString);
			
			// Move accused player to the the accuser's location
			int movePlayerIndex = 0;
			for(int i = 0; i<board.totalPlayers.size(); i++){
				if(guess.getPerson().equals(board.totalPlayers.get(i).getName())){
					movePlayerIndex = i;
					break;
				}
			}
			board.totalPlayers.get(movePlayerIndex).setLocation(board.totalPlayers.get(0).getRow(), board.totalPlayers.get(0).getColumn());
			
			shownCard = board.handleSuggestion(guess);
			
			if (shownCard == null) {
				Card noShownCard = new Card("No card shown", CardType.OTHER);
				board.setCurrentShownCard(noShownCard);
			}
			else {
			
				board.setCurrentShownCard(shownCard);
			}
			
			board.setCurrentSuggestion(guess);
			
			dispose();
			
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	class CancelListener implements MouseListener{
		public void mouseClicked(MouseEvent event) {
			//Quit the dialogue box
			dispose();
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
}
