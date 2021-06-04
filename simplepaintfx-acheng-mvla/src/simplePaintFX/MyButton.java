/**
 * 
 */
package simplePaintFX;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// TODO: Auto-generated Javadoc  
/**
 * The Class MyButton. This class simply mimics the look and feel of the original SimplePaint based upon Swing...
 *
 * @author Scott
 */
public class MyButton extends Button {
	
	/**
	 * Instantiates a new my button.
	 *
	 * @param txt the txt
	 */
	public MyButton (String txt) {
		super(txt);
		this.setPrefWidth(97);
		this.setPrefHeight(48);
		this.setFont(Font.font("Calibri",FontWeight.BOLD,14));
	}
	
}
