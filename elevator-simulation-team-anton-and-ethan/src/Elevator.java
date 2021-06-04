import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class Elevator.
 */

/**
 * @author Ethan And Anton
 * This class will represent an elevator, and will contain
 * configuration information (capacity, speed, etc) as well
 * as state information - such as stopped, direction, and count
 * of passengers targetting each floor...
 */
public class Elevator {
	
	/** The Constant LOGGER. */
	private final static Logger LOGGER = Logger.getLogger(Elevator.class.getName());
	
	/** The Constant UNDEF. */
	// Elevator State Variables
	private final static int UNDEF = -1;
	
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

	/** The capacity. */
	// Configuration parameters
	private int capacity = 15;
	
	/** The ticks per floor. */
	private int ticksPerFloor = 5;
	
	/** The ticks door open close. */
	private int ticksDoorOpenClose = 2;  
	
	/** The pass per tick. */
	private int passPerTick = 3;
	
	
	//State Variables
	/** The curr state. */
	// track the elevator state
	private int currState;
	
	/** The prev state. */
	private int prevState;
	
	/** The prev floor. */
	// track what floor you are on, and where you came from
	private int prevFloor;
	
	/** The curr floor. */
	private int currFloor;
	
	/** The direction. */
	// direction 1 = up, -1 = down
	private int direction;
	// timeInState is reset on state entry, used to determine if state is finished
	/** The time in state. */
	// or if floor has changed...
	private int timeInState;
	// used to track where the the door is in OPENDR and CLOSEDR states
	/** The door state. */
	//2 is fully closed, 0 is fully open
	private int doorState;
	
	/** The passengers. */
	// number of passengers on the elevator
	private int passengers;
	
	/** The num groups. */
	private int numGroups;
	// when exiting the stop state, the floor to moveTo and the direction to go in once you
	/** The move to floor. */
	// get there...
	private int moveToFloor;
	
	/** The move to floor dir. */
	private int moveToFloorDir;

	/** The num floors. */
	private final int NUM_FLOORS;
	
	/** The p list. */
	private ArrayList<Passengers> pList[];
	
	/** The offld delay. */
	private int offldDelay = 0;
	
	/** The board delay. */
	private int boardDelay = 0;
	
	/**
	 * Instantiates a new elevator.
	 *
	 * @param numFloors the num floors
	 */
	public Elevator(int numFloors) {
		NUM_FLOORS = numFloors;
		pList = new ArrayList[NUM_FLOORS];
		for(int i =0; i < NUM_FLOORS; i++) {
			pList[i] = new ArrayList<Passengers>();
		}
		this.prevState = STOP;
		this.currState = STOP;
		//starts off open
		timeInState=0;
		direction = 1;
//		doorState = 2;
		prevFloor = 0;
		currFloor = 0;
		passengers = 0;
		LOGGER.setLevel(Level.OFF);
	}

