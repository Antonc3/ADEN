
// TODO: Auto-generated Javadoc
/**
 * The Class Passengers.
 */
//By Anton
public class Passengers {
	//private static int ID;
	// These will come from the csv file, and should be initialized in the 
	/** The time. */
	// constructor.
	private int time;
	
	/** The number. */
	private int number;
	
	/** The from floor. */
	private int fromFloor;
	
	/** The to floor. */
	private int toFloor;
	
	/** The polite. */
	private boolean polite = true;
	
	/** The give up time. */
	private int giveUpTime;
	// this will be initialized in the constructor so that it is unique for each
	/** The id. */
	// set of Passengers
	private int id;	
	// These fields will be initialized during run time - boardTime is when the group
	// starts getting on the elevator, timeArrived is when the elevator starts offloading
	/** The board time. */
	// at the desired floor
	private int boardTime;
	
	/** The time arrived. */
	private int timeArrived;
	
	/**
	 * Instantiates a new passengers.
	 *
	 * @param ID the id
	 * @param time the time
	 * @param number the number
	 * @param fromFloor the from floor
	 * @param toFloor the to floor
	 * @param giveUpTime the give up time
	 * @param polite the polite
	 */
	public Passengers(int ID,int time, int number, int fromFloor, int toFloor,int giveUpTime,boolean polite) {
		id = ID;
		this.time = time;
		this.number = number;
		this.fromFloor = fromFloor;
		this.toFloor = toFloor;
		this.giveUpTime = giveUpTime;
		this.polite=polite;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return id + "";
	}
	
	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number the new number
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Gets the from floor.
	 *
	 * @return the from floor
	 */
	public int getFromFloor() {
		return fromFloor;
	}

	/**
	 * Sets the from floor.
	 *
	 * @param fromFloor the new from floor
	 */
	public void setFromFloor(int fromFloor) {
		this.fromFloor = fromFloor;
	}

	/**
	 * Gets the to floor.
	 *
	 * @return the to floor
	 */
	public int getToFloor() {
		return toFloor;
	}

	/**
	 * Sets the to floor.
	 *
	 * @param toFloor the new to floor
	 */
	public void setToFloor(int toFloor) {
		this.toFloor = toFloor;
	}

	/**
	 * Checks if is polite.
	 *
	 * @return true, if is polite
	 */
	public boolean isPolite() {
		return polite;
	}

	/**
	 * Sets the polite.
	 *
	 * @param polite the new polite
	 */
	public void setPolite(boolean polite) {
		this.polite = polite;
	}

	/**
	 * Gets the give up time.
	 *
	 * @return the give up time
	 */
	public int getGiveUpTime() {
		return giveUpTime;
	}

	/**
	 * Sets the give up time.
	 *
	 * @param giveUpTime the new give up time
	 */
	public void setGiveUpTime(int giveUpTime) {
		this.giveUpTime = giveUpTime;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the board time.
	 *
	 * @return the board time
	 */
	public int getBoardTime() {
		return boardTime;
	}

	/**
	 * Sets the board time.
	 *
	 * @param boardTime the new board time
	 */
	public void setBoardTime(int boardTime) {
		this.boardTime = boardTime;
	}

	/**
	 * Gets the time arrived.
	 *
	 * @return the time arrived
	 */
	public int getTimeArrived() {
		return timeArrived;
	}

	/**
	 * Sets the time arrived.
	 *
	 * @param timeArrived the new time arrived
	 */
	public void setTimeArrived(int timeArrived) {
		this.timeArrived = timeArrived;
	}
}
