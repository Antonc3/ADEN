import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonalLibraryModel.
 * @author Ethan Peng
 */
public class PersonalLibraryModel {
	
	/** The bk. */
	private ArrayList<Book> bk;
	
	/** The sg. */
	private ArrayList<Song> sg;
	
	/** The vg. */
	private ArrayList<VideoGame> vg;
	
	/** The vi. */
	private ArrayList<Video> vi;
	
	private String[] currSelected = new String[1000000];
	private int[] currInd = new int[1000000];
	
	/**
	 * Instantiates a new personal library model.
	 */
	public PersonalLibraryModel() {
		// TODO Auto-generated constructor stub
		bk = new ArrayList<>();
		sg = new ArrayList<>();
		vg = new ArrayList<>();
		vi = new ArrayList<>();
	}
	
	/**
	 * Insert book.
	 *
	 * @param author the author
	 * @param title the title
	 * @param format the format
	 * @param location the location
	 * @param notes the notes
	 */
	public void insertBook(String author, String title, String format, String location, String notes) {
		bk.add(new Book(author, title,format,location,notes));
	}
	
	/**
	 * Insert song.
	 *
	 * @param author the author
	 * @param songTitle the song title
	 * @param genre the genre
	 * @param format the format
	 * @param location the location
	 * @param notes the notes
	 */
	public void insertSong(String author, String songTitle, String genre,String format, String location, String notes) {
		sg.add(new Song(author, songTitle,genre,format, location,notes));
	}
	
	/**
	 * Insert video.
	 *
	 * @param vidTitle the vid title
	 * @param star the star
	 * @param format the format
	 * @param location the location
	 * @param notes the notes
	 */
	public void insertVideo(String vidTitle, String star, String format, String location, String notes) {
		vi.add(new Video(vidTitle,star, format, location,notes));
	}
	
	/**
	 * Insert video game.
	 *
	 * @param gtitle the gtitle
	 * @param format the format
	 * @param location the location
	 * @param notes the notes
	 */
	public void insertVideoGame(String gtitle, String format, String location, String notes) {
		vg.add(new VideoGame(gtitle,format, location,notes));
	}
	
	/**
	 * The Class ByTitle.
	 */
	private class ByTitle implements Comparator<Media> {
		
		/**
		 * Compare.
		 *
		 * @param o1 the o 1
		 * @param o2 the o 2
		 * @return the int
		 */
		public int compare(Media o1, Media o2) {
			return o1.getTitle().compareTo(o2.getTitle());
		}
	}
	
	/**
	 * Gets the media.
	 *
	 * @return the media
	 */
	
	//Anton fixed deleting problems
	public String[] getMedia() {
		Collections.sort(bk,new ByTitle());
		Collections.sort(sg,new ByTitle());
		Collections.sort(vg,new ByTitle());
		Collections.sort(vi,new ByTitle());
		ArrayList<Media> f  = new ArrayList<>();
		int ind = 0;
		for (int i = 0; i < bk.size();i++) {
			f.add(bk.get(i));
			//Anton added this part for each
			currSelected[ind] = "Book";
			currInd[ind] = i;
			ind++;
		}
		for (int i = 0; i < sg.size();i++) {
			f.add(sg.get(i));
			currSelected[ind] = "Song";
			currInd[ind] = i;
			ind++;
		}
		for (int i = 0; i < vi.size();i++) {
			f.add(vi.get(i));
			currSelected[ind] = "Video";
			currInd[ind] = i;
			ind++;
		}
		for (int i = 0; i < vg.size();i++) {
			f.add(vg.get(i));
			currSelected[ind] = "Video Game";
			currInd[ind] = i;
			ind++;
		}

		//written by Anton
		//returns string array
		ArrayList<Media> al = f;
		String[] s = new String[al.size()];
		int i = 0;
		for(Media m : al) {
			s[i] = m.toString();
			i++;
		}
		return s;
	}
	
