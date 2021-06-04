import java.util.logging.Logger;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

// TODO: Auto-generated Javadoc
/**
 * The Class ElevatorSimulation.
 */
//By Ethan
public class ElevatorSimulation extends Application {
	
	/** The controller. */
	private static ElevatorSimController controller;
	
	/** The curr floor. */
	private int currFloor;
	
	/** The floor. */
	public Label floor;
	
	/** The passengers. */
	public Label passengers;
	
	/** The currlog. */
	Boolean currlog = true;
	
	/** The cycle length. */
	int cycleLength=1;
	
	/** The time. */
	public Label time;
	
	/** The guifloor. */
	public int guifloor=0;
	
	/** The state. */
	public Label state; 
	
	/** The Constant STOP. */
	private final static int STOP = 0;
	
	/** The Constant MVTOFLR. */
	private final static int MVTOFLR = 1;
	
	/** The Constant OPENDR. */
	private final static int OPENDR = 2;
	
	/** The Constant OFFLD. */
	private final static int OFFLD = 3;
	
	/** The Constant BOARD. */
	private final static int BOARD = 4;
	
	/** The Constant CLOSEDR. */
	private final static int CLOSEDR = 5;
	
	/** The Constant MV1FLR. */
	private final static int MV1FLR = 6;
	
	/** The grid. */
	public Label grid[][] = new Label[10000][3];
	
	/** The pane. */
	private GridPane pane;
	
	/** The ri. */
	public GridPane ri;
	
	/** The build. */
	public GridPane build;
	

	/** The t. */
	Timeline t;
	
	/**
	 * Instantiates a new elevator simulation.
	 */
	public ElevatorSimulation() {
		controller = new ElevatorSimController(this);	
	}

	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		t = new Timeline(new KeyFrame(Duration.millis(10), ae -> {controller.stepSim(); }));


		t.setCycleCount(1);
		pane = new GridPane();
		floor = new Label("Floor #:");
		state = new Label("State:");
		passengers = new Label("Passenger Ct:");
		time = new Label("Time:");

		GridPane fl = new GridPane();
		fl.add(floor,0,0);
		fl.add(state,0,1);
		fl.add(passengers,0,2);
		fl.add(time,0,3);
		GridPane dn = new GridPane();
		
		Button pause = new Button("Pause Sim");
		pause.setOnAction(ae->t.stop());
		
		Button run = new Button("Run Sim");
		run.setOnAction(ae->{		
			t.setCycleCount(Animation.INDEFINITE);
			t.play();
			}
		);
		Button logof = new Button("Log ON/OFF");
		logof.setOnAction( ae->{controller.enableLogging(currlog); 
			if(currlog) {
				currlog=false;
			} else {
				currlog=true;
			}
		});
		
		dn.add(pause,0,0);
		dn.add(run,1,0);
		dn.add(logof,2,0);
		Label cyc = new Label("Enter Cycle Length:");
		TextField cyclen = new TextField();
		//check valid input
		cyclen.setOnAction(ae->t.setCycleCount(Integer.parseInt(cyclen.getText())));
		Button stepCyc = new Button("Step Cycle Ticks");
		stepCyc.setOnAction(ae->t.play());
		
		Button step1 = new Button("Step 1 Tick");
		step1.setOnAction(ae->{
			t.setCycleCount(1);
			t.play();
		});
		
		ri = new GridPane();
		ri.add(cyc,0,0);
		ri.add(cyclen,0,1);
		ri.add(stepCyc,1,0);
		ri.add(step1,2,0);
		pane.add(fl,0,0);
		pane.add(dn,0,1);
		pane.add(ri,1,1);
		build = new GridPane();
		//up is even, down is odd
		int ct = controller.getnumfl()*2-1;
		for(int i=0;i<controller.getnumfl();i++) {
			String temp = "";
			if(i==0) {
				temp = "*";
			}	
			grid[i][0]=new Label(temp + "Floor " + (i+1));
			grid[i][1]=new Label("Up: ");
			grid[i][2]=new Label("Down: ");
			build.add(grid[i][0],0,ct);
			build.add(grid[i][1],1,ct);
			build.add(grid[i][2],1,ct+1);
			ct-=2;
		}
		pane.add(build,0,3);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Elevator Simulation");
		primaryStage.show();
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main (String[] args) {
		Application.launch(args);
	}

}
