package todo;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.*;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */
public class ListOfTasks extends JPanel {

	private String name;
	private JButton addTaskButton;
	private JButton deleteButton;
	private JButton editButton;
	private JLabel nameLabel;
	private LinkedList<Task> tasks = new LinkedList();
	private ListSortMenu sortingMenu;
	
	static final int MAX_TASKS = 10;
	
	public ListOfTasks(String listName) {
		setLayout(new GridLayout(MAX_TASKS + 1, 1));
		
		nameLabel = new JLabel(listName);
		
		JPanel topBar = menuBars(listName);
		
		add(topBar);
		//TODO viewable tasks
	}
	
	private JPanel menuBars(String listName) {
		JPanel menues = new JPanel();
		menues.setLayout(new GridLayout(2, 3));
		
		sortingMenu = new ListSortMenu(listName);
		
		//buttons
		addTaskButton = new JButton("+");
		//TODO create-new-task (wizzard?)
		editButton = new JButton("Bearbeiten");
		deleteButton = new JButton("Entfernen");
		
		menues.add(sortingMenu); 
		menues.add(editButton);
		menues.add(addTaskButton);
		menues.add(deleteButton);
	}
	
	public String getListName() {
		return this.name;
	}
	
	public LinkedList<Task> getTaskList(){
		return this.tasks;
	}
	
	/**
	 * Overwrites all data in this List with data of the new Task, except tasks.
	 * 
	 * @param newList
	 */
	public void overwrite(ListOfTasks newList) {
		this.name = newList.name;
		this.nameLabel = newList.nameLabel;
	}
}