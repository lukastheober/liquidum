package todo;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
public class TaskEditingWizard extends MyDialog {

	/**
	 * @param task: the Task to be edited
	 * @param controller: the Controller this Wizard answers to Takes all data from
	 *        the existing task and puts them into the input fields for the user to
	 *        see and edit.
	 */

	private JTextField name;
	private JDatePickerImpl date;
	private JSpinner timeSpinner;
	private JComboBox<String> colour;
	private JComboBox<String> interval;
	private JButton cancel;
	private JButton createTask;
	private Task oldTask;
	private JTextField text;

	private String[] colours = {"Wei" + '\u00DF', "Blau", "Gr" + '\u00FC' + "n", "Rot", "Orange", "Pink"};
	private String[] intervals = { "1", "2", "3", "4", "5", "6" };

	private void initialize() {

		setLayout(new BorderLayout());
		setTitle("Aufgabe bearbeiten");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		initializeFields();
		setVisible(true);
		pack();

	}

	private void initializeFields() {
		
		JPanel inputPanel = new JPanel(new GridBagLayout());
		
		namePanel(inputPanel);
		datePanel(inputPanel);
		timePanel(inputPanel);
		colorPanel(inputPanel);
		intervallPanel(inputPanel);
		textPanel(inputPanel);
		
		add(inputPanel, BorderLayout.CENTER);
		
		add(buttonPanel(), BorderLayout.SOUTH);

	}
	
	private void namePanel(JPanel inputPanel) {
		inputPanel.add(new JLabel("Name"), new GridBagConstraints(0, 0, 1, 1, 1, 1, 
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		name = new JTextField(oldTask.getName());
		name.setName("name");
		inputPanel.add(name, new GridBagConstraints(1, 0, 1, 1, 1, 1, 
				GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL, 
				new Insets(5, 5, 5, 5), 0, 0));
	}

	private void datePanel(JPanel inputPanel) {
		
		inputPanel.add(new JLabel("Ablaufdatum"), new GridBagConstraints(0, 1, 1, 1, 1, 1, 
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		Properties p = new Properties();
		p.put("text.today", "today");
		p.put("text.month", "month");
		p.put("text.year", "year");
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		date = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String oldDate = oldTask.getDeadline().format(formatter);
		date.getJFormattedTextField().setText(oldDate);
		inputPanel.add(date, new GridBagConstraints(1, 1, 1, 1, 1, 1, 
				GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL, 
				new Insets(5, 5, 5, 5), 0, 0));
	}

	private void timePanel(JPanel inputPanel) {
		
		inputPanel.add(new JLabel("Ablaufzeit"), new GridBagConstraints(0, 2, 1, 1, 1, 1, 
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, 
				new Insets(5, 5, 5, 5), 0, 0));
		timeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
		timeSpinner.setEditor(timeEditor);

		LocalDateTime ldt = oldTask.getDeadline();
		ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
		Date output = Date.from(zdt.toInstant());

		timeSpinner.setValue(output);
		inputPanel.add(timeSpinner, new GridBagConstraints(1, 2, 1, 1, 1, 1, 
				GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL, 
				new Insets(5, 5, 5, 5), 0, 0));
	}

	private void colorPanel(JPanel inputPanel) {
		
		inputPanel.add(new JLabel("Farbe"), new GridBagConstraints(0, 3, 1, 1, 1, 1, 
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, 
				new Insets(5, 5, 5, 5), 0, 0));
		colour = new JComboBox<String>(colours);
		colour.setSelectedIndex(colorToIndex(oldTask.getColor()));
		colour.setName("colour");
		inputPanel.add(colour, new GridBagConstraints(1, 3, 1, 1, 1, 1, 
				GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL, 
				new Insets(5, 5, 5, 5), 0, 0));
	}

	private void intervallPanel(JPanel inputPanel) {
		
		inputPanel.add(new JLabel("Intervall in h"), new GridBagConstraints(0, 4, 1, 1, 1, 1, 
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, 
				new Insets(5, 5, 5, 5), 0, 0));
		interval = new JComboBox<String>(intervals);
		interval.setName("interval");
		inputPanel.add(interval, new GridBagConstraints(1, 4, 1, 1, 1, 1, 
				GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL, 
				new Insets(5, 5, 5, 5), 0, 0));
	}

	private void textPanel(JPanel inputPanel) {
		
		inputPanel.add(new JLabel("Text"), new GridBagConstraints(0, 5, 1, 1, 1, 1, 
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, 
				new Insets(5, 5, 5, 5), 0, 0));
		text = new JTextField(oldTask.getText());
		text.setName("text");
		inputPanel.add(text, new GridBagConstraints(1, 5, 1, 1, 1, 1, 
				GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL, 
				new Insets(5, 5, 5, 5), 0, 0));
	}

	private Component buttonPanel() {
		JPanel buttonPanel = new JPanel();
		
		cancel = new JButton("Abbrechen");
		cancel.setName("cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPanel.add(cancel, new GridBagConstraints(0, 6, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
				new Insets(5, 5, 5, 5), 0, 0));

		createTask = new JButton("Aufgabe erstellen");
		createTask.setName("create");
		createTask.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("") || name.getText().equals("")) {
					if (text.getText().equals("")) {
						text.setBackground(Color.red);
					}
					else {
						text.setBackground(Color.white);
					}
					if (name.getText().equals("")) {
						name.setBackground(Color.red);
					}
					else {
						name.setBackground(Color.white);
					}
				}
				else {
					String timeAsString = (String) timeSpinner.getModel().getValue().toString().subSequence(11, 19);
					String dateAsString = date.getJFormattedTextField().getText();
					String dateAndTime = toDateAndTime(dateAsString, timeAsString);
		
					oldTask.setColor(colorParser((String) colour.getSelectedItem()));
					oldTask.setName(name.getText());
					oldTask.setDeadline(LocalDateTime.parse(dateAndTime));
					oldTask.setInterval(Integer.parseInt((String) interval.getSelectedItem()));
					oldTask.setText(text.getText());
					
					dispose();
				}
			}

			private String toDateAndTime(String date, String time) {
				String[] dateAsArray = date.split("[.]");
				return dateAsArray[2] + "-" + dateAsArray[1] + "-" + dateAsArray[0] + "T" + time;
			}
		});
		buttonPanel.add(createTask, new GridBagConstraints(1, 6, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		return buttonPanel;
	}

	private int colorToIndex(Color inColor) {

		if (inColor.equals(Color.white))
			return 0;
		else if (inColor.equals(Color.blue))
			return 1;
		else if (inColor.equals(Color.green))
			return 2;
		else if (inColor.equals(Color.red))
			return 3;
		else if (inColor.equals(Color.orange))
			return 4;
		else if (inColor.equals(Color.pink))
			return 5;
		else
			return 0;
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