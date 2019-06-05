package todo;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */

public class MainMenuBar extends JMenuBar {

	private int filterButton;
	private int listCreateButton;
	private int restoreTaskButton;
	private int searchButton;
	private int showBinButton;

	public MainMenuBar() {
		JMenu mainMenu = new JMenu("Menü");
		add(mainMenu);

		JMenuItem menuAddList = new JMenuItem("Neue Liste erstellen");
		menuAddList.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		mainMenu.add(menuAddList);

		// JMenu submenuFilter = new ListSortMenu("Filter");

		// temp until ListSortMenu implemented
		JMenu submenuFilter = new JMenu("Filter");

		JMenuItem menuResetFilter = new JMenuItem("Filter zurücksetzten");
		submenuFilter.add(menuResetFilter);
		JMenuItem menuFilterColor1 = new JMenuItem("Color1");
		submenuFilter.add(menuFilterColor1);
		JMenuItem menuFilterColor2 = new JMenuItem("Color2");
		submenuFilter.add(menuFilterColor2);
		JMenuItem menuFilterColor3 = new JMenuItem("Color3");
		submenuFilter.add(menuFilterColor3);

		mainMenu.add(submenuFilter);

		JMenuItem menuSearch = new JMenuItem("Suchen");
		menuSearch.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		mainMenu.add(menuSearch);

		JMenuItem menuRestore = new JMenuItem("Aufgabe wiederherstellen");
		menuRestore.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		mainMenu.add(menuRestore);

	}
}
