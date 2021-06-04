
import java.awt.Color;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.*;

import javax.swing.JColorChooser;

public class SimplePaintListener implements MouseListener, MouseMotionListener, ActionListener, KeyListener {


	/** The panel. */
	private SimplePaintPanel panel;


	public SimplePaintListener(SimplePaintPanel s) {
		panel = s;
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		panel.addKeyListener(this);
		
	}
	 

    public void mousePressed(MouseEvent evt) {
        if (panel.isDragging())  // Ignore mouse presses that occur
            return;            //    when user is already drawing a curve.
                               //    (This can happen if the user presses
                               //    two mouse buttons at the same time.)
        panel.setPrevX(evt.getX());
        panel.setPrevY(evt.getY());
        panel.setDragging(true);
        

        panel.requestFocusInWindow();  //*** Necessary, e.g. if you draw a line right away and try to Undo with Keyboard
    } 

    public void mouseReleased(MouseEvent evt) {
        if (!panel.isDragging())
            return;  // Nothing to do because the user isn't drawing.
        panel.setDragging(false);
    }


    public void mouseDragged(MouseEvent evt) {
    		if (!panel.isDragging())
            return;  // Nothing to do because the user isn't drawing.

        int x = evt.getX();   // x-coordinate of mouse.
        int y = evt.getY();   // y-coordinate of mouse.

        if (x < 3)                          // Adjust the value of x,
            x = 3;                           //   to make sure it's in
        if (x > panel.getWidth())       //   the drawing area.
            x = panel.getWidth();
        

        if (y < 3)                          // Adjust the value of y,
            y = 3;                           //   to make sure it's in
        if (y > panel.getHeight())       //   the drawing area.
            y = panel.getHeight();

        panel.addLine(panel.getPrevX(), panel.getPrevY(), x, y);  //***update: simply add the line to the ArrayList, will be drawn laer
        panel.repaint(); //***added because the lines weren't showing up until you clicked the 
        				//the next color
        panel.setPrevX(x);  // Get ready for the next line segment in the curve.
        panel.setPrevY(y);

    } // end mouseDragged()



    public void mouseEntered(MouseEvent evt) { }   // Some empty routines.
    

    public void mouseExited(MouseEvent evt) { }    //    (Required by the MouseListener
    

    public void mouseClicked(MouseEvent evt) { }   //    and MouseMotionListener
    
 
    public void mouseMoved(MouseEvent evt) { }     //    interfaces).


	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		System.out.println(e.toString());

		if(button.equals("White")) 
			panel.setCurrentColor(Color.WHITE);
		else if(button.equals("Red"))
			panel.setCurrentColor(Color.RED);
		else if(button.equals("Green"))
			panel.setCurrentColor(Color.GREEN);
		else if(button.equals("Blue"))
			panel.setCurrentColor(Color.BLUE);
		else if(button.equals("Cyan"))
			panel.setCurrentColor(Color.CYAN);
		else if(button.equals("Magenta"))
			panel.setCurrentColor(Color.MAGENTA);
		else if(button.equals("Yellow"))
			panel.setCurrentColor(Color.YELLOW);
		else if(button.equals("Custom")) 
			panel.setCurrentColor(JColorChooser.showDialog(panel,
                    "Custom Color", panel.getCurrentColor()));
		else if(button.equals("Clear"))
			panel.clearAllLines();
		else if(button.equals("Quit"))
			System.exit(0);
		else if (button.equals("Undo"))
			panel.removeOneLine();
		//fixed magenta to yellow and vice versa
		//changed undo to remove one line
		//**** Button caused focus to be lost, so can simply set focus here
		panel.requestFocusInWindow();
		
		panel.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("pressed");
		char ch = e.getKeyChar();
		if(ch == 'z')
			panel.removeOneLine();
		panel.repaint();
	}
	//changed upper case z to lower case z


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}
