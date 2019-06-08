package todo;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */
public class GUI extends JFrame {
	
	private ListContainer listContainer = new ListContainer();
	private MainMenuBar menuBar = new MainMenuBar();
	
	public GUI() {
		JFrame guiFrame = new JFrame("Liquidum");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		
		//fullscreen
		guiFrame.setSize(height, width);
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//charming padding(9/10screen)
//		height = (height/10*9);
//		width = (width/10*9);
//		guiFrame.setSize(height, width);
		
		guiFrame.setJMenuBar(menuBar);
		//TODO contentpane for listcontainer. has to be scrollable. fixed size?
		guiFrame.setVisible(true);
	}

	/**
	 * Updates the whole UI.
	 * JComponents can be updated using updateUI().
	 */
	public void update(){
		menuBar.updateUI();
		listContainer.updateUI();
	}
}