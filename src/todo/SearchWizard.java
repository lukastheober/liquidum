package todo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */
public class SearchWizard extends MyDialog {
	
	public static void main(String[] args) {
		new SearchWizard(new Controller());
	}
	
	JTextField textField = new JTextField();

	public SearchWizard(Controller controller) {
		super(controller);
		
		setTitle("Suchen");
		
		setSize(400, 120);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(getParent());
		setResizable(false);
		
		setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		
		topPanel.add(new JLabel("Suchen:"), new GridBagConstraints(0, 0, 1, 1, 1, 1, 
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		
		topPanel.add(textField, new GridBagConstraints(1, 0, 1, 1, 20, 1, 
				GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		add(topPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridBagLayout());
		
		
		JButton cancel = new JButton("Abbrechen");
		bottomPanel.add(cancel, new GridBagConstraints(0, 0, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		
		
		JButton confirm = new JButton("Suchen");
		bottomPanel.add(confirm, new GridBagConstraints(1, 0, 1, 1, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
				new Insets(5, 5, 5, 5), 0, 0));
		
		add(bottomPanel, BorderLayout.SOUTH);
		
		
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.searchFor(textField.getText());
				dispose();
			}
		});
		
		setVisible(true);
		
	}

}