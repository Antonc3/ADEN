
public class Video extends Media {
	private String star;
	//by Aaron Zheng
	//type methods added by Anton

	//constructor 
	public Video(String title, String location, String notes, String format, String star) {
		// TODO Auto-generated constructor stub
		super("Video",title, location, notes, format);
		this.star = star;
	}

	//to string method that returns the string of the item.
	public String toString() {
		return  "Media Type: " + this.getType() + "\n"
				+"Star: " + this.getStar() + "\n"
				+"Video Title: " + this.getTitle() + "\n"
				+"Format: " + this.getFormat() + "\n"
				+"Location: " + this.getLocation() + "\n"
				+"Notes: " + this.getNotes();
	}

//getter for star
	public String getStar() {
		return star;
	}

//setter for star
	public void setStar(String star) {
		this.star = star;
	}
	

}
