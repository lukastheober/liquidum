package todo;

import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */

public class MainMenuBar extends JMenuBar {

	private JMenuItem filterButton;
	private JMenuItem listCreateButton;
	private JMenuItem restoreTaskButton;
	private JMenuItem searchButton;
	private JMenuItem showBinButton;

	public MainMenuBar() {
		JMenu mainMenu = new JMenu("Menü");
		add(mainMenu);
		
		ActionListener actListener = new MainMenuBarActionListener();

		listCreateButton = new JMenuItem("Neue Liste erstellen");
		listCreateButton.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		listCreateButton.addActionListener(actListener);
		mainMenu.add(listCreateButton);

		JMenu submenuFilter = new JMenu("Filter");

		JMenuItem menuResetFilter = new JMenuItem("Filter zurücksetzten");
		menuResetFilter.addActionListener(actListener);
		submenuFilter.add(menuResetFilter);
		JMenuItem filterButton = new JMenuItem("Rot");
		filterButton.addActionListener(actListener);
		submenuFilter.add(filterButton);
		filterButton = new JMenuItem("Grün");
		menuResetFilter.addActionListener(actListener);
		submenuFilter.add(filterButton);
		filterButton = new JMenuItem("Blau");
		menuResetFilter.addActionListener(actListener);
		submenuFilter.add(filterButton);

		mainMenu.add(submenuFilter);

		searchButton = new JMenuItem("Suchen");
		searchButton.addActionListener(actListener);
		mainMenu.add(searchButton);

		showBinButton = new JMenuItem("Papierkorb zeigen");
		showBinButton.addActionListener(actListener);
		mainMenu.add(showBinButton);
		
		restoreTaskButton = new JMenuItem("Aufgabe wiederherstellen");
		restoreTaskButton.addActionListener(actListener);
		mainMenu.add(restoreTaskButton);

	}
}
