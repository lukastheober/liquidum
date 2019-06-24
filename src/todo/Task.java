package todo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
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
	private LocalDateTime deadline;
	private LocalDateTime deletionDate;
	private int interval;
	private String text;
	private TaskMenu menu;
	private ListOfTasks myList;
	private String name;
	private JLabel nameLabel;
	private JLabel dateLabel;
	private JPanel textPanel;

	public Task(ListOfTasks myList, String name, LocalDateTime deadline, int interval, Color color, String text) {
		this.myList = myList;
		this.name = name;
		this.deadline = deadline;
		this.interval = interval;
		this.color = color;
		
		setPreferredSize(new Dimension(400, 150));
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		add(createTopBar(), BorderLayout.NORTH);
		
		createTextPanel(text);
		add(this.textPanel, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private JPanel createTopBar() {
		this.nameLabel = new JLabel(this.name);
		this.nameLabel.setHorizontalTextPosition(JLabel.LEFT);
		this.nameLabel.setPreferredSize(new Dimension(350,20));
		
		this.menu = new TaskMenu(this);
		
		JPanel upperTopBar = new JPanel(new FlowLayout());
		upperTopBar.add(this.nameLabel);
		upperTopBar.add(this.menu);
		
		this.dateLabel = new JLabel(deadlineToString());
		this.dateLabel.setPreferredSize(new Dimension(380, 20));
		this.dateLabel.setHorizontalTextPosition(JLabel.LEFT);
		JPanel lowerTopBar = new JPanel(new FlowLayout());
		lowerTopBar.add(this.dateLabel);
				
		JPanel topBar = new JPanel(new GridLayout(2, 0));
		topBar.setPreferredSize(new Dimension(400, 60));
		topBar.add(upperTopBar);
		topBar.add(lowerTopBar);
		topBar.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		
		return topBar;
	}
	
	private void createTextPanel(String text) {
		this.textPanel = new JPanel(new BorderLayout());
		this.textPanel.setBackground(Color.WHITE);
		this.textPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		
		JLabel textLabel = new JLabel(text);
		textLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		textLabel.setVerticalAlignment(SwingConstants.TOP);
		
		JPanel colorPanel = new JPanel();
		colorPanel.setBackground(this.color.darker());
		colorPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		
		this.textPanel.add(textLabel, BorderLayout.CENTER);
		this.textPanel.add(colorPanel, BorderLayout.EAST);
	}
	
	private String deadlineToString() {
		String dlTime = this.deadline.toString().substring(11, 16);
		
		String dlDate = this.deadline.toString().substring(0, 10);
		String[] dlDateAsArray = dlDate.split("-");
		dlDate = dlDateAsArray[2] + "." + dlDateAsArray[1] + "." + dlDateAsArray[0];
		
		return "Fällig am " + dlDate + " um " + dlTime;
	}
	
	public boolean expiresWithing3Days() {
		return this.deadline.minusDays(3).isBefore(LocalDateTime.now());
	}

	public void overwrite(Task newTask) {
		
	}

	public Color getColor() {
		return color;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public LocalDateTime getDeletionDate() {
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
	
	public String getText() {
		return text;
	}

	/*public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(3,1));
        
        ListOfTasks list = new ListOfTasks("test");
		Task t1 = new Task(list, "Müll rausbringen", LocalDate.now(), 2, Color.red, "Grüne und schwarze Tonne sind dran.");
		Task t2 = new Task(list, "Wäsche waschen", LocalDate.now(), 2, Color.green, "Buntwäsche.");
		Task t3 = new Task(list, "Blumen gießen", LocalDate.now(), 2, Color.blue, "Auch an Tomaten denken.");
		frame.add(t1);
		frame.add(t2);
		frame.add(t3);
		frame.pack();
		frame.setVisible(true);
	}*/
}