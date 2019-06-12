package todo;


import java.awt.Color;
import java.awt.LayoutManager;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;


/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */

public class Task extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7860879667916763952L;

	
	private Color color;
	private Date deadline;
	private Date deletionDate;
	private int interval;
	private TaskMenu menu;
	private ListOfTasks myList;
	private String name;
	private JLabel nameLabel;
	private JLabel dateLabel;
	private JTextPane textPane;

	public Task(Color color, Date deadline, Date deletionDate, int interval, TaskMenu menu, ListOfTasks myList,
			String name, JLabel nameLabel, JLabel dateLabel, JTextPane textPane) {
		super();
		this.color = color;
		this.deadline = deadline;
		this.deletionDate = deletionDate;
		this.interval = interval;
		this.menu = menu;
		this.myList = myList;
		this.name = name;
		this.nameLabel = nameLabel;
		this.dateLabel = dateLabel;
		this.textPane = textPane;
	}
	public boolean expiresWithing3DaysOf(Date date) {
		return true;
	}

	public void overwrite(Task newTask) {
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getDeletionDate() {
		return deletionDate;
	}

	public void setDeletionDate(Date deletionDate) {
		this.deletionDate = deletionDate;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public TaskMenu getMenu() {
		return menu;
	}

	public void setMenu(TaskMenu menu) {
		this.menu = menu;
	}

	public ListOfTasks getMyList() {
		return myList;
	}

	public void setMyList(ListOfTasks myList) {
		this.myList = myList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	public JLabel getDateLabel() {
		return dateLabel;
	}

	public void setDateLabel(JLabel dateLabel) {
		this.dateLabel = dateLabel;
	}

	public JTextPane getTextPane() {
		return textPane;
	}

	public void setTextPane(JTextPane textPane) {
		this.textPane = textPane;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
