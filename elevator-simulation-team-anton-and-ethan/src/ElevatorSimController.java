import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Node;import javafx.scene.control.Label;

//By Ethan
public class ElevatorSimController {
	private ElevatorSimulation gui;
	private Building building;
	private int stepCnt = 0;
	private int numfl;
	private boolean endSim = false;
	int idct=0;
	public String passCSV;
	private ArrayList<Passengers> pList;
	
	/**
	 * Instantiates a new elevator sim controller.
	 *
	 * @param gui the gui
	 */
	public ElevatorSimController(ElevatorSimulation gui) {
		this.gui = gui;
		pList = new ArrayList<Passengers>();
		getConfig("ElevatorSimConfig2.csv");
	}
	public int getnumfl() {
		return numfl;
	}
	public int getStep() {
		return stepCnt;
	}
	private void getConfig(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			line = br.readLine();
			String[] values = line.split(",");
			numfl=Integer.parseInt(values[1]);
			line = br.readLine();
			values = line.split(",");
			int numel=Integer.parseInt(values[1]);
			line = br.readLine();
			values = line.split(",");
			passCSV=(values[1]);
			line = br.readLine();
			values = line.split(",");
			int cap=Integer.parseInt(values[1]);
			line = br.readLine();
			values = line.split(",");
			int floorti=Integer.parseInt(values[1]);
			line = br.readLine();
			values = line.split(",");
			int doorti=Integer.parseInt(values[1]);
			line = br.readLine();
			values = line.split(",");
			int tickp=Integer.parseInt(values[1]);
			InitializePassengerData(passCSV);
			building = new Building(passCSV,numfl, numel,cap,floorti,doorti,tickp,pList);
		} catch (IOException e) { 
			System.err.println("Error in reading file: "+filename);
			e.printStackTrace();
		}
	}
	private void InitializePassengerData(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			boolean fi = true;
			while ((line = br.readLine())!= null) {
				String[] values = line.split(",");
				//System.out.println("Here");
				if(!fi) {
					int t = Integer.parseInt(values[0]);
					int p = Integer.parseInt(values[1]);
					int ff = Integer.parseInt(values[2])-1;
					int tf = Integer.parseInt(values[3])-1;
					String con=values[4];
					boolean po = false;
					if(con.equals("TRUE")) {
						po=true;
					}
					int wa = Integer.parseInt(values[5]);
					System.out.println(idct + " " + t + " "+p+" "+ff+" "+tf+" "+wa+" "+po);
					pList.add(new Passengers(idct,t,p,ff,tf,wa,po));
					idct++;
				} else {
					fi=false;
				}
			}
		} catch (IOException e) { 
			System.err.println("Error in reading file: "+filename);
			e.printStackTrace();
		}
	}
	
	public void enableLogging(Boolean yes) {
		if(yes) {
			building.log(true);
			System.out.println("LOG on");
		} else {
			building.log(false);
			System.out.println("LOG off");
		}
	}
 	public void stepSim() {
		stepCnt++;
		building.updateElevator(stepCnt);
		gui.time.setText("Time: " + stepCnt);
		gui.floor.setText("Floor #: " + (building.getCurrFloor() + 1));
		gui.passengers.setText("Passenger Ct: " + building.getTotPass());
		gui.state.setText("State: " + building.printState(building.lift.getCurrState()));
		for(int i=0;i<numfl;i++) {
			if(i==building.getCurrFloor() ) {	
				if(gui.grid[i][0].getText().charAt(0)!='*')
				gui.grid[i][0].setText("*"+gui.grid[i][0].getText());
			} else if(gui.grid[i][0].getText().charAt(0)=='*') {
				gui.grid[i][0].setText(gui.grid[i][0].getText().substring(1,gui.grid[i][0].getText().length()));
			}
			String passUp = building.floors[i].getUpQ().toString();
			gui.grid[i][1].setText("Up: " + passUp);
			String passDown = building.floors[i].getdownQ().toString();
			gui.grid[i][2].setText("Down: " + passDown);
		}
		/*
		for(int i=0;i<numfl;i++) {
			Label temp = new Label("ok");
			int ri=0;
			int ci=0;
			String passUp = building.floors[i].getUpQ().toString();
		    for(Node child: gui.build.getChildren()) {
		    	//1 rem is up, 0 rem is down
		    	if ((gui.build.getRowIndex(child)+1) % 2 == 0) {
		    		if((numfl - gui.build.getRowIndex(child)+1)/2==i && gui.build.getColumnIndex(child)==1) {
			    		temp = (Label)child;
			    		ri=gui.build.getRowIndex(child);
			    		ci=1;
			    		break;
			    	}
		    	}
		    	
		    }
		    gui.build.getChildren().remove(temp);
		    temp.setText("Up: " + passUp);
		    gui.build.add(temp,ci,ri);
			String passDown = building.floors[i].getdownQ().toString();
			for(Node child: gui.build.getChildren()) {
		    	//check div by 2
				if ((gui.build.getRowIndex(child)) % 2==0) {
					if(numfl - gui.build.getRowIndex(child)/2==i && gui.build.getColumnIndex(child)==1) {
			    		temp = (Label)child;
			    		ri=gui.build.getRowIndex(child);
			    		ci=1;
			    		break;
			    	}
				}
		    }
		    gui.build.getChildren().remove(temp);
		    temp.setText("Down: " + passDown);
		    gui.build.add(temp,ci,ri);
		}
		*/
		
		//move elevator
		//changing an element of a pane actually changes
		/*
		if(building.getCurrFloor() != gui.guifloor) {
			int ri=0;
			int ci=0;
			Label temp = new Label("ok");
			
			for(Node child: gui.build.getChildren()) {
				int r = gui.build.getRowIndex(child);
			    int c = gui.build.getColumnIndex(child);
			    if(c==0&&numfl-(r+1)/2==gui.guifloor) {
			    	temp = (Label)child;
			    	ri=r;
			    	ci=0;
			    	break;
			    }
			}
			gui.build.getChildren().remove(temp);
			temp.setText(temp.getText().substring(1,temp.getText().length()));
	    	gui.build.add(temp,ci,ri);
			
			Label leb = new Label("hi");
			for(Node child: gui.build.getChildren()) {
				int r = gui.build.getRowIndex(child);
			    int c = gui.build.getColumnIndex(child);
			    if(c==0&&numfl-(r+1)/2==building.getCurrFloor()) {
			    	leb = (Label)child;
			    	ri=r;
			    	ci=0;
			    	break;
			    }
			}
			gui.build.getChildren().remove(leb);
			leb.setText("*" + leb.getText());
	    	gui.build.add(leb,ci,ri);
	    	gui.guifloor = building.getCurrFloor();
		}
		*/
	}

}