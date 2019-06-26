package todo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		setLayout(new BorderLayout());
		setTitle("Liste bearbeiten");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		initializeFields();
		setVisible(true);
		pack();

	}

	private void initializeFields() {

		JPanel top = new JPanel();
		top.setLayout(new GridBagLayout());
		
		top.add(new JLabel("Name"), new GridBagConstraints(0, 0, 1, 1, 1, 1, 
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		name = new JTextField(oldList.getListName());
		name.setName("name");
		top.add(name, new GridBagConstraints(1, 0, 1, 1, 20, 1, 
				GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		add(top, BorderLayout.CENTER);
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridBagLayout());

		cancel = new JButton("Abbrechen");
		cancel.setName("cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});
		bottom.add(cancel, new GridBagConstraints(0, 0, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
				new Insets(5, 5, 5, 5), 0, 0));

		editListOfTasks = new JButton("Bearbeiten");
		editListOfTasks.setName("edit");
		editListOfTasks.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (name.getText().trim().equals("")) {
					name.setBackground(Color.red);
				}
				else {
					oldList.setName(name.getText());
					oldList.setNameLabel(name.getText().trim());
					dispose();
				}
			}
		});
		bottom.add(editListOfTasks, new GridBagConstraints(1, 0, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		add(bottom, BorderLayout.SOUTH);
	}
}