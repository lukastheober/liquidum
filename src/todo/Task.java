package todo;

import java.awt.Color;
import java.util.Date;

import javax.swing.*;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */
public class Task extends JPanel {

	private Color color;
	private Date deadline;
	private Date deletionDate;
	private int interval;
	private ListOfTasks myList;
	private String name;
	private JLabel dateLabel;
	private TaskMenu menu;
	private JLabel nameLabel;
	private JTextPane textPane;

	public Task(){

	}

	/**
	 * Overwrites all data in this Task with data of the new Task, except myList.
	 * 
	 * @param newTask
	 */
	public void overwrite(Task newTask){

	}

	/**
	 * 
	 * @param date
	 */
	public boolean expiresWithin3DaysOf(Date date){
		return false;
	}


}