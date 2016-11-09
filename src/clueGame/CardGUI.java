package clueGame;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class CardGUI extends JPanel{
	public CardGUI(Set<Card> c) {
		JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new GridLayout(3, 2));
		JLabel masterLabel = new JLabel("My Cards");
		//Go through cards and display which cards are in the players hand
		
		//People
		JPanel people = new JPanel();
		for (Card i : c) {
			if(i.getType() == CardType.PERSON) {
				JTextField peopleText = new JTextField(i.getName());
				peopleText.setEnabled(false);
				people.add(peopleText);
			}
		}
		people.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		
		//Rooms
		JPanel rooms = new JPanel();
		for (Card i : c) {
			if(i.getType() == CardType.ROOM) {
				JTextField roomText = new JTextField(i.getName());
				roomText.setEnabled(false);
				rooms.add(roomText);
			}
		}
		rooms.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		//Weapons
		JPanel weapons = new JPanel();
		for (Card i : c) {
			if(i.getType() == CardType.WEAPON) {
				JTextField weaponText = new JTextField(i.getName());
				weaponText.setEnabled(false);
				weapons.add(weaponText);
			}
		}
		weapons.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		
		//Add all of them to the master panel
		masterPanel.add(masterLabel);
		masterPanel.add(people);
		masterPanel.add(rooms);
		masterPanel.add(weapons);
	}
}
