package todo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:13
 */
public class TaskRestorationWizard extends MyDialog {
	
	private JSplitPane splitPane;
	private JList<String> taskList = new JList<String>();

	public TaskRestorationWizard(Controller controller) {
		super(controller);
		if (controller.getBin().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Der Papierkorb ist leer.");
			return;
		}
		setTitle("Papierkorb");
		setUpList();
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(new JScrollPane(taskList));
		taskList.setSelectedIndex(0);
		splitPane.setRightComponent(new TaskPreview(getCorrespondingTask(taskList.getSelectedValue())));
		add(splitPane, BorderLayout.CENTER);
		
		setUpButtons();
		
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	private void setUpButtons() {
		JButton restore = new JButton("Wiederherstellen");
		JButton delete = new JButton("L" + '\u00F6' + "schen");
		JButton cancel = new JButton("Abbrechen");
		
		restore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Task task = getCorrespondingTask(taskList.getSelectedValue());
				controller.getBin().remove(task);
				ListOfTasks myList = task.getMyList();
				myList.getTaskList().add(task);
				myList.loadTasks();
				controller.updateUI();
				
				fillTaskListWithCurrentBin();
				taskList.setSelectedIndex(0);
			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.deleteTaskFromBin(getCorrespondingTask(taskList.getSelectedValue()));
				fillTaskListWithCurrentBin();
				taskList.setSelectedIndex(0);
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		buttonPanel.add(cancel, gbc);
		buttonPanel.add(restore, gbc);
		buttonPanel.add(delete, gbc);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private Task getCorrespondingTask(String taskName) {
		for (Task task : controller.getBin()) {
			if (task.getName().equals(taskName))
				return task;
		}
		return null;
	}

	private void setUpList() {
		fillTaskListWithCurrentBin();

		taskList.setPreferredSize(new Dimension(100, 200));
		
		taskList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String selectedTaskName = ((JList<String>)e.getSource()).getSelectedValue();
				
				Task task = getCorrespondingTask(selectedTaskName);
				splitPane.setRightComponent(new TaskPreview(task));
				splitPane.updateUI();
					
			}
		});
	}
	
	private void fillTaskListWithCurrentBin() {
		String[] bin = new String[controller.getBin().size()];
		int i = 0;
		for (Task task : controller.getBin()) {
			bin[i] = task.getName();
			i++;
		}
		taskList.setListData(bin);
		taskList.updateUI();
	}

	class TaskPreview extends JPanel {
		
		private Color color;
		private String text;
		private String name;
		
		public TaskPreview(Task task) {
			if (task == null)
				return;
			color = task.getColor();
			text = task.getText();
			name = task.getName();
			
			setUp();
		}

		private void setUp() {
			setPreferredSize(new Dimension(300, 100));
			setLayout(new BorderLayout());
			
			JLabel nameLabel = new JLabel(name);
			nameLabel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
			add(nameLabel, BorderLayout.NORTH);
			
			JLabel textLabel = new JLabel(text);
			textLabel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
			textLabel.setBackground(Color.white);
			add(textLabel, BorderLayout.CENTER);
			
			JPanel colorPanel = new JPanel();
			colorPanel.setBackground(color);
			colorPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
			add(colorPanel, BorderLayout.EAST);
			setVisible(true);
		}	
	}

}