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
	private JLabel nameLabel;
	private ListSortMenu sortingMenu = new ListSortMenu();
	
	/*
	 * 	
	 */    
    private int nrOfTasks = 0;
    private static final int maxNrOfTasks = 10;    
    
    public ListOfTasks(String aListName) {
	    setLayout(new GridLayout(maxNrOfTasks + 1,1));
	    setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    
	    menuBar = new ListMenuBar(aListName);
	    this.listNr = MyGUI.getListContainer().getNumberOfLists() + 1;
	    menuBar.myList = listNr;
	    
	    add(menuBar);
    }
    
    public int getNrOfTasks() {
            return nrOfTasks;
    }
    
    public void setNrOfTasks(int i) {
            nrOfTasks = i;
    }
    

	/*
	 * 
	 */
	public void oldListOfTasks(String n) {
		name = n;
		//TODO set nameLabel
		this.displayFields();
	}
	
	public ListOfTasks(){
		displayFields();
	}
	
	private void displayFields() {
		addTaskButton = new JButton("Hinzuf�gen");
		editButton = new JButton("Bearbeiten");
		deleteButton = new JButton("Entfernen");
		
        JFrame listFrame = new JFrame(name);
		JPanel topPanel = new JPanel();
		JPanel workPanel = new JPanel();
		JPanel gridPanel = new JPanel();
		//TODO include namelabel in Panel
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