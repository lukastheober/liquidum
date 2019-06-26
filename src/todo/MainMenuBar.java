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

	private JMenuItem filterButton1, filterButton2, filterButton3, filterButton4, filterButton5, filterButton6, 
	listCreateButton, searchButton, showBinButton, menuResetFilter;

	public MainMenuBar() {
		JMenu mainMenu = new JMenu("Menü");
		add(mainMenu);

		listCreateButton = new JMenuItem("Liste erstellen");
		mainMenu.add(listCreateButton);
		JMenu submenuFilter = new JMenu("Filtern");
		menuResetFilter = new JMenuItem("Filter zurücksetzten");
		submenuFilter.add(menuResetFilter);
		filterButton1 = new JMenuItem("Weiß");
		submenuFilter.add(filterButton1);
		filterButton2 = new JMenuItem("Blau");
		submenuFilter.add(filterButton2);
		filterButton3 = new JMenuItem("Grün");
		submenuFilter.add(filterButton3);
		filterButton4 = new JMenuItem("Rot");
		submenuFilter.add(filterButton4);
		filterButton5 = new JMenuItem("Orange");
		submenuFilter.add(filterButton5);
		filterButton6 = new JMenuItem("Pink");
		submenuFilter.add(filterButton6);
		mainMenu.add(submenuFilter);

		searchButton = new JMenuItem("Suchen");
		mainMenu.add(searchButton);

		showBinButton = new JMenuItem("Papierkorb anzeigen");
		mainMenu.add(showBinButton);
	}

	public JMenuItem getListCreateButton() {
		return listCreateButton;
	}

	public JMenuItem getSearchButton() {
		return searchButton;
	}

	public JMenuItem[] getFilterButtons() {
		JMenuItem[] list = { filterButton1, filterButton2, filterButton3, filterButton4, filterButton5, filterButton6};
		return list;
	}
	
	public JMenuItem getResetFilter() {
		return menuResetFilter;
	}
	
	public JMenuItem getShowBinButton() {
		return showBinButton;
	}
}
