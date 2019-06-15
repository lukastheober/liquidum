package todo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	private JPanel textPanel;

	public Task(String name, LocalDate deadline, int interval, Color color, String text) {
		this.name = name;
		this.deadline = deadline;
		this.interval = interval;
		this.color = color;
		
		setPreferredSize(new Dimension(400, 150));
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createRaisedBevelBorder());
		
		add(createTopBar(), BorderLayout.NORTH);
		
		createTextPanel(text);
		add(textPanel, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private JPanel createTopBar() {
		JPanel colorPanel = new JPanel();
		colorPanel.setPreferredSize(new Dimension(20,20));
		colorPanel.setBackground(this.color);
		colorPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		
		this.nameLabel = new JLabel(name);
		nameLabel.setHorizontalTextPosition(JLabel.LEFT);
		nameLabel.setPreferredSize(new Dimension(230,20));
				
		this.dateLabel = new JLabel(deadline.toString());
		dateLabel.setPreferredSize(new Dimension(85,20));
				
		this.menu = new TaskMenu(this);
				
		JPanel topBar = new JPanel(new FlowLayout());
		topBar.add(colorPanel);
		topBar.add(nameLabel);
		topBar.add(dateLabel);
		topBar.add(menu);
		topBar.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		
		return topBar;
	}
	
	private void createTextPanel(String text) {
		this.textPanel = new JPanel(new GridLayout(1,1));
		JLabel textLabel = new JLabel(text);
		textLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		textLabel.setVerticalAlignment(SwingConstants.TOP);
		
		textPanel.setBackground(Color.WHITE);
		textPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		textPanel.add(textLabel);
	}
	
	public boolean expiresWithing3DaysOf(LocalDate date) {
		return true;
	}

	public void overwrite(Task newTask) {
		
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

	/*public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(3,1));
        
		Task t1 = new Task("Müll rausbringen", LocalDate.now(), 2, Color.red, "Grüne und schwarze Tonne sind dran.");
		Task t2 = new Task("Wäsche waschen", LocalDate.now(), 2, Color.green, "Buntwäsche.");
		Task t3 = new Task("Blumen gießen", LocalDate.now(), 2, Color.blue, "Auch an Tomaten denken.");
		frame.add(t1);
		frame.add(t2);
		frame.add(t3);
		frame.pack();
		frame.setVisible(true);
	}*/
}