
// TODO: Auto-generated Javadoc
/**
 * The Class Song.
 */
public class Song extends Media {
	// By Anton Cheng
	/** The artist. */
	private String artist;
	
	/** The genre. */
	private String genre;
	
	/**
	 * Instantiates a new song.
	 *
	 * @param title the title
	 */
	public Song(String artist, String title,String genre, String format, String location, String notes) {
		super("Song", title,location,notes,format);
		this.artist = artist;
		this.genre = genre;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String str = "Media Type: " + this.getType() + "\n"
					+"Artist: " + this.getArtist() + "\n"
					+"Song Title: " + this.getTitle() + "\n"
					+"Genre: " + this.getGenre() + "\n"
					+"Format: " + this.getFormat() + "\n"
					+"Location: " + this.getLocation() + "\n"
					+"Notes: " + this.getNotes();
		return str;
	}
	
	/**
	 * Gets the artist.
	 *
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}
	
	/**
	 * Sets the artist.
	 *
	 * @param artist the new artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	/**
	 * Gets the genre.
	 *
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}
	
	/**
	 * Sets the genre.
	 *
	 * @param genre the new genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	

}
