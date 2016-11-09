package clueGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu{
	private Board board;

	public FileMenu(Board board){
		this.board = board;
		createLayout();
	}
	
	public void createLayout(){
		JMenu menu = new JMenu("File");
		
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
