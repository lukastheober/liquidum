package todo;

import java.util.LinkedList;

import javax.swing.*;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */
public class ListOfTasks extends JPanel {

	private String name;
	private LinkedList<Task> tasks;
	private JButton addTaskButton;
	private JButton deleteButton;
	private JButton editButton;
	private JLabel nameLabel;
	private ListSortMenu sortingMenu;

	public ListOfTasks() {

	}

	/**
	 * Overwrites all data in this List with data of the new Task, except tasks.
	 * 
	 * @param newList
	 */
	public void overwrite(ListOfTasks newList) {

	}

	public LinkedList<Task> getTaskList() {
		return tasks;
	}
}