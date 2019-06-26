package todo;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */
public class ListSortMenu extends JMenuBar {

	private ListOfTasks myList;
	private JMenuItem colorButton;
	private JMenuItem deadlineButton;
	private JMenuItem nameButton;

	public ListSortMenu(ListOfTasks myList){
		this.myList = myList;
		this.setPreferredSize(new Dimension(70 ,20));
		JMenu mainMenu = new JMenu("Sortieren");
		mainMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mainMenu.setPreferredSize(new Dimension(70, 20));
		add(mainMenu);
		colorButton = new JMenuItem("nach Farbe");
		mainMenu.add(colorButton);
		deadlineButton = new JMenuItem("nach Ablaufdatum");
		mainMenu.add(deadlineButton);
		nameButton = new JMenuItem("nach Name");
		mainMenu.add(nameButton);
		
	}

	public ListOfTasks getMyList() {
		return myList;
	}

	public JMenuItem getColorButton() {
		return colorButton;
	}

	public JMenuItem getDeadlineButton() {
		return deadlineButton;
	}

	public JMenuItem getNameButton() {
		return nameButton;
	}


}