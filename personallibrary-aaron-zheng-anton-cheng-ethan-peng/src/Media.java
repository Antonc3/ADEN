import java.util.Comparator;

// Ethan Peng
//type methods added by Anton
/**
 * The Class Media.
 */
public class Media implements Comparable<Media>, Comparator<Media> {
		
	/** The title. */
	private String title;
	
	/** The location. */
	private String location;
	
	/** The notes. */
	private String notes;
	
	/** The format. */
	private String format;
	
	private String type;
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTitle() {
		return title;
	}


	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}


	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}


	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}


	/**
	 * Sets the notes.
	 *
	 * @param notes the new notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}


	/**
	 * Gets the format.
	 *
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}


	/**
	 * Sets the format.
	 *
	 * @param format the new format
	 */
	public void setFormat(String format) {
		this.format = format;
	}


	/**
	 * Instantiates a new media.
	 *
	 * @param title the name
	 * @param location where it's stored
	 * @param notes notes about the media object
	 * @param format what type of object it is
	 */
	public Media(String type, String title, String location, String notes, String format) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.title = title;
		this.location = location;
		this.notes = notes;
		this.format = format;
	}

	
	// These may be helpful for use cases 3-5...
	/**
	 * Compare.
	 *
	 * @param o1 the first object
	 * @param o2 the second object
	 * @return if they are equalaf
	 */
	// Ignore for use cases 1,2
	@Override
	public int compare(Media o1, Media o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Compare to.
	 *
	 * @param o the object
	 * @return val of object
	 */
	@Override
	public int compareTo(Media o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
