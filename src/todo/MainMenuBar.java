package todo;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;

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
		Font font = new Font("Tahoma", Font.PLAIN, 15);
		JMenu mainMenu = new JMenu("Men"  + '\u00FC');
		mainMenu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(mainMenu);

		listCreateButton = new JMenuItem("Liste erstellen");
		listCreateButton.setFont(font);
		mainMenu.add(listCreateButton);
		
		JMenu submenuFilter = new JMenu("Filtern");
		submenuFilter.setFont(font);
		
		menuResetFilter = new JMenuItem("Filter zur" + '\u00FC' + "cksetzten");
		menuResetFilter.setFont(font);
		submenuFilter.add(menuResetFilter);
		
		filterButton1 = new JMenuItem("Wei" + '\u00DF');
		filterButton1.setFont(font);
		submenuFilter.add(filterButton1);
		
		filterButton2 = new JMenuItem("Blau");
		filterButton2.setFont(font);
		submenuFilter.add(filterButton2);
		
		filterButton3 = new JMenuItem("Gr" + '\u00FC' + "n");
		filterButton3.setFont(font);
		submenuFilter.add(filterButton3);
		
		filterButton4 = new JMenuItem("Rot");
		filterButton4.setFont(font);
		submenuFilter.add(filterButton4);
		
		filterButton5 = new JMenuItem("Orange");
		filterButton5.setFont(font);
		submenuFilter.add(filterButton5);
		
		filterButton6 = new JMenuItem("Pink");
		filterButton6.setFont(font);
		submenuFilter.add(filterButton6);
		
		mainMenu.add(submenuFilter);

		searchButton = new JMenuItem("Suchen");
		searchButton.setFont(font);
		mainMenu.add(searchButton);

		showBinButton = new JMenuItem("Papierkorb anzeigen");
		showBinButton.setFont(font);
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
