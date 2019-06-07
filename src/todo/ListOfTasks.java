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
	private LinkedList<Task> tasks;
	private JButton addTaskButton;
	private JButton deleteButton;
	private JButton editButton;
	//TODO is this important?
	private JLabel nameLabel;
	private ListSortMenu sortingMenu = new ListSortMenu();

	public ListOfTasks(){
		//test
		addTaskButton = new JButton("+");
		editButton = new JButton("Bearbeiten");
		deleteButton = new JButton("X");
		//testend
		
        JFrame listFrame = new JFrame(name);
		JPanel topPanel = new JPanel();
		JPanel workPanel = new JPanel();
		JPanel gridPanel = new JPanel();
//		topPanel.add(nameLabel);
		topPanel.add(sortingMenu);
		listFrame.add(topPanel);
				
		gridPanel.add(addTaskButton);
		gridPanel.add(editButton);
		gridPanel.add(deleteButton);
		
		workPanel.add(gridPanel);
		listFrame.add(workPanel);
			
		listFrame.pack();
		
		listFrame.setSize(420, 666);
		listFrame.setLocationRelativeTo(null);
		listFrame.setVisible(true);
	}
	
	public String getListName() {
		return this.name;
	}

	/**
	 * Overwrites all data in this List with data of the new Task, except tasks.
	 * 
	 * @param newList
	 */
	public void overwrite(ListOfTasks newList){
		this.name = newList.name;
		this.nameLabel = newList.nameLabel;
	}
}