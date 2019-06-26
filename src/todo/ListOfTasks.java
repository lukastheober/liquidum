package todo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.*;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */
public class ListOfTasks extends JPanel {
 
	//test
	private String name;
	private JButton addTaskButton;
	private JButton deleteButton;
	private JButton editButton;
	private JLabel nameLabel;
	private LinkedList<Task> tasks = new LinkedList<Task>();
	private ListSortMenu sortingMenu;
	
	static final int MAX_TASKS = 10;
	public ListOfTasks(String listName) {
		name = listName;
		setBorder(BorderFactory.createRaisedSoftBevelBorder());
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(410, 750));
		nameLabel = new JLabel(listName);

		JPanel topBar = menuBars();
		
		add(topBar);

		setVisible(true);
	}

	private JPanel menuBars() {
		JPanel menus = new JPanel(new GridLayout(2,1));
		menus.setPreferredSize(new Dimension(400, 80));
		menus.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		
		JPanel topMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bottomMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));

		sortingMenu = new ListSortMenu(this);
		nameLabel.setPreferredSize(new Dimension(305, 30));
		
		// buttons
		addTaskButton = new JButton("+");
		addTaskButton.setPreferredSize(new Dimension(165, 30));
		editButton = new JButton("Liste bearbeiten");
		editButton.setPreferredSize(new Dimension(105, 30));
		deleteButton = new JButton("Liste l" + '\u00F6' + "schen");
		deleteButton.setPreferredSize(new Dimension(105, 30));
		
		topMenu.add(this.nameLabel);
		topMenu.add(this.sortingMenu);
		menus.add(topMenu);
		
		bottomMenu.add(editButton);
		bottomMenu.add(addTaskButton);
		bottomMenu.add(deleteButton);
		menus.add(bottomMenu);
		
		return menus;
	}
	
	public void loadTasks() {
		for(Task currentTask : tasks) {
			add(currentTask);
		}
	}
	
	public String getListName() {
		return this.name;
	}
	
	public LinkedList<Task> getTaskList(){
		return this.tasks;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public JButton getAddTaskButton() {
		return addTaskButton;
	}

	public JButton getEditButton() {
		return editButton;
	}
	
	public void setNameLabel(String newName) {
		this.nameLabel.setText(newName);
	}

	public void addList(LinkedList<Task> loadList) {
		/* only to use by the load class
		 * */
		this.tasks = loadList;
		
	}
	
	public ListSortMenu getSortingMenu() {
		return sortingMenu;
	}
}