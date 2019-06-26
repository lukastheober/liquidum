package todo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:13
 */
public class TaskCreationWizard extends MyDialog {

	private JTextField name;
	private JDatePickerImpl date;
	private JSpinner timeSpinner;
	private JComboBox<String> colour;
	private JComboBox<String> interval;
	private JTextField text;
	private JButton cancel;
	private JButton createTask;
	private ListOfTasks tList;

	private String[] colours = {"Wei" + '\u00DF', "Blau", "Gr" + '\u00FC' + "n", "Rot", "Orange", "Pink"};
	private String[] intervals = { "1", "2", "3", "4", "5", "6" };

	public TaskCreationWizard(ListOfTasks tList, Controller controller) {
		super(controller);
		this.tList = tList;
		initialize();
	}

	private void initialize() {

		setLayout(new GridLayout(0, 2, 11, 10));
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
		name = new JTextField();
		name.setName("name");
		name.setPreferredSize(new Dimension(220, 25));
		this.add(name);

		this.add(new JLabel("    Ablaufdatum"));
		Properties p = new Properties();
		p.put("text.today", "today");
		p.put("text.month", "month");
		p.put("text.year", "year");
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		date = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		date.getJFormattedTextField().setText(LocalDate.now().format(formatter));
		date.setPreferredSize(new Dimension(220, 25));
		this.add(date);
		
		this.add(new JLabel("    Ablaufzeit"));
		timeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date());
		JPanel time = new JPanel();
		timeSpinner.setPreferredSize(new Dimension(220, 25));
		time.add(timeSpinner);
		this.add(time);
		

		this.add(new JLabel("    Farbe"));
		colour = new JComboBox<String>(colours);
		colour.setName("colour");
		colour.setPreferredSize(new Dimension(220, 25));
		this.add(colour);

		this.add(new JLabel("    Intervall in h"));
		interval = new JComboBox<String>(intervals);
		interval.setName("interval");
		interval.setPreferredSize(new Dimension(220, 25));
		this.add(interval);

		this.add(new JLabel("    Text"));
		text = new JTextField();
		text.setName("text");
		text.setPreferredSize(new Dimension(220, 25));
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

		createTask = new JButton("  Aufgabe erstellen");
		createTask.setName("create");
		createTask.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String timeAsString = (String) timeSpinner.getModel().getValue().toString().subSequence(11, 19);
				String dateAsString = date.getJFormattedTextField().getText();
				String dateAndTime = toDateAndTime(dateAsString, timeAsString);
				
				
				controller.addTask(new Task(tList ,name.getText(), LocalDateTime.parse(dateAndTime) , Integer.parseInt((String) interval.getSelectedItem()), colorParser((String) colour.getSelectedItem()), text.getText()));
				dispose();
			}
			private String toDateAndTime(String date, String time) {
				String[] dateAsArray = date.split("[.]");
				return dateAsArray[2] + "-" + dateAsArray[1] + "-" + dateAsArray[0] + "T" + time;
			}
		});
		this.add(createTask);
	}

	private Color colorParser(String clrStr) {
		switch (clrStr) {
		case "Blau":
			return Color.BLUE;
		case "Gr" + '\u00FC' + "n":
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
