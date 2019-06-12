package todo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:13
 */
public class TaskEditingWizard extends MyDialog {

	/**
	 * @param task: the Task to be edited
	 * @param controller: the Controller this Wizard answers to Takes all data from
	 *        the existing task and puts them into the input fields for the user to
	 *        see and edit.
	 */

	private JTextField name;
	private JDatePickerImpl date;
	private JComboBox<String> colour;
	private JComboBox<String> interval;
	private JButton cancel;
	private JButton createTask;
	private Task oldTask;

	private String[] colours = { "Schwarz", "Blau", "Rot", "Grün", "Grau", "Orange", "Pink" };
	private String[] intervals = { "Auswählen", "1 h", "2 h", "3 h", "4 h", "5 h" };

	//private final static int BLACK = Color.BLACK.getRGB();
	
	private void initialize() {

		setLayout(new GridLayout(0, 2, 10, 10));
		setSize(300, 400);
		setTitle("  Aufgabe erstellen");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		initializeFields();
		setVisible(true);
		pack();

	}

	private void initializeFields() {

		this.add(new JLabel("    Name"));
		name = new JTextField(oldTask.getName());
		name.setName("name");
		this.add(name);

		this.add(new JLabel("    Ablaufdatum"));
		Properties p = new Properties();
		p.put("text.today", "today");
		p.put("text.month", "month");
		p.put("text.year", "year");
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		date = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		date.getJFormattedTextField().setText("06.06.2019");
		this.add(date);

		this.add(new JLabel("    Farbe"));
		colour = new JComboBox<String>(colours);
		colour.setSelectedIndex(colorToIndex(oldTask.getColor()));
		colour.setName("colour");
		this.add(colour);

		this.add(new JLabel("    Intervall"));
		interval = new JComboBox<String>(intervals);
		interval.setName("interval");
		this.add(interval);

		cancel = new JButton("  Abbrechen");
		cancel.setName("cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		this.add(cancel);

		createTask = new JButton("  Aufgabe erstellen");
		createTask.setName("create");
		createTask.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Task-Constructor not complete");
				oldTask.overwrite(new Task(name, date, colour, interval, (ListOfTasks) e.getSource()));

			}
		});
		this.add(createTask);

	}

	private int colorToIndex(Color inColor) {

		if(inColor == Color.BLACK) {
			return 0;
		} else if(inColor == Color.BLUE) {
			return 1;
		} else if(inColor == Color.RED) {
			return 2;
		} else if(inColor == Color.GREEN) {
			return 3;
		} else if(inColor == Color.GRAY) {
			return 4;
		} else if(inColor == Color.ORANGE) {
			return 5;
		} else {
			return 6;
		}
	}
	
	public TaskEditingWizard(Task task, Controller controller) {
		super(controller);
		oldTask = task;
		initialize();
		// TODO Auto-generated constructor stub
	}

}