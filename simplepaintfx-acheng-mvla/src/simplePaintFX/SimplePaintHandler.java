 /**
 * 
 */
package simplePaintFX;


import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

// TODO: Auto-generated Javadoc
/**
 * The Class SimplePaintHandler.
 *
 * @author Scott
 */
public class SimplePaintHandler {
	
	/** The draw pane. */
	private SimplePaintPane drawPane;
	
	/**
	 * Instantiates a new simple paint handler.
	 *
	 * @param s the s
	 */
	public SimplePaintHandler(SimplePaintPane s) {
		drawPane = s;
	}
	
	/**
	 * Sets the color to the requested value.
	 */
	public void setColor(Color color) {	
		// TODO #1: complete this handler using methods in the drawing pane
		drawPane.setCurrentColor(color);
	}

	/**
	 * Clear drawing pane.
	 */
	public void clearDrawingPane() {	
		// TODO #2: complete this handler using methods in the drawing pane
		drawPane.clearPane();
	}
	
	/**
	 * Quit.
	 */
	public void quit () {
		System.exit(0);
	}
	
	/**
	 * Undo.
	 */
	public void undo () {
		// TODO #3: complete this handler using methods in the drawing pane
		//          you will likely need to make sure that the focus is in the drawing pane first...
		drawPane.removeOneLine();
	}
	
	
	// TODO #4: Review the rest of these methods - fundamentally, they are identical to SimplePaint,
	//          Note that we don't need placeholders for unused abstract methods.
	//          If you have questions, please ask.
	/**
	 * Mouse pressed.
	 *
	 * @param me the me
	 */
	public void mousePressed(MouseEvent me) {
		if (drawPane.isDragging()) 
			return;
		drawPane.setPrevX(me.getX());
		drawPane.setPrevY(me.getY());
		drawPane.setDragging(true);
		drawPane.requestFocus();
	}
	
	/**
	 * Mouse released.
	 *
	 * @param me the me
	 */
	public void mouseReleased(MouseEvent me) {
		if (!drawPane.isDragging()) 
			return;
		drawPane.setDragging(false);
	}

	/**
	 * Mouse dragged.
	 *
	 * @param me the me
	 */
	public void mouseDragged(MouseEvent me)  {
		double x = me.getX();
		double y = me.getY();

		if (x < 3) 
			x = 3;
		else if (x > drawPane.getWidth()) 
			x = drawPane.getWidth();
		
		if (y < 3) 
			y = 3;
		else if (y > drawPane.getHeight()) 
			y = drawPane.getHeight();
		
		drawPane.addLine(drawPane.getPrevX(),drawPane.getPrevY(),x, y);
		drawPane.setPrevX(x);
		drawPane.setPrevY(y);
	}
	
	/**
	 * Key pressed.
	 *
	 * @param ke the ke
	 */
	public void keyPressed(KeyEvent ke) {
		String key = ke.getText();
		System.out.println("pressed "+key);
		if (("Z".equals(key) || "z".equals(key)) && ke.isMetaDown()) {
			drawPane.removeOneLine();
		}
	}
}  
