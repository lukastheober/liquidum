package todo;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class TaskCreationWizard extends MyDialog {

	private JTextField name;
	private JDatePickerImpl date;
	private JComboBox<String> colour;
	private JComboBox<String> interval;
	private JTextField text;
	private JButton cancel;
	private JButton createTask;
	private ListOfTasks tList;

	private String[] colours = {"Weiß", "Blau", "Grün", "Rot", "Orange", "Pink"};
	private String[] intervals = { "1", "2", "3", "4", "5", "6" };

	public TaskCreationWizard(ListOfTasks tList, Controller controller) {
		super(controller);
		this.tList = tList;
		initialize();
	}

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
		name = new JTextField();
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		date.getJFormattedTextField().setText(LocalDate.now().format(formatter));
		this.add(date);

		this.add(new JLabel("    Farbe"));
		colour = new JComboBox<String>(colours);
		colour.setName("colour");
		this.add(colour);

		this.add(new JLabel("    Intervall"));
		interval = new JComboBox<String>(intervals);
		interval.setName("interval");
		this.add(interval);

		this.add(new JLabel("    Text"));
		text = new JTextField();
		text.setName("text");
		this.add(text);		
		
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
				controller.addTask(new Task(tList ,name.getText(), LocalDate.parse(date.getJFormattedTextField().getText(), formatter) , Integer.parseInt((String) interval.getSelectedItem()), colorParser((String) colour.getSelectedItem()), text.getText()));
				dispose();
			}
		});
		this.add(createTask);

	}

	private Color colorParser(String clrStr) {
		//Color.BLUE, Color.GREEN.toString(), Color.RED.toString(), Color.ORANGE.toString(), Color.PINK.toString()
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
