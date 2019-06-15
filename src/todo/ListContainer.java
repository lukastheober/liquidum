package todo;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */
public class ListContainer extends JPanel {

	public ListContainer(){
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setLayout(new FlowLayout(FlowLayout.LEFT));
	}

	public void loadListsOfTasks(LinkedList<ListOfTasks> inListOfTasks) {
		for(ListOfTasks lot : inListOfTasks) {
			add(lot);
		}
	}
	
}