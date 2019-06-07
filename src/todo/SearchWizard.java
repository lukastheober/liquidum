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
	
	JTextField textField = new JTextField();

	public SearchWizard(Controller controller) {
		super(controller);
		
		setTitle("Suchen");
		
		setSize(400, 120);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(getParent());
		setResizable(false);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.fill = GridBagConstraints.NONE;
		add(new JLabel("Suchen:"), c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_END;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(textField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.fill = GridBagConstraints.HORIZONTAL;
		JButton cancel = new JButton("Abbrechen");
		add(cancel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.fill = GridBagConstraints.HORIZONTAL;
		JButton confirm = new JButton("Suchen");
		add(confirm, c);
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.searchFor(textField.getText());
			}
		});
		
		setVisible(true);
		
	}

}