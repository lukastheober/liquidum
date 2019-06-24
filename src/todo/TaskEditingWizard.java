package todo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	private JTextField text;

	private String[] colours = { "Schwarz", "Blau", "Rot", "Grün", "Grau", "Orange", "Pink" };
	private String[] intervals = { "1", "2", "3", "4", "5", "6" };

	private void initialize() {

		setLayout(new GridLayout(0, 2, 10, 10));
		setSize(300, 400);
		setTitle("  Aufgabe bearbeiten");
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
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		//System.out.println(oldTask.getDeadline());
		date.getJFormattedTextField().setText(oldTask.getDeadline().format(formatter));
		this.add(date);

		this.add(new JLabel("    Farbe"));
		colour = new JComboBox<String>(colours);
		colour.setSelectedIndex(colorToIndex(oldTask.getColor()));
		colour.setName("colour");
		this.add(colour);

		this.add(new JLabel("    Intervall in h"));
		interval = new JComboBox<String>(intervals);
		interval.setName("interval");
		this.add(interval);
		
		this.add(new JLabel("    Text"));
		text = new JTextField(oldTask.getText());
		text.setName("text");
		this.add(text);

		cancel = new JButton("  Abbrechen");
		cancel.setName("cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		this.add(cancel);

		createTask = new JButton("  Aufgabe bearbeiten");
		createTask.setName("create");
		createTask.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Task-Constructor not complete");
			//	System.out.println(date.getJFormattedTextField().getText());
				oldTask.overwrite(new Task(oldTask.getMyList(), name.getText(), LocalDate.parse(date.getJFormattedTextField().getText(), formatter), Integer.parseInt((String) interval.getSelectedItem()), colorParser((String) colour.getSelectedItem()), text.getText()));
				dispose();
			}
		});
		this.add(createTask);
	}

	private int colorToIndex(Color inColor) {

		if (inColor == Color.BLACK) {
			return 0;
		} else if (inColor == Color.BLUE) {
			return 1;
		} else if (inColor == Color.RED) {
			return 2;
		} else if (inColor == Color.GREEN) {
			return 3;
		} else if (inColor == Color.GRAY) {
			return 4;
		} else if (inColor == Color.ORANGE) {
			return 5;
		} else {
			return 6;
		}
	}

	public TaskEditingWizard(Task task, Controller controller) {
		super(controller);
		oldTask = task;
		initialize();
	}

	private Color colorParser(String clrStr) {
		switch (clrStr) {
		case "Blau":
			return Color.BLUE;
		case "Grün":
			return Color.GREEN;
		case "Rot":
			return Color.RED;
		case "Orange":
			return Color.ORANGE;
		case "Pink":
			return Color.PINK;
		default:
			return Color.WHITE;
		}
	}

}