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
		// TODO use this object
		// MouseListener dnd = new DragAndDropMouseListener();
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
		sortingMenu.setPreferredSize(new Dimension(50, 30));
		nameLabel.setPreferredSize(new Dimension(300, 30));
		
		// buttons
		addTaskButton = new JButton("+");
		addTaskButton.setPreferredSize(new Dimension(315, 30));
		editButton = new JButton("Bearbeiten");
		editButton.setPreferredSize(new Dimension(30, 30));
		deleteButton = new JButton("Entfernen");
		deleteButton.setPreferredSize(new Dimension(30, 30));
		
		topMenu.add(this.nameLabel);
		topMenu.add(sortingMenu);
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

	/**
	 * Overwrites all data in this List with data of the new Task, except tasks.
	 * 
	 * @param newList
	 */
	public void overwrite(ListOfTasks newList) {
		this.name = newList.name;
		this.nameLabel = newList.nameLabel;
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

	public void addList(LinkedList<Task> loadList) {
		/* only to use by the load class
		 * */
		this.tasks = loadList;
		
	}
}