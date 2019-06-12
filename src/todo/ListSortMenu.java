package todo;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */
public class ListSortMenu extends JMenu {

	private ListOfTasks myList;
	private JMenuItem colorButton;
	private JMenuItem deadlineButton;
	private JMenuItem nameButton;

	public ListSortMenu(ListOfTasks myList){
		this.myList = myList;
		JMenu mainMenu = new JMenu("sortieren");
		add(mainMenu);
		colorButton = new JMenuItem("nach Farbe");
		mainMenu.add(colorButton);
		deadlineButton = new JMenuItem("nach Ablaufdatum");
		mainMenu.add(deadlineButton);
		nameButton = new JMenuItem("nach Name");
		mainMenu.add(nameButton);
		
	}


}