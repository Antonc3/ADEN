import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

// TODO: Auto-generated Javadoc
/**
 * The Class Building.
 */
//Written By Anton
public class Building {
	
	/** The Constant LOGGER. */
	private final static Logger LOGGER = Logger.getLogger(Building.class.getName());
	
	/** The fh. */
	private FileHandler fh;
	
	/** The Constant STOP. */
	// Elevator State Variables
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
	
	/** The num floors. */
	private final int NUM_FLOORS;
	
	/** The num elevators. */
	private final int NUM_ELEVATORS;
	
	/** The floors. */
	public Floor[] floors;
	
	/** The lift. */
	public Elevator lift;
	
	/** The cur pass. */
	private int curPass = 0;
	
	/** The p list. */
	private ArrayList<Passengers> pList;
	
	/** The arrived at destination. */
	private ArrayList<Passengers> arrivedAtDestination;
	
	/** The gave up. */
	private ArrayList<Passengers> gaveUp;
	
	/** The check polite. */
	private boolean checkPolite = false;
	
	/** The pass data file. */
	private String passDataFile;
	
	/**
	 * Instantiates a new building.
	 *
	 * @param fna the fna
	 * @param numFloors the num floors
	 * @param numElevators the num elevators
	 * @param capacity the capacity
	 * @param ticksPerFlr the ticks per flr
	 * @param doorTicks the door ticks
	 * @param passengersPerTick the passengers per tick
	 * @param pasList the pas list
	 */
	public Building(String fna, int numFloors, int numElevators,int capacity, int ticksPerFlr, int doorTicks, int passengersPerTick,ArrayList<Passengers> pasList) {
//		System.out.println(" numFloors "+numFloors+" numElevators "+numElevators +" ticksPerFlr " +ticksPerFlr +" doorTicks "+doorTicks + " passengerPerTick " + passengersPerTick);
		NUM_FLOORS = numFloors;
		NUM_ELEVATORS = numElevators;
		passDataFile=fna.substring(0,fna.length()-4) + "passData.csv";
		System.out.println(passDataFile);
		System.setProperty("java.util.logging.SimpleFormatter.format","%4$-7s %5$s%n");
		LOGGER.setLevel(Level.OFF);
		try {
			fh = new FileHandler(fna.substring(0,fna.length()-4) + ".log");
			LOGGER.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// create the floors
		floors = new Floor[NUM_FLOORS];
		for (int i = 0; i < NUM_FLOORS; i++) {
			floors[i]= new Floor(100); 
		}
		floors[0].setLoggerFH(fh); // only need to pass the file to one of the floors.
		lift = new Elevator(NUM_FLOORS);
		lift.setLoggerFH(fh);
		lift.setCapacity(capacity);
		lift.setPassPerTick(passengersPerTick);
		lift.setTicksDoorOpenClose(doorTicks);
		lift.setTicksPerFloor(ticksPerFlr);
		gaveUp = new ArrayList();
		arrivedAtDestination = new ArrayList();
		pList = pasList;
		log(true);
		LOGGER.info("CONFIG: Capacity="+lift.getCapacity()+" Ticks-Floor="
				+lift.getTicksPerFloor()+" Ticks-Door="+lift.getTicksDoorOpenClose()
				+" Ticks-Passengers="+lift.getPassPerTick());
	}
	
	
	/**
	 * Update elevator.
	 *
	 * @param time the time
	 */
	public void updateElevator(int time) {
		
		if (elevatorStateChanged(lift)) 
			LOGGER.info("Time="+time+"   Prev State: " + printState(lift.getPrevState()) + "   Curr State: "+printState(lift.getCurrState())
			+"   PrevFloor: "+(lift.getPrevFloor()+1) + "   CurrFloor: " + (lift.getCurrFloor()+1) /*+ "  current passnegers: " +lift.getPassengers()*/);
 
		while(curPass < pList.size() && time == pList.get(curPass).getTime()) {
			Passengers p = pList.get(curPass);
			LOGGER.info("Time="+time+" Called="+p.getNumber()+" Floor="+
					(p.getFromFloor()+1)
					+" Dir="+((p.getFromFloor() < p.getToFloor())?"Up":"Down")+" passID=" + p.getId());
			this.addPassenger(p);
            curPass++;
        }
		if(curPass >= pList.size() && !checkForPassengers() && lift.getCurrState() == STOP&& lift.getPrevState()==STOP) {
			LOGGER.info("Time="+time+" Detected End of Simulation");
			processPassengerData();
			System.exit(0);
		}
		switch (lift.getCurrState()) {
			case STOP: lift.updateCurrState(currStateStop(time,lift)); break;
			case MVTOFLR: lift.updateCurrState(currStateMvToFlr(time,lift)); break;
			case OPENDR: lift.updateCurrState(currStateOpenDr(time,lift)); break;
			case OFFLD: lift.updateCurrState(currStateOffLd(time,lift)); break;
			case BOARD: lift.updateCurrState(currStateBoard(time,lift)); break;
			case CLOSEDR: lift.updateCurrState(currStateCloseDr(time,lift)); break;
			case MV1FLR: lift.updateCurrState(currStateMv1Flr(time,lift)); break;
		}
		
		
	}
	
	/**
	 * Elevator state changed.
	 *
	 * @param l the l
	 * @return true, if successful
	 */
	private boolean elevatorStateChanged(Elevator l) {
		return (l.getPrevState() != l.getCurrState() || l.getCurrFloor() != l.getPrevFloor());
	}
	
	/**
	 * Prints the state.
	 *
	 * @param state the state
	 * @return the string
	 */
	public String printState(int state) {
		switch (state) {
			case STOP: return "STOP   ";
			case MVTOFLR: return "MVTOFLR";
			case OPENDR: return "OPENDR ";
			case OFFLD: return "OFFLD  ";
			case BOARD: return "BOARD  ";
			case CLOSEDR: return "CLOSEDR";
			case MV1FLR: return "MV1FLR ";
		}
		return "";
	}
	
	/**
	 * Gets the curr floor.
	 *
	 * @return the curr floor
	 */
	public int getCurrFloor() {
		return lift.getCurrFloor();
	}
	
	/**
	 * Gets the tot pass.
	 *
	 * @return the tot pass
	 */
	public int getTotPass() {
		return lift.getPassengers();
	}
	
	/** The going up. */
	private boolean goingUp = false;
	
	/** The go to flr. */
	private int goToFlr = 0;
	
	/**
	 * Curr state stop.
	 *
	 * @param time the time
	 * @param lift the lift
	 * @return the int
	 */
	private int currStateStop(int time, Elevator lift) {
		//no passengers
		int upQSize = 0;
		int firstUpQ = NUM_FLOORS;
		int downQSize = 0;
		int firstDownQ = -1;
		for(int i =0; i < NUM_FLOORS; i++) {
			upQSize+=floors[i].upQSize();
			firstUpQ = Math.min(firstUpQ, (floors[i].upQEmpty()) ? NUM_FLOORS : i);
			downQSize+=floors[i].downQSize();
			firstDownQ = Math.max(firstDownQ, (floors[i].downQEmpty()) ? -1 : i);
		}
		boolean curDownFloor = (floors[lift.getCurrFloor()].downQEmpty());
		boolean curUpFloor = floors[lift.getCurrFloor()].upQEmpty();
		if(!curDownFloor || !curUpFloor) {
			if(!curDownFloor && !curUpFloor) {
				lift.setDirection((upQSize >= downQSize) ? 1 : -1);
			}
			else if(!curDownFloor) {
				lift.setDirection(-1);
			}
			else {
				lift.setDirection(1);
			}
			return OPENDR;
		}
		if(upQSize != 0 || downQSize != 0) {
			if(upQSize > 0 && upQSize > downQSize) {
				if(lift.getCurrFloor() > firstUpQ) {
					lift.setDirection(-1);
				}
				else{
					lift.setDirection(1);
				}
				goToFlr = firstUpQ;
				goingUp = true;
				return MVTOFLR;
			}
			else if(downQSize > 0 && upQSize < downQSize) {
				//want to go to firstDownq
				if(lift.getCurrFloor() > firstDownQ) {
					lift.setDirection(-1);
				}
				else{
					lift.setDirection(1);
				}
				goToFlr = firstDownQ;
				goingUp = false;
				return MVTOFLR;
			}
			else {
				int curFlr = lift.getCurrFloor();
				if(Math.abs(firstUpQ-curFlr) > Math.abs(firstDownQ-curFlr)) {
					if(lift.getCurrFloor() > firstDownQ) {
						lift.setDirection(-1);
					}
					else {
						lift.setDirection(1);
					}
					goToFlr = firstDownQ;
					goingUp = false;
					return MVTOFLR;
				}
				else if(Math.abs(firstUpQ-curFlr) < Math.abs(firstDownQ-curFlr)) {
					if(lift.getCurrFloor() > firstUpQ) {
						lift.setDirection(-1);
					}
					else {
						lift.setDirection(1);
					}
					goToFlr = firstUpQ;
					goingUp = true;
					return MVTOFLR;	
				}
				else {
					if(lift.getCurrFloor() > firstUpQ) {
						lift.setDirection(-1);
					}
					else {
						lift.setDirection(1);
					}
					goToFlr = firstUpQ;
					goingUp = true;
					return MVTOFLR;
				}
			}		
		}
		return STOP;
	}

	/**
	 * Curr state mv to flr.
	 *
	 * @param time the time
	 * @param lift the lift
	 * @return the int
	 */
	private int currStateMvToFlr(int time, Elevator lift) {
		//assume no passengers inside
//		System.out.println(goToFlr);
//		System.out.println(lift.getDirection());
		if(lift.getCurrFloor() != goToFlr) {
			lift.moveElevator();
			if(lift.getCurrFloor() == goToFlr) {
				if(goingUp) {
					lift.setDirection(1);
				}
				else {
					lift.setDirection(-1);
				}
				return OPENDR;
			}
			return MVTOFLR;
		}
		if(goingUp) {
			lift.setDirection(1);
		}
		else {
			lift.setDirection(-1);
		}
		return OPENDR;
	}
	
	/**
	 * Curr state open dr.
	 *
	 * @param time the time
	 * @param lift the lift
	 * @return the int
	 */
	private int currStateOpenDr(int time, Elevator lift) {
		if(!lift.isDoorOpen()) {
			lift.openDoor();
		}
		if(lift.isDoorOpen()) {
			int dir = lift.getDirection();
			if(!lift.getPList(lift.getCurrFloor()).isEmpty()) {
				return OFFLD;
			}
			if(dir == -1) {
				//going down
				if(lift.getCapacity() > lift.getPassengers()) {
					if(!floors[lift.getCurrFloor()].downQEmpty()) {
						return BOARD;
					}
				}
			}
			if(dir == 1) {
				//going up
				if(lift.getCapacity() > lift.getPassengers()) {
					if(!floors[lift.getCurrFloor()].upQEmpty()) {
						return BOARD;
					}
				}
			}
			return CLOSEDR;
			
		}
		return OPENDR;
	}
	
	/**
	 * Curr state off ld.
	 *
	 * @param time the time
	 * @param lift the lift
	 * @return the int
	 */
	private int currStateOffLd(int time, Elevator lift) {
		
		if(lift.getPrevState() != OFFLD) {
			int offldDelay = 0;
			while(lift.hasPassenger(lift.getCurrFloor())) {
				Passengers p = lift.getPassenger(lift.getCurrFloor());
				offldDelay +=p.getNumber();
				LOGGER.info("Time="+time+" Arrived="+p.getNumber()+" Floor="+ (lift.getCurrFloor()+1)
						+" passID=" + p.getId());
				lift.removePassenger(lift.getCurrFloor());
				p.setTimeArrived(time);
				arrivedAtDestination.add(p);
			}
			lift.setOffldDelay(offldDelay);
		}
		lift.updTimeInOffld();
		if(lift.offldDone()) {
			lift.setOffldDelay(0);
			int dir = lift.getDirection();
			if(dir == -1) {
				//going down
				if(lift.getCapacity() > lift.getPassengers()) {
					if(!floors[lift.getCurrFloor()].downQEmpty()) {
						return BOARD;
					}
				}
				if(lift.getPassengers() == 0) {
					if(!floors[lift.getCurrFloor()].upQEmpty() && !hasPassengersInDir()) {
						lift.setDirection(1);
						return BOARD;
					}
				}
			}
			if(dir == 1) {
				//going up
				if(lift.getCapacity() > lift.getPassengers()) {
					if(!floors[lift.getCurrFloor()].upQEmpty()) {
						return BOARD;
					}
				}
				if(lift.getPassengers() == 0) {
					if(!floors[lift.getCurrFloor()].downQEmpty()&& !hasPassengersInDir()) {
						lift.setDirection(-1);
						return BOARD;
					}
				}
			}
			return CLOSEDR;
		}
		return OFFLD;
	}
	
	/**
	 * Curr state board.
	 *
	 * @param time the time
	 * @param lift the lift
	 * @return the int
	 */
	private int currStateBoard(int time, Elevator lift) {
		while(lift.getPassengers() < lift.getCapacity() && hasNextPassenger()) {
			Passengers p = getNextPassenger();
			if(p.getTime() + p.getGiveUpTime() < time) {
				LOGGER.info("Time="+time+" GaveUp="+p.getNumber()+" Floor="+ (lift.getCurrFloor()+1)
						+" Dir="+((lift.getDirection()>0)?"Up":"Down")+" passID=" + p.getId());
				gaveUp.add(p);
				p.setGiveUpTime(time);
				popNextPassenger();
			}
			else if(p.getNumber()+lift.getPassengers() > lift.getCapacity()) {
				LOGGER.info("Time="+time+" Skip="+p.getNumber()+" Floor="+ (lift.getCurrFloor()+1)
						+" Dir="+((lift.getDirection()>0)?"Up":"Down")+" passID=" + p.getId());
				break;
			}
			else {
				lift.setOffldDelay(lift.getOffldDelay()+p.getNumber());
				//setBoardTime
				LOGGER.info("Time="+time+" Board="+p.getNumber()+" Floor="+ (lift.getCurrFloor()+1)
						+" Dir="+((lift.getDirection()>0)?"Up":"Down")+" passID=" + p.getId());
				popNextPassenger();
				p.setBoardTime(time);
				lift.addPassenger(p.getToFloor(), p);
			}
			checkPolite = false;
		}
		lift.updTimeInOffld();
		if(lift.offldDone()) {
			lift.setOffldDelay(0);
			return CLOSEDR;
		}
		return BOARD;
	}
	
	/**
	 * Curr state close dr.
	 *
	 * @param time the time
	 * @param lift the lift
	 * @return the int
	 */
	public int currStateCloseDr(int time, Elevator lift) {
		
		if(!lift.isDoorClosed()) {
			lift.closeDoor();
		}
		if(!checkPolite && lift.getDirection() == 1 && !floors[lift.getCurrFloor()].upQEmpty()) {
			if(!floors[lift.getCurrFloor()].getUpPassengers().isPolite()) {
				checkPolite = true;
				return OPENDR;
			}
		}
		if(!checkPolite && lift.getDirection() == -1 && !floors[lift.getCurrFloor()].downQEmpty()) {
			if(!floors[lift.getCurrFloor()].getDownPassengers().isPolite()) {
				checkPolite = true;
				return OPENDR;
			}
		} 
		if(lift.isDoorClosed()) {
			checkPolite = false;
			if(lift.getPassengers()==0 && !checkForPassengers()) {
				return STOP;
			}
			if(hasPassengersInDir()&&lift.getPassengers()==0 ) {
				return MV1FLR;
			}
			else if(checkForPassengers()&&!hasPassengersInDir()&&lift.getPassengers()==0 ) {
				if(lift.getDirection() == 1) {
					lift.setDirection(-1);
					return MV1FLR;
				}if(lift.getDirection() == -1) {
					lift.setDirection(1);
					return MV1FLR;
				}
			}
			return MV1FLR;
		}
		return CLOSEDR;
	}
	
	/**
	 * Curr state mv 1 flr.
	 *
	 * @param time the time
	 * @param lift the lift
	 * @return the int
	 */
	public int currStateMv1Flr(int time, Elevator lift) {
		lift.moveElevator();
		if(lift.getPrevFloor() == lift.getCurrFloor()) {
			return MV1FLR;
		}
		
		int dir = lift.getDirection();
		if(dir == -1) {
			//going down
			if(lift.getCapacity() > lift.getPassengers()) {
				if(!floors[lift.getCurrFloor()].downQEmpty()) {
					return OPENDR;
				}
			}
		}
		if(dir == 1) {
			//going up
			if(lift.getCapacity() > lift.getPassengers()) {
				if(!floors[lift.getCurrFloor()].upQEmpty()) {
					return OPENDR;
				}
			}
		}
		if(!lift.getPList(lift.getCurrFloor()).isEmpty()) {
			return OPENDR;
		}
		if(lift.getPassengers()==0&&!hasPassengersInDir()) {
			if(!floors[lift.getCurrFloor()].upQEmpty()) {
				lift.setDirection(1);
				return OPENDR;
			}
			if(!floors[lift.getCurrFloor()].downQEmpty()) {
				lift.setDirection(-1);
				return OPENDR;
			}
		}
		if(lift.getCurrFloor() == 0) {
			if(lift.getPassengers() == 0) {
				lift.setDirection(1);
			}
		}
		if(lift.getCurrFloor() == NUM_FLOORS-1) {
			if(lift.getPassengers() == 0) {
				lift.setDirection(-1);
			}
		}
		return MV1FLR;
	}
	
	/**
	 * Process passenger data.
	 */
	public void processPassengerData() {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(passDataFile)));
			out.println("ID,Number,From,To,WaitToBoard,TotalTime");
			for (Passengers p : arrivedAtDestination) {
				String str = p.getId()+","+p.getNumber()+","+p.getFromFloor()+","+p.getToFloor()+","+
				             (p.getBoardTime() - p.getTime())+","+(p.getTimeArrived() - p.getTime());
				out.println(str);
			}
			for (Passengers p : gaveUp) {
				String str = p.getId()+","+p.getNumber()+","+p.getFromFloor()+","+p.getToFloor()+","+
				             p.getGiveUpTime()+",-1";
				out.println(str);
			}
			out.flush();
			out.close();
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Checks for passengers in dir.
	 *
	 * @return true, if successful
	 */
	private boolean hasPassengersInDir() {
		if(lift.getDirection() == 1) {
			for(int i = lift.getCurrFloor()+1; i < NUM_FLOORS; i++) {
				if(!floors[i].downQEmpty() ||!floors[i].upQEmpty()) {
					return true;
				}
			}
		}
		if(lift.getDirection() == -1) {
			for(int i = lift.getCurrFloor()-1; i >= 0; i--) {
				if(!floors[i].downQEmpty() ||!floors[i].upQEmpty()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Gets the next passenger.
	 *
	 * @return the next passenger
	 */
	private Passengers getNextPassenger() {
		Passengers p = new Passengers(0,0,0,0,0,0, false);
		if(lift.getDirection() == 1) {
			if(floors[lift.getCurrFloor()].upQEmpty()) {
				return null;
			}
			p = floors[lift.getCurrFloor()].getUpPassengers();
		}
		else {
			if(floors[lift.getCurrFloor()].downQEmpty()) {
				return null;
			}
			p = floors[lift.getCurrFloor()].getDownPassengers();
		}
		return p;
	}
	
	/**
	 * Checks for next passenger.
	 *
	 * @return true, if successful
	 */
	private boolean hasNextPassenger() {
		if(lift.getDirection() == 1) {
			if(floors[lift.getCurrFloor()].upQEmpty()) {
				return false;
			}
		}
		if(lift.getDirection() == -1) {
			if(floors[lift.getCurrFloor()].downQEmpty()) {
				return false;
			}
		}
		return true;
		
	}
	
	/**
	 * Pop next passenger.
	 */
	public void popNextPassenger() {
		if(lift.getDirection() == 1) {
			if(floors[lift.getCurrFloor()].upQEmpty()) {
				return;
			}
			floors[lift.getCurrFloor()].popUpPassengers();
		}
		else {
			if(floors[lift.getCurrFloor()].downQEmpty()) {
				return;
			}
			floors[lift.getCurrFloor()].popDownPassengers();
		}
	}
	
	/**
	 * Adds the passenger.
	 *
	 * @param p the p
	 */
	public void addPassenger(Passengers p) {
		floors[p.getFromFloor()].addPassenger(p);
	}
	
	/**
	 * Check for passengers.
	 *
	 * @return true, if successful
	 */
	private boolean checkForPassengers() {
		int upQSize = 0;
		int firstUpQ = NUM_FLOORS;
		int downQSize = 0;
		int firstDownQ = -1;
		for(int i =0; i < NUM_FLOORS; i++) {
			upQSize+=floors[i].upQSize();
			firstUpQ = Math.min(firstUpQ, (floors[i].upQEmpty()) ? NUM_FLOORS : i);
			downQSize+=floors[i].downQSize();
			firstDownQ = Math.max(firstDownQ, (floors[i].downQEmpty()) ? -1 : i);
		}
		return (downQSize > 0|| upQSize >0);
	}
	
	/**
	 * Gets the passengers.
	 *
	 * @return the passengers
	 */
	private int getPassengers() {
		return lift.getPassengers();
	}
	
	/**
	 * Gets the passengers in building.
	 *
	 * @return the passengers in building
	 */
	public int getPassengersInBuilding() {
		int total = 0;
		for(int i = 0; i < NUM_FLOORS; i++) {
			total += floors[i].downQSize() + floors[i].upQSize();
		}
		return total;
	}

/**
 * Log.
 *
 * @param on the on
 */
public void log(Boolean on) {
		if(on){
			LOGGER.setLevel(Level.INFO);
		} else{
			LOGGER.setLevel(Level.OFF);
		}
	}
}