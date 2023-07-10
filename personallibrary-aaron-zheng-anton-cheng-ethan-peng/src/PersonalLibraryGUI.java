import javafx.application.Application;
import javafx.stage.Stage;

//public class PersonalLibraryGUI extends Application {
//	private static PersonalLibararyController plc = 
//	public PersonalLibraryGUI() {
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//
//
//	}
//
//	public static void main(String[] args) {
//		Application.launch(args);
//
//	}
//
//}
///**
// * 
// */


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Anton Cheng
 *
 */
public class PersonalLibraryGUI extends Application {
    private BorderPane main = new BorderPane();	
    private BorderPane mediaData = new BorderPane();
    private BorderPane bookPane = new BorderPane();
    private BorderPane songPane = new BorderPane();
    private BorderPane videoPane = new BorderPane();
    private BorderPane videoGamePane = new BorderPane();

    private static PersonalLibraryController controller = new PersonalLibraryController();


    private TextField bookAuthor;
	private TextField bookTitle;
	private TextField bookFormat;
	private TextField bookLocation;
	private TextField bookNotes;

	private TextField songArtist;
	private TextField songTitle;
	private TextField songGenre;
	private TextField songFormat;
	private TextField songLocation;
	private TextField songNotes;
	
	private TextField videoTitle;
	private TextField videoStar;
	private TextField videoFormat;
	private TextField videoLocation;
	private TextField videoNotes;
	
	private TextField vgTitle;
	private TextField vgFormat;
	private TextField vgLocation;
	private TextField vgNotes;
	
	private RadioButton noneRB;
	private RadioButton bookRB;
	private RadioButton songRB;
	private RadioButton videoRB;
	private RadioButton videoGameRB;
	private ToggleGroup tg = new ToggleGroup();

