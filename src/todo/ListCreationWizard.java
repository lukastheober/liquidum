package todo;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */
public class ListCreationWizard extends MyDialog {

	private JTextField name;
	private JButton cancel;
	private JButton createListOfTasks;

	public ListCreationWizard(Controller controller) {
		super(controller);
		initialize();
	}

	private void initialize() {

		// feel free to make it pretty
		setLayout(new GridLayout(0, 2, 10, 10));
		setSize(300, 400);
		setTitle("Liste erstellen");
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

		cancel = new JButton("  Abbrechen");
		cancel.setName("cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});
		this.add(cancel);

		createListOfTasks = new JButton("  Liste erstellen");
		createListOfTasks.setName("create");
		createListOfTasks.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.addList(new ListOfTasks(name.getText()));
				dispose();
			}
		});
		this.add(createListOfTasks);
	}
}
