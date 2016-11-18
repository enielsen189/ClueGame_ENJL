package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.SuggestionGui.CancelListener;
import clueGame.SuggestionGui.SubmitListener;

public class AccusationGui extends JDialog{
	private JComboBox<String> personAcc, weaponAcc, roomAcc;
	private JButton submitButton;
	private JButton cancelButton;
	private String personAccString;
	private String weaponAccString;
	private String roomAccString;
	private Solution accusation;
	
	private Board board; 
	
	public AccusationGui(Board board){
		this.board = board;
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		createLayout();
	}
	
	private void createLayout() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		
		//Drop Downs
		//PERSON
		JPanel personAccPanel = new JPanel();
		personAcc = new JComboBox<String>();
		for(int i=0; i<board.personCards.size(); i++){
			personAcc.addItem(board.personCards.get(i).getName());
		}
		personAccPanel.add(personAcc);
		personAccPanel.setBorder(new TitledBorder (new EtchedBorder(), "Person"));
				
		personAcc.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						JComboBox combo = (JComboBox)e.getSource();
			            personAccString = (String)combo.getSelectedItem();
			            }
			        }            
			);
		
		//WEAPON
		JPanel weaponAccPanel = new JPanel();
		weaponAcc = new JComboBox<String>();
		for(int i=0; i<board.weaponCards.size(); i++){
			weaponAcc.addItem(board.weaponCards.get(i).getName());
		}
		weaponAccPanel.add(weaponAcc);
		weaponAccPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapon"));
				
		weaponAcc.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						JComboBox combo = (JComboBox)e.getSource();
			            weaponAccString = (String)combo.getSelectedItem();
			            }
			        }            
			);
		
		//ROOM
		JPanel roomAccPanel = new JPanel();
		roomAcc = new JComboBox<String>();
		for(int i=0; i<board.roomCards.size(); i++){
			roomAcc.addItem(board.roomCards.get(i).getName());
		}
		roomAccPanel.add(roomAcc);
		roomAccPanel.setBorder(new TitledBorder (new EtchedBorder(), "Room"));
				
		roomAcc.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						JComboBox combo = (JComboBox)e.getSource();
			            roomAccString = (String)combo.getSelectedItem();
			            }
			        }            
			);
		
		//Add Buttons
		submitButton = new JButton("Submit");
		submitButton.addMouseListener(new SubmitAccListener());
		cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new CancelListener());
		
		
		panel.add(roomAccPanel);
		panel.add(personAccPanel);
		panel.add(weaponAccPanel);
		panel.add(submitButton);
		panel.add(cancelButton);
		
		add(panel);
		
		
		
	}
	class SubmitAccListener implements MouseListener{
		public void mouseClicked(MouseEvent event) {
			accusation = new Solution(personAccString, roomAccString, weaponAccString);
			
			if (board.checkAccusation(accusation) == true) {
				JOptionPane.showMessageDialog(null, "You have won the game!!! The correct cards were: " + personAccString + " with the " + weaponAccString + " in the " + roomAccString + ".", "Winner!", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "Sorry, your accusation was incorrect.", "Sorry not sorry!", JOptionPane.INFORMATION_MESSAGE);
			}
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
