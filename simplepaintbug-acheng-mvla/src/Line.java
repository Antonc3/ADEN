
import java.awt.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class Line.
 */
// line holds all of the properties of a line, color, x1,x2,y1,y2
public class Line {

	/** The y 2. */
	private int x1, x2, y1, y2;
	
	/** The color. */
	private Color color;
	
	/**
	 * Instantiates a new line.
	 *
	 * @param x1 the x 1
	 * @param y1 the y 1
	 * @param x2 the x 2
	 * @param y2 the y 2
	 * @param color the color
	 */
	public Line(int x1, int y1, int x2, int y2, Color color) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.setColor(color);
	}

	/**
	 * Gets the x1.
	 *
	 * @return the x1
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * Sets the x1.
	 *
	 * @param x1 the new x1
	 */
	public void setX1(int x1) {
		this.x1 = x1;
	}

	/**
	 * Gets the x2.
	 *
	 * @return the x2
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * Sets the x2.
	 *
	 * @param x2 the new x2
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}

	/**
	 * Gets the y1.
	 *
	 * @return the y1
	 */
	public int getY1() {
		return y1;
	}

	/**
	 * Sets the y1.
	 *
	 * @param y1 the new y1
	 */
	public void setY1(int y1) {
		this.y1 = y1;
	}

	/**
	 * Gets the y2.
	 *
	 * @return the y2
	 */
	public int getY2() {
		return y2;
	}

	/**
	 * Sets the y2.
	 *
	 * @param y2 the new y2
	 */
	public void setY2(int y2) {
		this.y2 = y2;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
}