	private TextField titleSearch;
	private String lastSearch = "";
	private ListView<String> lv;

	
    @Override
    public void start(Stage primaryStage) {
    	
    	Scene scene = new Scene(main,400,400);
    	Scene bookScene = new Scene(bookPane,400,400);
    	Scene songScene = new Scene(songPane,400,400);
    	Scene videoScene = new Scene(videoPane,400,400);
    	Scene videoGameScene = new Scene(videoGamePane,400,400);
    	Scene viewMediaScene = new Scene(mediaData,400,400);
    	main.setTop(new Label("Main Screen"));
  
    	Button toBook = new Button("Add Book");
    	toBook.setOnAction(e -> primaryStage.setScene(bookScene));
    	
    	Button toSong = new Button("Add Song");
    	toSong.setOnAction(e -> primaryStage.setScene(songScene));

    	Button toVideo = new Button("Add Video");
    	toVideo.setOnAction(e -> primaryStage.setScene(videoScene));
    	
    	Button toVideoGame = new Button("Add Video Game");
    	toVideoGame.setOnAction(e -> primaryStage.setScene(videoGameScene));

    	Button viewMedia = new Button("View Media");
    	viewMedia.setOnAction(e -> {
    		viewMediaDB();
    		primaryStage.setScene(viewMediaScene);
    	});
    	addToBookScene(primaryStage,scene);
    	addToSongScene(primaryStage,scene);
    	addToVideoScene(primaryStage,scene);
    	addToVideoGameScene(primaryStage,scene);
        addToMedViewScene(primaryStage,scene);

    	GridPane gp = new GridPane();
    	gp.add(toBook,0,0);
    	gp.add(toSong,0,1);
    	gp.add(toVideo,0,2);
    	gp.add(toVideoGame,0,3);
    	main.setCenter(gp);
    	HBox btnBox = new HBox(15);
    	btnBox.getChildren().addAll(viewMedia);
    	main.setBottom(btnBox);

        
        
    	primaryStage.setTitle("Media");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Adds the to med view scene.
     *
     * @param stage the stage
     * @param main the main
     */
    private void addToMedViewScene(Stage stage, Scene main) {
    	GridPane gp = new GridPane();
    	mediaData.setTop(new Label("Media Data View"));
       
        
        noneRB = new RadioButton("None");
        bookRB = new RadioButton("Book");
        songRB = new RadioButton("Song");
        videoRB = new RadioButton("Video");
        videoGameRB = new RadioButton("Video Game");
        
        noneRB.setToggleGroup(tg);
        noneRB.setSelected(true);
        bookRB.setToggleGroup(tg);
        songRB.setToggleGroup(tg);
        videoRB.setToggleGroup(tg);
        videoGameRB.setToggleGroup(tg);
        Button back = new Button("Back");
        back.setOnAction(e -> {
        	noneRB.setSelected(true);
        	titleSearch.clear();
        	stage.setScene(main);
        });
        gp.add(back, 0,0);
        
        Label titleSearchLabel = new Label("Title: ");
        
        titleSearch =  new TextField();
        titleSearch.setOnAction(e ->{
        	lastSearch = titleSearch.getText();
        	if(noneRB.isSelected()) {
        		viewMediaDBByTitle(lastSearch);
        	}
    		if(bookRB.isSelected()) {
    			viewMediaDBByTypeAndTitle("Book",lastSearch);
    		}
    		if(songRB.isSelected()) {
    			viewMediaDBByTypeAndTitle("Song",lastSearch);
    		}
    		if(videoRB.isSelected()) {
    			viewMediaDBByTypeAndTitle("Video",lastSearch);
    		}
    		if(videoGameRB.isSelected()) {
    			viewMediaDBByTypeAndTitle("Video Game",lastSearch);
    		}

        });
        Button clearButton = new Button("Clear All Paramaters!");
        clearButton.setOnAction(e ->{
        	viewMediaDB();
        	titleSearch.clear();
        	noneRB.setSelected(true);
        });
        gp.add(clearButton, 3, 1);
        gp.add(titleSearchLabel, 2, 0);
        gp.add(titleSearch,3, 0);
        
        noneRB.setOnAction(e->{
        	if(noneRB.isSelected()) {
        		if(lastSearch.length() != 0) {
        			viewMediaDBByTitle(lastSearch);
        		}
        		else {
        			viewMediaDB();
        		}
        	}
        });
        bookRB.setOnAction(e->{
        	if(bookRB.isSelected()) {
        		if(lastSearch.length() != 0) {
        			viewMediaDBByTypeAndTitle("Book",lastSearch);
        		}
        		else {
        			viewMediaDBByType("Book");
        		}
        	}
        });
        songRB.setOnAction(e->{
        	if(songRB.isSelected()) {
        		if(lastSearch.length() != 0) {
        			viewMediaDBByTypeAndTitle("Song",lastSearch);
        		}
        		else { 
        			viewMediaDBByType("Song");
        		}
        	}
        });
        videoRB.setOnAction(e->{
        	if(videoRB.isSelected()) {
        		if(lastSearch.length() != 0) {
        			viewMediaDBByTypeAndTitle("Video",lastSearch);
        		}
        		else {
        			viewMediaDBByType("Video");
        		}
        	}
        });
        videoGameRB.setOnAction(e->{
        	if(videoGameRB.isSelected()) {
        		if(lastSearch.length() != 0) {
        			viewMediaDBByTypeAndTitle("Video Game",lastSearch);
        		}
        		else {
        			viewMediaDBByType("Video Game");
        		}
        	}
        });
        
        Button delSelected = new Button("Delete Selected!");
        delSelected.setOnAction(e ->{
        	if(getListViewInd() != -1) {
        		controller.delete(getListViewInd());
        		if(lastSearch.length() !=0) {
        			if(noneRB.isSelected()) {
        				viewMediaDBByTitle(lastSearch);
        			}
        			if(bookRB.isSelected()) {
            			viewMediaDBByTypeAndTitle("Book",lastSearch);
            		}
            		if(songRB.isSelected()) {
            			viewMediaDBByTypeAndTitle("Song",lastSearch);
            		}
            		if(videoRB.isSelected()) {
            			viewMediaDBByTypeAndTitle("Video",lastSearch);
            		}
            		if(videoGameRB.isSelected()) {
            			viewMediaDBByTypeAndTitle("Video Game",lastSearch);
            		}
        		}
        		else {
	        		if(noneRB.isSelected()) {
	        			viewMediaDB();
	        		}
	        		else if(bookRB.isSelected()) {
	            		viewMediaDBByType("Book");
	            	}
	        		else if(songRB.isSelected()) {
	            		viewMediaDBByType("Song");
	            	}
	        		else if(videoRB.isSelected()) {
	            		viewMediaDBByType("Video");
	            	}
	        		else if(videoGameRB.isSelected()) {
	            		viewMediaDBByType("Video Game");
	            	}
        		}
        	}
        	else {
        		Alert a = new Alert(AlertType.ERROR);
        		a.setContentText("You Must Have Something Selected To Delete");
        		a.show();
        	}
        });
        gp.add(delSelected, 0, 3);
        gp.add(noneRB, 1, 0);
        gp.add(bookRB,1,1);
        gp.add(songRB, 1,2);
        gp.add(videoRB, 1,3);
        gp.add(videoGameRB, 1,4);
        
        mediaData.setBottom(gp);
    }
    
    private void addToBookScene(Stage stage,Scene main) {
    	GridPane gp = new GridPane();
    	Label lblBook = new Label("Adding A Book");
    	gp.add(lblBook, 0, 0);
    	Label lblTitle = new Label("Title: ");
    	Label lblAuthor = new Label("Author: ");
    	Label lblFormat = new Label("Format: ");
    	Label lblLocation = new Label("Location: ");
    	Label lblNotes = new Label("Notes: ");
	    bookAuthor = new TextField();
	    bookTitle = new TextField();
    	bookFormat = new TextField();
    	bookLocation = new TextField();
    	bookNotes = new TextField();

    	gp.add(lblAuthor,0,1);
    	gp.add(lblTitle,0, 2);
        gp.add(lblFormat,0, 3);
        gp.add(lblLocation,0,4);
        gp.add(lblNotes,0,5);

    	gp.add(bookAuthor,1,1);
    	gp.add(bookTitle,1,2);
    	gp.add(bookFormat,1,3);
    	gp.add(bookLocation,1,4);
    	gp.add(bookNotes,1,5);

        Button addBook = new Button("Add Book");
        addBook.setOnAction(e -> {
        	String s = controller.addBook(bookAuthor.getText(),
										bookTitle.getText(),
										bookFormat.getText(),
										bookLocation.getText(),
										bookNotes.getText());
        	if(s.length() != 0){
        		Alert a = new Alert(Alert.AlertType.ERROR);
        		a.setContentText(s);
        		a.show();
        	}
        	clearTextFields();

        });
        
        Button back = new Button("Back");
        back.setOnAction(e -> stage.setScene(main));
        
        HBox btns = new HBox(15);
        
        btns.getChildren().addAll(addBook,back);
        
        bookPane.setCenter(gp);
        bookPane.setBottom(btns);
    }
    private void addToSongScene(Stage stage,Scene main) {
    	GridPane gp = new GridPane();
    	Label lblSong = new Label("Adding A Song");
    	gp.add(lblSong, 0, 0);
    	Label lblArtist = new Label("Artist: ");
    	Label lblTitle = new Label("Title: ");
    	Label lblGenre = new Label("Genre: ");
    	Label lblFormat = new Label("Forrmat: ");
    	Label lblLocation = new Label("Location: ");
    	Label lblNotes = new Label("Notes: ");
	    songArtist= new TextField();
	    songTitle = new TextField();
    	songGenre = new TextField();
	    songFormat = new TextField();
    	songLocation = new TextField();
    	songNotes = new TextField();

    	gp.add(lblArtist,0,1);
    	gp.add(lblTitle,0, 2);
        gp.add(lblGenre,0, 3);
        gp.add(lblFormat,0, 4);
        gp.add(lblLocation,0,5);
        gp.add(lblNotes,0,6);

    	gp.add(songArtist,1,1);
    	gp.add(songTitle,1,2);
    	gp.add(songGenre,1,3);
    	gp.add(songFormat,1,4);
    	gp.add(songLocation,1,5);
    	gp.add(songNotes,1,6);

        Button addSong = new Button("Add Song");
        addSong.setOnAction(e -> {
        	String s = controller.addSong(songArtist.getText(),
										songTitle.getText(),
										songGenre.getText(),
										songFormat.getText(),
										songLocation.getText(),
										songNotes.getText());
        	if(s.length() != 0){
        		Alert a = new Alert(Alert.AlertType.ERROR);
        		a.setContentText(s);
        		a.show();
        	}
        	clearTextFields();

        });
        
        Button back = new Button("Back");
        back.setOnAction(e -> stage.setScene(main));
        
        HBox btns = new HBox(15);
        
        btns.getChildren().addAll(addSong,back);
        
        songPane.setCenter(gp);
        songPane.setBottom(btns);
    }
    private void addToVideoScene(Stage stage,Scene main) {
    	GridPane gp = new GridPane();
    	Label lblVideo = new Label("Adding A Video");
    	gp.add(lblVideo, 0, 0);
    	Label lblStar = new Label("Star: ");
    	Label lblTitle = new Label("Title: ");
    	Label lblFormat = new Label("Forrmat: ");
    	Label lblLocation = new Label("Location: ");
    	Label lblNotes = new Label("Notes: ");
    	videoStar= new TextField();
    	videoTitle = new TextField();
    	videoFormat = new TextField();
    	videoLocation = new TextField();
    	videoNotes = new TextField();

    	gp.add(lblTitle,0,1);
    	gp.add(lblStar,0, 2);
        gp.add(lblFormat,0,3);
        gp.add(lblLocation,0,4);
        gp.add(lblNotes,0,5);

    	gp.add(videoTitle,1,1);
    	gp.add(videoStar,1,2);
    	gp.add(videoFormat,1,3);
    	gp.add(videoLocation,1,4);
    	gp.add(videoNotes,1,5);

        Button addVideo = new Button("Add Video");
        addVideo.setOnAction(e -> {
        	String s = controller.addVideo(videoTitle.getText(),
        								videoStar.getText(),
										videoFormat.getText(),
										videoLocation.getText(),
										videoNotes.getText());
        	if(s.length() != 0){
        		Alert a = new Alert(Alert.AlertType.ERROR);
        		a.setContentText(s);
        		a.show();

        	}
        	clearTextFields();

        });
        
        Button back = new Button("Back");
        back.setOnAction(e -> stage.setScene(main));
        
        HBox btns = new HBox(15);
        
        btns.getChildren().addAll(addVideo,back);
        
        videoPane.setCenter(gp);
        videoPane.setBottom(btns);
    }
    private void addToVideoGameScene(Stage stage,Scene main) {
    	GridPane gp = new GridPane();
    	Label lblVideoGame = new Label("Adding A Video Game");
    	gp.add(lblVideoGame, 0, 0);
    	Label lblTitle = new Label("Title: ");
    	Label lblFormat = new Label("Forrmat: ");
    	Label lblLocation = new Label("Location: ");
    	Label lblNotes = new Label("Notes: ");
    	vgTitle = new TextField();
    	vgFormat = new TextField();
    	vgLocation = new TextField();
    	vgNotes = new TextField();

    	gp.add(lblTitle,0,1);
        gp.add(lblFormat,0,2);
        gp.add(lblLocation,0,3);
        gp.add(lblNotes,0,4);

    	gp.add(vgTitle,1,1);
    	gp.add(vgFormat,1,2);
    	gp.add(vgLocation,1,3);
    	gp.add(vgNotes,1,4);

        Button addVideoGame = new Button("Add Video Game");
        addVideoGame.setOnAction(e -> {
        	String s = controller.addVideoGame(vgTitle.getText(),
										vgFormat.getText(),
										vgLocation.getText(),
										vgNotes.getText());
        	if(s.length() != 0){
        		Alert a = new Alert(Alert.AlertType.ERROR);
        		a.setContentText(s);
        		a.show();
        	}
        	clearTextFields();
        });
        
        Button back = new Button("Back");
        back.setOnAction(e -> stage.setScene(main));
        
        HBox btns = new HBox(15);
        
        btns.getChildren().addAll(addVideoGame,back);
        
        videoGamePane.setCenter(gp);
        videoGamePane.setBottom(btns);
    }

   private int getListViewInd() {
    	return lv.getSelectionModel().getSelectedIndex();
    }
    
    private void viewMediaDB() {
    	lastSearch = "";
    	String[] mediaDataStr = controller.getMediaDataStr();
    	if(mediaDataStr.length == 0) {
    		mediaDataStr = new String[1];
    		mediaDataStr[0] = "No Media Was Found";
    	}
    	lv = new ListView<>(FXCollections.observableArrayList(mediaDataStr));
    	lv.setPrefWidth(400);
    	mediaData.setCenter(new ScrollPane(lv));
    }
    
    private void viewMediaDBByTitle(String title) {
    	String[] mediaDataStr = controller.getMediaByTitle(title);
    	if(mediaDataStr.length == 0) {
    		mediaDataStr = new String[1];
    		mediaDataStr[0] = "No Media With Title '" + title + "' Were Found";
    	}
    	lv = new ListView<>(FXCollections.observableArrayList(mediaDataStr));
    	lv.setPrefWidth(400);
    	mediaData.setCenter(new ScrollPane(lv));
    }
    
    /**
     * View media DB by type.
     *
     * @param type the type
     */
    private void viewMediaDBByType(String type) {
    	String[] mediaDataStr = controller.getMediaByType(type);
    	if(mediaDataStr.length == 0) {
    		mediaDataStr = new String[1];
    		mediaDataStr[0] = "No Media With Type '" + type + "' Were Found";
    	}
    	lv = new ListView<>(FXCollections.observableArrayList(mediaDataStr));
    	lv.setPrefWidth(400);
    	mediaData.setCenter(new ScrollPane(lv));
    }
    private void viewMediaDBByTypeAndTitle(String type, String title) {
    	String[] mediaDataStr = controller.getMediaByTypeAndTitle(type,title);
    	if(mediaDataStr.length == 0) {
    		mediaDataStr = new String[1];
    		mediaDataStr[0] = "No Media With Title '" + title + "' and Type '"+ type + "' Were Found";
    	}
    	lv = new ListView<>(FXCollections.observableArrayList(mediaDataStr));
    	lv.setPrefWidth(400);
    	mediaData.setCenter(new ScrollPane(lv));
    }
    private void clearTextFields() {
    	
    	bookAuthor.clear();
    	bookTitle.clear();
    	bookFormat.clear();
    	bookLocation.clear();
    	bookNotes.clear();

    	songArtist.clear();
    	songTitle.clear();
    	songGenre.clear();
    	songFormat.clear();
    	songLocation.clear();
    	songNotes.clear();
    		
    	videoTitle.clear();
    	videoStar.clear();
    	videoFormat.clear();
    	videoLocation.clear();
    	videoNotes.clear();
    		
    	vgTitle.clear();
    	vgFormat.clear();
    	vgLocation.clear();
    	vgNotes.clear();
    }    
  /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

} ;