
// TODO: Auto-generated Javadoc
/**
 * The Class Book.
 */
public class Book extends Media {
	// By Anton Cheng
	/** The author. */
	private String author;
	
	
	/**
	 * Instantiates a new book.
	 *
	 * @param title the title
	 */
	public Book(String author, String title, String format, String location, String notes) {
		// TODO Auto-generated constructor stub
		super("Book",title,location,notes,format);
		this.author = author;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String str = "Media Type: " + this.getType() + "\n"
					+"Author: " + this.getAuthor() + "\n"
					+"Title: " + this.getTitle() + "\n"
					+"Format: " + this.getFormat() + "\n"
					+"Location: " + this.getLocation() + "\n"
					+"Notes: " + this.getNotes();
		return str;
	}

	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}


	/**
	 * Sets the author.
	 *
	 * @param author the new author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