	public String[] getMatchingMedia(String type) {
		String[] s = new String[1];
		if (type.equals("Book")) {
			s = new String[bk.size()];
			int i = 0;
			for(Media m : bk) {
				s[i] = m.toString();
				currSelected[i] = "Book";
				currInd[i] = i;
				i++;
			}
		} else if (type.equals("Song")) {
			s = new String[sg.size()];
			int i = 0;
			for(Media m : sg) {
				s[i] = m.toString();
				currSelected[i] = "Song";
				currInd[i] = i;
				i++;
			}
		} else if (type.equals("Video")) {
			s = new String[vi.size()];
			int i = 0;
			for(Media m : vi) {
				s[i] = m.toString();
				currSelected[i] = "Video";
				currInd[i] = i;
				i++;
			}
		} else {
			s = new String[vg.size()];
			int i = 0;
			for(Media m : vg) {
				s[i] = m.toString();
				currSelected[i] = "Video Game";
				currInd[i] = i;
				i++;
			}
		}
		return s;
	}
	
	public String[] getMatchingTitle(String title) {
		ArrayList<Media> temp = new ArrayList<>();
		int ct = 0;
		for (int i = 0; i < bk.size(); i++) {
			if (bk.get(i).getTitle().equals(title)) {
				temp.add(bk.get(i));
				currSelected[ct] = "Book";
				currInd[ct] = i;
				ct++;
			}
		}
		for (int i = 0; i < sg.size(); i++) {
			if (sg.get(i).getTitle().equals(title)) {
				temp.add(sg.get(i));
				currSelected[ct] = "Song";
				currInd[ct] = i;
				ct++;
			}
		}
		for (int i = 0; i < vi.size(); i++) {
			if (vi.get(i).getTitle().equals(title)) {
				temp.add(vi.get(i));
				currSelected[ct] = "Video";
				currInd[ct] = i;
				ct++;
			}
		}
		for (int i = 0; i < vg.size(); i++) {
			if (vg.get(i).getTitle().equals(title)) {
				temp.add(vg.get(i));
				currSelected[ct] = "Video Game";
				currInd[ct] = i;
				ct++;
			}
		}
		String[] s = new String[temp.size()];
		ct = 0;
		for (Media m: temp) {
			s[ct] = m.toString();
			ct++;
		}
		return s;
	}
	
	public String[] getTypeAndTitle(String type, String title) {
		String[] s = new String[1];
		ArrayList<String> temp = new ArrayList<>();
		if (type.equals("Book")) {
			//change size back
			int i = 0;
			for(Media m : bk) {
				if (bk.get(i).getTitle().equals(title)) {
					temp.add(m.toString());
					currSelected[i] = "Book";
					currInd[i] = i;
					i++;
				}	
			}
			Object[] arr = temp.toArray();
			s = Arrays.copyOf(arr, 
                    arr.length, 
                    String[].class);
		} else if (type.equals("Song")) {
			int i = 0;
			for(Media m : sg) {
				if (sg.get(i).getTitle().equals(title)) {
					temp.add(m.toString());
					currSelected[i] = "Song";
					currInd[i] = i;
					i++;
				}	
			}
			Object[] arr = temp.toArray();
			s = Arrays.copyOf(arr, 
                    arr.length, 
                    String[].class);
		} else if (type.equals("Video")) {
			int i = 0;
			for(Media m : vi) {
				if (vi.get(i).getTitle().equals(title)) {
					temp.add(m.toString());
					currSelected[i] = "Video";
					currInd[i] = i;
					i++;
				}	
			}
			Object[] arr = temp.toArray();
			s = Arrays.copyOf(arr, 
                    arr.length, 
                    String[].class);
		} else {
			int i = 0;
			for(Media m : vg) {
				if (vg.get(i).getTitle().equals(title)) {
					temp.add(m.toString());
					currSelected[i] = "Video Game";
					currInd[i] = i;
					i++;
				}	
			}
			Object[] arr = temp.toArray();
			s = Arrays.copyOf(arr, 
                    arr.length, 
                    String[].class);
		}
		return s;
	}
	
	public void delete(int index) {
		if (currSelected[index].equals("Book")) {
			bk.remove(currInd[index]);
		} else if (currSelected[index].equals("Song")) {
			sg.remove(currInd[index]);
		} else if (currSelected[index].equals("Video")) {
			vi.remove(currInd[index]);
		} else {
			vg.remove(currInd[index]);
		}
		
	}
	
}
