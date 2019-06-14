package todo;

import java.awt.Color;
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

	private String name;
	private JButton addTaskButton;
	private JButton deleteButton;
	private JButton editButton;
	private JLabel nameLabel;
	private LinkedList<Task> tasks = new LinkedList();
	private ListSortMenu sortingMenu;
	
	static final int MAX_TASKS = 10;
	
	public ListOfTasks(String listName) {
		//TODO use this object
		MouseListener dnd = new DragAndDropMouseListener();	

		setLayout(new GridLayout(MAX_TASKS + 1, 1));
		nameLabel = new JLabel(listName);
		
		JPanel topBar = menuBars();
		//TODO viewable tasks
		
		add(topBar);
		setVisible(true);
	}
	
	private JPanel menuBars() {
		JPanel menus = new JPanel();
		menus.setLayout(new GridLayout(2, 3));
		
		sortingMenu = new ListSortMenu(this);
		
		//buttons
		addTaskButton = new JButton("+");
		//TODO create-new-task (wizzard?)
		editButton = new JButton("Bearbeiten");
		deleteButton = new JButton("Entfernen");
		
		menus.add(sortingMenu); 
		menus.add(editButton);
		menus.add(addTaskButton);
		menus.add(deleteButton);
		
		return menus;
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
	
//	//testing main
//	public static void main(String[] args) {
//		new ListOfTasks("povowjcn");
//	}
}