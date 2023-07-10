
public class VideoGame extends Media {


// By Aaron Zheng
	//type methods added by Anton
	//Constructor
	public VideoGame(String title, String location, String notes, String format) {
		super("Video Game",title, location, notes, format);
		
		
	}
	
	//to string method that returns the string of the item.
	public String toString() {
		return "Media Type: " + this.getType() + "\n"
				+"Game Title: " + this.getTitle() + "\n"
				+"Format: " + this.getFormat() + "\n"
				+"Location: " + this.getLocation() + "\n"
				+"Notes: " + this.getNotes();
	}

}
