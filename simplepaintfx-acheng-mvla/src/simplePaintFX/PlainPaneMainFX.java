package simplePaintFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


// TODO: Auto-generated Javadoc
/**
 * The Class PlainPaneMainFX. This class simply creates the basic pane, scene and stage....
 * Testing another change
 */
public class PlainPaneMainFX extends Application {
	
	/** The scene. */
	private Scene scene;
	
	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 */
	public void start(Stage primaryStage) {
		PlainPane pane = new PlainPane();
		scene = new Scene(pane,700,500);
		primaryStage.setTitle("Simple Paint");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		pane.setWindow(scene.getWindow());
		
		
	}
	
	

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

}