	/**
	 * Enable logging.
	 */
	public void enableLogging() {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * Sets the logger FH.
	 *
	 * @param fh the new logger FH
	 */
	public void setLoggerFH(FileHandler fh) {
		LOGGER.addHandler(fh);
	}
	
	/**
	 * Update curr state.
	 *
	 * @param state the state
	 */
	public void updateCurrState(int state) {
		prevState = currState;
		currState = state;
		if(prevState != currState) {
			timeInState = 0;
		}
	}
	
	/**
	 * Gets the p list.
	 *
	 * @param floor the floor
	 * @return the p list
	 */
	public ArrayList<Passengers> getPList(int floor) {
		return pList[floor];
	}
	
	/**
	 * Sets the offld delay.
	 *
	 * @param i the new offld delay
	 */
	public void setOffldDelay(int i) {
		this.offldDelay = i;
	}
	
	/**
	 * Gets the offld delay.
	 *
	 * @return the offld delay
	 */
	public int getOffldDelay() {
		return this.offldDelay;
	}
	
	/**
	 * Upd time in offld.
	 */
	public void updTimeInOffld() {
		timeInState+=passPerTick;
	}
	
	/**
	 * Offld done.
	 *
	 * @return true, if successful
	 */
	public boolean offldDone() {
		return this.timeInState >= this.offldDelay;
	}
	
	/**
	 * Adds the passenger.
	 *
	 * @param floor the floor
	 * @param p the p
	 */
	public void addPassenger(int floor, Passengers p) {
		pList[floor].add(p);
		passengers+=p.getNumber();
		numGroups++;
	}
	
	/**
	 * Checks for passenger.
	 *
	 * @param floor the floor
	 * @return true, if successful
	 */
	public boolean hasPassenger(int floor) {
		return !pList[floor].isEmpty();
	}
	
	/**
	 * Gets the passenger.
	 *
	 * @param floor the floor
	 * @return the passenger
	 */
	public Passengers getPassenger(int floor) {
		Passengers p = pList[floor].get(0);
		return p;
	}
	
	/**
	 * Removes the passenger.
	 *
	 * @param floor the floor
	 * @return the passengers
	 */
	public Passengers removePassenger(int floor) {
		Passengers p = pList[floor].remove(0);
		passengers-=p.getNumber();
		numGroups--;
		return p;
	}
	
	/**
	 * Move elevator.
	 */
	public void moveElevator() {
		if(isDoorClosed()) {
			timeInState++;
			prevFloor=currFloor;
			if(timeInState%ticksPerFloor==0) {
				currFloor+=direction;
			}
		}
	}
	
	/**
	 * Close door.
	 */
	public void closeDoor() {
		doorState++;
		currState=CLOSEDR;
	}
	
	/**
	 * Open door.
	 */
	public void openDoor() {
		doorState--;
		currState=OPENDR;
		prevFloor = currFloor;
	}
	
	/**
	 * Checks if is door closed.
	 *
	 * @return true, if is door closed
	 */
	public boolean isDoorClosed() {
		if(doorState==ticksDoorOpenClose) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if is door open.
	 *
	 * @return true, if is door open
	 */
	public boolean isDoorOpen() {
		if(doorState==0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the capacity.
	 *
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Sets the capacity.
	 *
	 * @param capacity the new capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Gets the ticks per floor.
	 *
	 * @return the ticks per floor
	 */
	public int getTicksPerFloor() {
		return ticksPerFloor;
	}

	/**
	 * Sets the ticks per floor.
	 *
	 * @param ticksPerFloor the new ticks per floor
	 */
	public void setTicksPerFloor(int ticksPerFloor) {
		this.ticksPerFloor = ticksPerFloor;
	}

	/**
	 * Gets the ticks door open close.
	 *
	 * @return the ticks door open close
	 */
	public int getTicksDoorOpenClose() {
		return ticksDoorOpenClose;
	}

	/**
	 * Sets the ticks door open close.
	 *
	 * @param ticksDoorOpenClose the new ticks door open close
	 */
	public void setTicksDoorOpenClose(int ticksDoorOpenClose) {
		this.ticksDoorOpenClose = ticksDoorOpenClose;
		this.doorState = ticksDoorOpenClose;
	}

	/**
	 * Gets the pass per tick.
	 *
	 * @return the pass per tick
	 */
	public int getPassPerTick() {
		return passPerTick;
	}

	/**
	 * Sets the pass per tick.
	 *
	 * @param passPerTick the new pass per tick
	 */
	public void setPassPerTick(int passPerTick) {
		this.passPerTick = passPerTick;
	}

	/**
	 * Gets the curr state.
	 *
	 * @return the curr state
	 */
	public int getCurrState() {
		return currState;
	}

	/**
	 * Sets the curr state.
	 *
	 * @param currState the new curr state
	 */
	public void setCurrState(int currState) {
		this.currState = currState;
	}

	/**
	 * Gets the prev state.
	 *
	 * @return the prev state
	 */
	public int getPrevState() {
		return prevState;
	}

	/**
	 * Sets the prev state.
	 *
	 * @param prevState the new prev state
	 */
	public void setPrevState(int prevState) {
		this.prevState = prevState;
	}

	/**
	 * Gets the prev floor.
	 *
	 * @return the prev floor
	 */
	public int getPrevFloor() {
		return prevFloor;
	}

	/**
	 * Sets the prev floor.
	 *
	 * @param prevFloor the new prev floor
	 */
	public void setPrevFloor(int prevFloor) {
		this.prevFloor = prevFloor;
	}

	/**
	 * Gets the curr floor.
	 *
	 * @return the curr floor
	 */
	public int getCurrFloor() {
		return currFloor;
	}

	/**
	 * Sets the curr floor.
	 *
	 * @param currFloor the new curr floor
	 */
	public void setCurrFloor(int currFloor) {
		this.currFloor = currFloor;
	}

	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * Sets the direction.
	 *
	 * @param direction the new direction
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * Gets the time in state.
	 *
	 * @return the time in state
	 */
	public int getTimeInState() {
		return timeInState;
	}

	/**
	 * Sets the time in state.
	 *
	 * @param timeInState the new time in state
	 */
	public void setTimeInState(int timeInState) {
		this.timeInState = timeInState;
	}

	/**
	 * Gets the door state.
	 *
	 * @return the door state
	 */
	public int getDoorState() {
		return doorState;
	}

	/**
	 * Sets the door state.
	 *
	 * @param doorState the new door state
	 */
	public void setDoorState(int doorState) {
		this.doorState = doorState;
	}

	/**
	 * Gets the passengers.
	 *
	 * @return the passengers
	 */
	public int getPassengers() {
		return passengers;
	}

	/**
	 * Sets the passengers.
	 *
	 * @param passengers the new passengers
	 */
	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	/**
	 * Gets the move to floor.
	 *
	 * @return the move to floor
	 */
	public int getMoveToFloor() {
		return moveToFloor;
	}

	/**
	 * Sets the move to floor.
	 *
	 * @param moveToFloor the new move to floor
	 */
	public void setMoveToFloor(int moveToFloor) {
		this.moveToFloor = moveToFloor;
	}

	/**
	 * Gets the move to floor dir.
	 *
	 * @return the move to floor dir
	 */
	public int getMoveToFloorDir() {
		return moveToFloorDir;
	}

	/**
	 * Sets the move to floor dir.
	 *
	 * @param moveToFloorDir the new move to floor dir
	 */
	public void setMoveToFloorDir(int moveToFloorDir) {
		this.moveToFloorDir = moveToFloorDir;
	}
	
}
