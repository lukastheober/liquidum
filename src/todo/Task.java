package todo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;

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
	private LocalDate deadline;
	private LocalDate deletionDate;
	private int interval;
	private TaskMenu menu;
	private ListOfTasks myList;
	private String name;
	private JLabel nameLabel;
	private JLabel dateLabel;
	private JLabel textLabel;

	public Task(String name, LocalDate deadline, int interval, Color color, String text) {
		this.name = name;
		this.deadline = deadline;
		this.interval = interval;
		this.color = color;
		
		setSize(400, 250);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));	
		
		this.nameLabel = new JLabel(name);
		this.dateLabel = new JLabel(deadline.toString());
		this.menu = new TaskMenu(this);
		JPanel topBar = new JPanel(new GridLayout(1,3));
		topBar.add(nameLabel);
		topBar.add(dateLabel);
		topBar.add(menu);
		topBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		topBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(topBar, BorderLayout.NORTH);
		
		this.textLabel = new JLabel(text);
		textLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(textLabel, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public boolean expiresWithing3DaysOf(LocalDate date) {
		return true;
	}

	public void overwrite(Task newTask) {
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Color getColor() {
		return color;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public LocalDate getDeletionDate() {
		return deletionDate;
	}

	public int getInterval() {
		return interval;
	}

	public TaskMenu getMenu() {
		return menu;
	}

	public ListOfTasks getMyList() {
		return myList;
	}

	public String getName() {
		return name;
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public JLabel getDateLabel() {
		return dateLabel;
	}

	public JLabel getTextLabel() {
		return textLabel;
	}

	/*public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 750);
        frame.setLayout(new GridLayout(3,1));
        
		Task t1 = new Task("Müll rausbringen", LocalDate.now(), 2, Color.red, "Grüne und schwarze Tonne sind dran.");
		Task t2 = new Task("Wäsche waschen", LocalDate.now(), 2, Color.green, "Buntwäsche.");
		Task t3 = new Task("Blumen gießen", LocalDate.now(), 2, Color.red, "Auch an Tomaten denken.");
		frame.add(t1);
		frame.add(t2);
		frame.add(t3);
		frame.setVisible(true);
	}*/
}