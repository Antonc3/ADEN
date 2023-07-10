import java.util.ArrayList;
//Written by Aaron Zheng
public class PersonalLibraryController {
	PersonalLibraryModel model;
	String err;
	public PersonalLibraryController() {
		model = new PersonalLibraryModel();

	}

	public String addBook(String author, String title, String format, String location, String notes) {
		err = checkForBlankTitle(title);
		if(err.length() == 0) {
			model.insertBook(author, title, format, location, notes);
		}
		return err;

	}
	public String addSong(String author, String songTitle,String genre, String format, String location, String notes) {
		err = checkForBlankTitle(songTitle);
		if(err.length() == 0) {
			model.insertSong(author, songTitle, genre,format, location, notes);
		}
		return err;
	}
	public String addVideo(String vidTitle, String star, String format, String location, String notes) {
		err = checkForBlankTitle(vidTitle);
		if(err.length() == 0) {
			model.insertVideo(vidTitle, star, format, location, notes);
		}
		return err;
	}
	public String addVideoGame(String gtitle, String format, String location, String notes) {
		err = checkForBlankTitle(gtitle);
		if(err.length() == 0) {
		model.insertVideoGame(gtitle, format, location, notes);
		}
		return err;

	}
	
	public String[] getMediaDataStr() {
		return model.getMedia();
	}
	public String[] getMediaByType(String type) {
	
		return model.getMatchingMedia(type);
	}
	
	public String[] getMediaByTitle(String title) {
		if(title.length() == 0) {
			return new String[0];
		}
		return model.getMatchingTitle(title);
	}
	
	public String[] getMediaByTypeAndTitle(String type, String title) {
		if(type.length() == 0 || title.length() == 0) {
			return new String[0];
		}
		return model.getTypeAndTitle(type, title);
	}
	
	//Written by anton
	public void delete(int index) {
		model.delete(index);
	}





	//written by Aaron Zheng
	public String checkForBlankTitle(String title) {
		if(title.length() == 0) {
			return "The title cannot be blank! Media will not be entered into personal library.";

		}

		return "";

	}
	
	

}
