// ListIterater can be used to look at the contents of the floor queues for 
// debug/display purposes...
import java.util.ListIterator;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class Floor.
 */

//By Anton
public class Floor {
	
	/** The Constant LOGGER. */
	private final static Logger LOGGER = Logger.getLogger(Floor.class.getName());
	
	/** The num floors. MUST be initialized in the constructor */
	private final int NUM_FLOORS;
	
	/** The up requests. */
	// add queues to track the up requests and down requests...
	private GenericQueue<Passengers> upRequests;
	
	/** The down requests. */
	private GenericQueue<Passengers> downRequests;
	/**
	 * Instantiates a new floor.
	 *
	 * @param qSize the q size
	 */
	public Floor(int qSize) {
		NUM_FLOORS = qSize;
		LOGGER.setLevel(Level.OFF);
		// add additional initialization here
		upRequests = new GenericQueue<Passengers>(qSize);
		downRequests = new GenericQueue<Passengers>(qSize);
		
	}
	
	/**
	 * Gets the up Q.
	 *
	 * @return the up Q
	 */
	public GenericQueue<Passengers> getUpQ(){
		return upRequests;
	}
	
	/**
	 * Gets the down Q.
	 *
	 * @return the down Q
	 */
	public GenericQueue<Passengers> getdownQ(){
		return downRequests;
	}
	
	/**
	 * Gets the up passengers.
	 *
	 * @return the up passengers
	 */
	public Passengers getUpPassengers(){
		return upRequests.peek();
	}
	
	/**
	 * Pop up passengers.
	 *
	 * @return the passengers
	 */
	public Passengers popUpPassengers(){
		return upRequests.remove();
	}
	
	/**
	 * Gets the down passengers.
	 *
	 * @return the down passengers
	 */
	public Passengers getDownPassengers(){
		return downRequests.peek();
	}
	
	/**
	 * Pop down passengers.
	 *
	 * @return the passengers
	 */
	public Passengers popDownPassengers(){
		return downRequests.remove();
	}
	
	/**
	 * Up Q empty.
	 *
	 * @return true, if successful
	 */
	public boolean upQEmpty() {
		return upRequests.isEmpty();
	}
	
	/**
	 * Down Q empty.
	 *
	 * @return true, if successful
	 */
	public boolean downQEmpty() {
		return downRequests.isEmpty();
	}

	/**
	 * Down Q size.
	 *
	 * @return the int
	 */
	public int downQSize() {
		return downRequests.getSize();
	}

	/**
	 * Up Q size.
	 *
	 * @return the int
	 */
	public int upQSize() {
		return upRequests.getSize();
	}
	
	/**
	 * Adds the passenger.
	 *
	 * @param p the p
	 */
	public void addPassenger(Passengers p) {
		if(p.getFromFloor() <p.getToFloor()) {
			upRequests.add(p);
		}
		else {
			downRequests.add(p);
		}
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

}
