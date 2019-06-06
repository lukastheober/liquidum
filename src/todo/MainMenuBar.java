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
		JMenu mainMenu = new JMenu("Menu");
		add(mainMenu);
		
		ActionListener actListener = new MainMenuBarActionListener();

		listCreateButton = new JMenuItem("Create new List");
		//listCreateButton.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		listCreateButton.addActionListener(actListener);
		mainMenu.add(listCreateButton);

		JMenu submenuFilter = new JMenu("Filter");

		JMenuItem menuResetFilter = new JMenuItem("reset Filter");
		menuResetFilter.addActionListener(actListener);
		submenuFilter.add(menuResetFilter);
		filterButton = new JMenuItem("Red");
		filterButton.addActionListener(actListener);
		submenuFilter.add(filterButton);
		filterButton = new JMenuItem("Green");
		menuResetFilter.addActionListener(actListener);
		submenuFilter.add(filterButton);
		filterButton = new JMenuItem("Blue");
		menuResetFilter.addActionListener(actListener);
		submenuFilter.add(filterButton);

		mainMenu.add(submenuFilter);

		searchButton = new JMenuItem("Search");
		searchButton.addActionListener(actListener);
		mainMenu.add(searchButton);

		showBinButton = new JMenuItem("Show bin");
		showBinButton.addActionListener(actListener);
		mainMenu.add(showBinButton);
		
		restoreTaskButton = new JMenuItem("Restore Task");
		restoreTaskButton.addActionListener(actListener);
		mainMenu.add(restoreTaskButton);

	}
}
