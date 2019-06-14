package todo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */
public class ListEditingWizard extends MyDialog {

	/**
	 * @param list: the List to be edited
	 * @param controller: the Controller this wizard answers to Takes all data from
	 *        the existing list and puts them into the input fields for the user to
	 *        see and edit.
	 */
	private JTextField name;
	private JButton cancel;
	private JButton editListOfTasks;
	private ListOfTasks oldList;

	public ListEditingWizard(ListOfTasks tasklist, Controller controller) {
		super(controller);
		oldList = tasklist;
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
		name = new JTextField(oldList.getListName());
		name.setName("name");
		name.setText(oldList.getListName());
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

		editListOfTasks = new JButton("  Liste bearbeiten");
		editListOfTasks.setName("edit");
		editListOfTasks.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// missing Constructor with params
				System.out.println("Missing ListOfTasks Constructor, doing nothing");
				oldList.overwrite(new ListOfTasks(name.getText()));
			}
		});
		this.add(editListOfTasks);
	}
}