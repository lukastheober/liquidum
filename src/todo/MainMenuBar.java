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
			filterButton7, listCreateButton, searchButton, showBinButton, menuResetFilter;

	public MainMenuBar() {
		JMenu mainMenu = new JMenu("Menü");
		add(mainMenu);

		listCreateButton = new JMenuItem("Liste erstellen");
		mainMenu.add(listCreateButton);
		JMenu submenuFilter = new JMenu("Filtern");
		menuResetFilter = new JMenuItem("Filter zurücksetzten");
		submenuFilter.add(menuResetFilter);
		filterButton1 = new JMenuItem("Rot");
		submenuFilter.add(filterButton1);
		filterButton2 = new JMenuItem("Grün");
		submenuFilter.add(filterButton2);
		filterButton3 = new JMenuItem("Blau1");
		submenuFilter.add(filterButton3);
		filterButton4 = new JMenuItem("Blau2");
		submenuFilter.add(filterButton4);
		filterButton5 = new JMenuItem("Blau3");
		submenuFilter.add(filterButton5);
		filterButton6 = new JMenuItem("Blau4");
		submenuFilter.add(filterButton6);
		filterButton7 = new JMenuItem("Blau5");
		submenuFilter.add(filterButton7);
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

	public JMenuItem[] getFilterButton() {
		JMenuItem[] list = { filterButton1, filterButton2, filterButton3, filterButton4, filterButton5, filterButton6,
				filterButton7 };
		return list;
	}
	
	public JMenuItem getResetFilter() {
		return menuResetFilter;
	}
}
