package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Board;

public class DetectiveNotesGUI extends JDialog{
	private JComboBox<String> personGuess, roomGuess, weaponGuess;
	
	private Board board;
	
	public DetectiveNotesGUI(Board board){
		this.board = board;
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		createLayout();
	}
		
	private void createLayout() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		
		//Check Boxes
		JPanel personCheckBoxPanel = new JPanel();
		for(int i=0; i<board.personCards.size();i++){
			JCheckBox j = new JCheckBox(board.personCards.get(i).getName());
			personCheckBoxPanel.add(j);
		}
		personCheckBoxPanel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		
		JPanel roomCheckBoxPanel = new JPanel();
		for(int i=0; i<board.roomCards.size();i++){
			JCheckBox j = new JCheckBox(board.roomCards.get(i).getName());
			roomCheckBoxPanel.add(j);
		}
		roomCheckBoxPanel.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		
		JPanel weaponCheckBoxPanel = new JPanel();
		for(int i=0; i<board.weaponCards.size();i++){
			JCheckBox j = new JCheckBox(board.weaponCards.get(i).getName());
			weaponCheckBoxPanel.add(j);
		}
		weaponCheckBoxPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		
		
		//Drop Downs
		JPanel personGuessPanel = new JPanel();
		personGuess = new JComboBox<String>();
		for(int i=0; i<board.personCards.size(); i++){
			personGuess.addItem(board.personCards.get(i).getName());
		}
		personGuessPanel.add(personGuess);
		personGuessPanel.setBorder(new TitledBorder (new EtchedBorder(), "Person Guess"));
		
		JPanel roomGuessPanel = new JPanel();
		roomGuess = new JComboBox<String>();
		for(int i=0; i<board.roomCards.size(); i++){
			roomGuess.addItem(board.roomCards.get(i).getName());
		}
		roomGuessPanel.add(roomGuess);
		roomGuessPanel.setBorder(new TitledBorder (new EtchedBorder(), "Room Guess"));
		
		JPanel weaponGuessPanel = new JPanel();
		weaponGuess = new JComboBox<String>();
		for(int i=0; i<board.weaponCards.size(); i++){
			weaponGuess.addItem(board.weaponCards.get(i).getName());
		}
		weaponGuessPanel.add(weaponGuess);
		weaponGuessPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapon Guess"));
		
		add(panel);
		
		panel.add(personCheckBoxPanel);
		panel.add(personGuessPanel);
		
		panel.add(roomCheckBoxPanel);
		panel.add(roomGuessPanel);
		
		panel.add(weaponCheckBoxPanel);
		panel.add(weaponGuessPanel);
	}

}
