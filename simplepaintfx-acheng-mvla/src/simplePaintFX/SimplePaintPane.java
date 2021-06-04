package simplePaintFX;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

// TODO: Auto-generated Javadoc
/**
 * The Class SimplePaintPane.
 */
public class SimplePaintPane extends Pane {
	
	/** The prev Y. */
	private double prevX, prevY;
	
	/** The dragging. */
	private boolean dragging;
	
	/** The current color. */
	private Color currentColor = Color.WHITE;
	
	/**
	 * Instantiates a new simple paint pane.
	 */
	SimplePaintPane() {
		this.setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY,new Insets(0))));
	}
		
	/**
	 * Adds the line.
	 *
	 * @param prevX the prev X
	 * @param prevY the prev Y
	 * @param currX the curr X
	 * @param currY the curr Y
	 */
	public void addLine(double prevX, double prevY, double currX, double currY) {
		// TODO #1:   Create a new line, set its color and add it to the drawing pane
		Line l = new Line(prevX,prevY,currX,currY);
		l.setStroke(this.getCurrentColor());
		this.getChildren().add(l);
	}
	
	/**
	 * Removes the one line.
	 */
	public void removeOneLine() {
		// TODO #2:    Write the code to remove the last line added to the drawing pane....
		// this.getChildren() returns an ObservableList...
		// How do you find the size of the list?
		// How can you remove an element from the list?
		// Great questions - javadocs ObservableList has the answers...
		// One final thought - what are the boundary conditions that you need to consider?
		
		if(this.getChildren().size() > 0) {
			this.getChildren().remove(this.getChildren().size()-1);
		}
		// cause the repaint
		this.requestLayout();
		
	}
	
	/**
	 * Gets the prev X.
	 *
	 * @return the prev X
	 */
	public double getPrevX() {
		return prevX;
	}
	
	/**
	 * Sets the prev X.
	 *
	 * @param prevX the new prev X
	 */
	public void setPrevX(double prevX) {
		this.prevX = prevX;
	}
	
	/**
	 * Gets the prev Y.
	 *
	 * @return the prev Y
	 */
	public double getPrevY() {
		return prevY;
	}
	
	/**
	 * Sets the prev Y.
	 *
	 * @param prevY the new prev Y
	 */
	public void setPrevY(double prevY) {
		this.prevY = prevY;
	}
	
	/**
	 * Checks if is dragging.
	 *
	 * @return true, if is dragging
	 */
	public boolean isDragging() {
		return dragging;
	}
	
	/**
	 * Sets the dragging.
	 *
	 * @param dragging the new dragging
	 */
	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}
	
	/**
	 * Gets the current color.
	 *
	 * @return the current color
	 */
	public Color getCurrentColor() {
		return currentColor;
	}
	
	/**
	 * Sets the current color.
	 *
	 * @param currentColor the new current color
	 */
	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}
	
	/**
	 * Clear pane.
	 */
	public void clearPane() {
		// TODO #3:    Clear all children from the drawing pane...
		this.getChildren().clear();

		this.requestLayout();
	}
	
}
