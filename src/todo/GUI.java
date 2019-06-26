package todo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */
public class GUI extends JFrame {

	private ListContainer listContainer = new ListContainer();
	private MainMenuBar menuBar = new MainMenuBar();
	private int mainWindowWidth;
	private int mainWindowHeight;
	private ImageIcon programmIcon;
	
	public GUI() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		mainWindowHeight = screenSize.height;
		mainWindowWidth = screenSize.width;
		
		programmIcon = new ImageIcon("favicon.png");
		this.setIconImage(programmIcon.getImage());
		
        //window settings
        setSize(mainWindowWidth, mainWindowHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Liquidum 1.0.00");
        setLayout(new BorderLayout());
        
        //menuBar
        menuBar = new MainMenuBar();
        setJMenuBar(menuBar);
        
        //content
        listContainer = new ListContainer();
        add(listContainer, BorderLayout.CENTER);
        
        setVisible(true);

        //Look and feel
	    try {
	    	 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
        } catch (InstantiationException e) {
                e.printStackTrace();
        } catch (IllegalAccessException e) {
                e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
        }
	}
	
	/**
	 * Updates the whole UI.
	 * JComponents can be updated using updateUI().
	 */
	public void update(){
		menuBar.updateUI();
		listContainer.updateUI();
	}

	public MainMenuBar getMainMenuBar() {
		return menuBar;
	}
	public ListContainer getListContainer() {
		return listContainer;
	}
}