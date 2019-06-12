package todo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JButton cancel;
	private JButton createTask;

	private String[] colours = { "Auswählen", "Blau", "Rot", "Grün" };
	private String[] intervals = { "Auswählen", "1 h", "2 h", "3 h", "4 h", "5 h" };

	public TaskCreationWizard(ListOfTasks tList, Controller controller) {
		super(controller);
		initialize();
		// TODO Auto-generated constructor stub
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
		date.getJFormattedTextField().setText("06.06.2019");
		this.add(date);

		this.add(new JLabel("    Farbe"));
		colour = new JComboBox<String>(colours);
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
				System.out.println("Task-Constructor missing");
				controller.addTask(new Task(name.getText(), date, colour.getSelectedItem(), interval.getSelectedItem()));

			}
		});
		this.add(createTask);

	}

}
