
import javax.swing.*;
import java.awt.*;

// This is the specification for the custom container panel 
// that will be added to the JFrame object 'window'
public class PlainPanel extends JPanel {

	private JMenuBar menuBar;
	 SimplePaintPanel drawingSurfacePanel;
	
	/**
	 * Instantiates a new plain panel.
	 */
	public PlainPanel() {
		
		drawingSurfacePanel = new SimplePaintPanel();
		SimplePaintListener listener = new SimplePaintListener(drawingSurfacePanel);

		// Set current object's layout
		this.setLayout(new BorderLayout());
		
		// east will hold the JButtons
		// (no need to create custom JPanel class for this)
		JPanel east = new JPanel();
		east.setLayout(new GridLayout(9,1));
		
		JButton white = new JButton("White");
		white.addActionListener(listener);
		JButton red = new JButton("Red");
		red.addActionListener(listener);
		JButton blue = new JButton("Green");
		blue.addActionListener(listener);
		JButton green = new JButton("Blue");
		green.addActionListener(listener);
		JButton cyan = new JButton("Cyan");
		cyan.addActionListener(listener);
		JButton magenta = new JButton("Magenta");
		magenta.addActionListener(listener);
		JButton yellow = new JButton("Yellow");
		yellow.addActionListener(listener);
		JButton custom = new JButton("Custom");
		custom.addActionListener(listener);
		JButton clear = new JButton("Clear");
		clear.addActionListener(listener);
		
		east.add(white);
		east.add(red);
		east.add(blue);
		east.add(green);
		east.add(cyan);
		east.add(magenta);
		east.add(yellow);
		east.add(custom);
		east.add(clear);
		
		this.add(east,BorderLayout.EAST);
		this.add(drawingSurfacePanel, BorderLayout.CENTER); 
		
		//set up menus
		JMenu simplePaint = new JMenu("Simple Paint");
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(listener);
		simplePaint.add(quit);
		
		JMenu editMenu = new JMenu("Edit");
		JMenuItem undo = new JMenuItem("Undo");
		undo.addActionListener(listener);
		editMenu.add(undo);
		
		menuBar = new JMenuBar();
		menuBar.add(simplePaint);
		menuBar.add(editMenu);
	}
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}
	
	public SimplePaintPanel getSimplePaintPanel() {
		return drawingSurfacePanel;
	}

}
