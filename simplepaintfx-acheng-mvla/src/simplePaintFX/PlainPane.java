package simplePaintFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Window;

// TODO: Auto-generated Javadoc
/**
 * The Class PlainPane.
 */
public class PlainPane extends BorderPane {
	
	/** The drawing surface pane. */
	SimplePaintPane drawingSurfacePane;
	
	/** The handler. */
	private SimplePaintHandler handler;
	
	/** The menu bar. */
	private MenuBar menuBar;
	
	/** The owner. */
	private Window owner;
	
	/**
	 * Instantiates a new plain pane.
	 */
	public PlainPane() {
		this.setBorder(new Border(new BorderStroke(Color.GRAY,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(3.0))));

		// TODO #1: create a pane to be the drawing surface of class SimplePaintPane
		// and place it in the Center of PlainPane
		 drawingSurfacePane = new SimplePaintPane();
		 this.setCenter(drawingSurfacePane);
	     // don't touch this stuff... 	

		// handler is analogous to SimplePaintListener in original program
		// All event-handling will be implemented in the Handler
		// TODO #2: You must provide your drawing pane to  SimplePaintHandler 
		handler = new SimplePaintHandler(drawingSurfacePane);
		// TODO #3: register the necessary handlers for events in drawingSurfacePane - refer to SimplePaint
		
		drawingSurfacePane.setOnMouseDragged(e ->{
			handler.mouseDragged(e);
		});
		drawingSurfacePane.setOnMousePressed(e ->{
			handler.mousePressed(e);
		});
		drawingSurfacePane.setOnMouseReleased(e -> {
			handler.mouseReleased(e);
		});
		drawingSurfacePane.setOnKeyPressed(e ->{
			handler.keyPressed(e);
		});
		// Creating a Vertical Box to hold the buttons, setting the outside margins and alignment
		VBox btnBox = new VBox(5); 
		btnBox.setPadding(new Insets(3));  
		btnBox.setAlignment(Pos.CENTER);

		
		// using MyButton class to create a similar look and feel with original program.
		// TODO #4 :Create the rest of the buttons (white and colorpicker are already done)
		//          and register the handler of each button (except colorpicker) 
		MyButton white = new MyButton("White");
		white.setOnAction(e ->{
			handler.setColor(Color.WHITE);
		});
		MyButton red = new MyButton("Red");
		red.setOnAction(e ->{
			handler.setColor(Color.RED);
		});
		MyButton yellow = new MyButton("Yellow");
		yellow.setOnAction(e ->{
			handler.setColor(Color.YELLOW);
		});
		MyButton green = new MyButton("Green");
		green.setOnAction(e ->{
			handler.setColor(Color.GREEN);
		});
		MyButton blue = new MyButton("Blue");
		blue.setOnAction(e ->{
			handler.setColor(Color.BLUE);
		});
		MyButton cyan = new MyButton("Cyan");
		cyan.setOnAction(e ->{
			handler.setColor(Color.CYAN);
		});
		MyButton magenta = new MyButton("Magenta");
		magenta.setOnAction(e ->{
			handler.setColor(Color.MAGENTA);
		});
//		MyButton white = new MyButton("White");
		
	
		// accessing the color picker in JavaFX is a pain - so don't touch this stuff
		Text custom = new Text("\nCustom Color");
		custom.setFont(Font.font("Calibri",FontWeight.BOLD,14));
		custom.setFill(Color.RED);
		final ColorPicker colorPicker = new ColorPicker();	
		colorPicker.getStyleClass().add("button");
		colorPicker.setPrefHeight(48);
		colorPicker.setPrefWidth(97);
		colorPicker.setOnAction(e -> handler.setColor(colorPicker.getValue()));
		MyButton clear = new MyButton("Clear");
		clear.setOnAction(e -> {
			drawingSurfacePane.clearPane();
		});
		
		// TODO #5: Add all the buttons to the button box and place it in the right section of this pane
		btnBox.getChildren().addAll(white,red,yellow,green,blue,cyan,magenta,colorPicker,clear);
		this.setRight(btnBox);
		
		// Creating menus - we have not gone over this... but take notes on the syntax
		Menu simplePaint = new Menu("Simple Paint");
		MenuItem quit = new MenuItem("Quit");
		simplePaint.getItems().add(quit);     
	    
	    Menu editMenu = new Menu("Edit");
	    MenuItem undo = new MenuItem("Undo");
	    editMenu.getItems().add(undo);

	    // TODO #6: Register the handlers for the quit and undo menu actions - using lambdas
		quit.setOnAction(e -> {
			System.exit(0);
		});
	    undo.setOnAction(e ->{
	    	drawingSurfacePane.removeOneLine();
	    });
	    menuBar = new MenuBar(simplePaint,editMenu);  // add the two menu options...
	    this.setTop(menuBar);
		
	}

	/**
	 * Sets the window.
	 *
	 * @param window the new window
	 */
	public void setWindow(Window window) {
		owner = window;
	}
	
	/**
	 * Gets the menu bar.
	 *
	 * @return the menu bar
	 */
	public MenuBar getMenuBar() {
		return menuBar;
	}
}
