package todo;

import javax.swing.JDialog;
import java.awt.GridLayout;
import java.util.Properties;
 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */
public class ListCreationWizard extends MyDialog {

	private JTextField name;
	private JDatePickerImpl date;
	private JComboBox<String> colour;
	private JComboBox<String> interval;
	private JButton cancel;
	private JButton createTask;

    private String[] colours = { "Auswählen", "Blau", "Rot", "Grün" };
    private String[] intervals = { "Auswählen", "1 h", "2 h", "3 h", "4 h", "5 h" };
	
	// CONSTRUCTOR

	public ListCreationWizard(Controller controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}
	
	private void initialize() {

		setLayout(new GridLayout(0, 2, 10, 10));
		setSize(300, 400);
		setTitle("Liste erstellen");
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		this.add(cancel);

		createTask = new JButton("  Aufgabe erstellen");
		createTask.setName("create");
		this.add(createTask);

	}



}