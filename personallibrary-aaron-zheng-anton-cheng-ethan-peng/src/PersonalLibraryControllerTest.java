import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author aaron
 *
 */
class PersonalLibraryControllerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test_AddVideoWithBlankTitle() {
		PersonalLibraryController ctrl = new PersonalLibraryController();
		ctrl.addVideo("","","","","");
		assertTrue(ctrl.getMediaDataStr().length == 0);
		assertTrue(ctrl.addVideo("","","","","").equals("The title cannot be blank! Media will not be entered into personal library."));
		
	}
	@Test
	void test_AddVideoGameWithBlankTitle() {
		PersonalLibraryController ctrl = new PersonalLibraryController();
		ctrl.addVideoGame("","","","");
		assertTrue(ctrl.getMediaDataStr().length == 0);
		assertTrue(ctrl.addVideoGame("","","","").equals("The title cannot be blank! Media will not be entered into personal library."));
		
	}
	@Test
	void test_AddSongWithBlankTitle() {
		PersonalLibraryController ctrl = new PersonalLibraryController();
		ctrl.addSong("","","","","","");
		assertTrue(ctrl.getMediaDataStr().length == 0);
		assertTrue(ctrl.addSong("","","","","","").equals("The title cannot be blank! Media will not be entered into personal library."));
		
	}
	@Test
	void test_AddBookWithBlankTitle() {
		PersonalLibraryController ctrl = new PersonalLibraryController();
		ctrl.addBook("","","","","");
		assertTrue(ctrl.getMediaDataStr().length == 0);
		assertTrue(ctrl.addBook("","","","","").equals("The title cannot be blank! Media will not be entered into personal library."));
		
	}
	@Test
	void test_SearchbyType() {
		PersonalLibraryController ctrl = new PersonalLibraryController();
		ctrl.addBook("","testbook","","","");
		ctrl.addSong("","testsong","","","","");
		ctrl.addBook("testvideo","","","","");
		ctrl.addVideoGame("testvideogame","","","");
		assertTrue(ctrl.getMediaByType("Book").length == 1);
		assertTrue(ctrl.getMediaByType("Song").length == 1);
		assertTrue(ctrl.getMediaByType("Video Game").length == 1);
		assertTrue(ctrl.getMediaByType("Book").length == 1);
			
	}
	@Test
	void test_SearchbyTitle() {
		PersonalLibraryController ctrl = new PersonalLibraryController();
		ctrl.addBook("","testbook","","","");
		ctrl.addSong("","testsong","","","","");
		ctrl.addVideo("testvideo","","","","");
		ctrl.addVideoGame("testvideogame","","","");
		assertTrue(ctrl.getMediaByTitle("").length == 0);
		assertTrue(ctrl.getMediaByTitle("testbook").length == 1);
		assertTrue(ctrl.getMediaByTitle("testsong").length == 1);
		assertTrue(ctrl.getMediaByTitle("testvideo").length == 1);
		assertTrue(ctrl.getMediaByTitle("testvideogame").length == 1);
	}
	
	@Test
	void test_SearchbyTitleAndType() {
		PersonalLibraryController ctrl = new PersonalLibraryController();
		ctrl.addBook("","testbook","","","");
		ctrl.addSong("","testsong","","","","");
		ctrl.addVideo("testvideo","","","","");
		ctrl.addVideoGame("testvideogame","","","");
		assertTrue(ctrl.getMediaByTypeAndTitle("", "").length == 0);
		assertTrue(ctrl.getMediaByTypeAndTitle("Book", "testbook").length == 1);
		assertTrue(ctrl.getMediaByTypeAndTitle("Song", "testsong").length == 1);
		assertTrue(ctrl.getMediaByTypeAndTitle("Video", "testsong").length == 0);
		assertTrue(ctrl.getMediaByTypeAndTitle("Video", "testvideo").length == 1);
		assertTrue(ctrl.getMediaByTypeAndTitle("Video Game", "testvideogame").length == 1);
		assertTrue(ctrl.getMediaByTypeAndTitle("Video Game", "testvideogameqdqwdw").length == 0);
	}
	
	
	
	@Test
	void test_Delete() {
		PersonalLibraryController ctrl = new PersonalLibraryController();
		ctrl.addBook("","testbook","","","");
		ctrl.addBook("","testbook","","","");
		ctrl.addBook("","testbook2","","","");
		ctrl.getMediaByType("Book");
		ctrl.delete(1);
		assertTrue(ctrl.getMediaDataStr().length == 2);
		
		
	}

}
