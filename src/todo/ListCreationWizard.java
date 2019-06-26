package todo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Transparency;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

		setLayout(new BorderLayout());
		setTitle("Liste erstellen");
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
		
		name = new JTextField();
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

		createListOfTasks = new JButton("Liste erstellen");
		createListOfTasks.setName("create");
		createListOfTasks.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (name.getText().trim().equals("")) {
					name.setBackground(Color.red);
				}
				else {
					controller.addList(new ListOfTasks(name.getText().trim()));
					dispose();
				}
			}
		});
		bottom.add(createListOfTasks, new GridBagConstraints(1, 0, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		add(bottom, BorderLayout.SOUTH);
	}
}
