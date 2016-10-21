package edu.csupomona.cs585.ibox;

import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.api.services.drive.Drive.Files;

import edu.csupomona.cs585.ibox.sync.FileSyncManager;
import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static java.nio.file.StandardCopyOption.*;

/**
 * Placeholder for unit test
 */
public class AppTest extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */

	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	/**
	 * 
	 */
	public void testApp() throws Exception {
		// Getting path of directory
		try {
			String LocalDir = System.getProperty("user.dir");
			Path ConvertedDir = Paths.get(LocalDir);

			// Mocking GoogleDriveSyncManager.java using mock
			FileSyncManager DriveSync = mock(GoogleDriveFileSyncManager.class);

			WatchDir watchdir = new WatchDir(ConvertedDir, DriveSync);

			// Calling Thread since processEvent() is running in infinite loop
			watchDirProcessEventThread thread = new watchDirProcessEventThread(
					watchdir);
			Thread t = new Thread(thread);
			t.start();

			// Creating a new file
			File file = new File(ConvertedDir + "/" + "testingFile.txt");
			if (file.createNewFile()){
				System.out.println("File is created!");
				}else{
				System.out.println("File already exists.");
				}

			// Updating the file by adding text
			PrintWriter writeInTheFile = new PrintWriter(file);
			writeInTheFile.println("Updating the file with some text");
			writeInTheFile.close();
			file.delete();

			// Testing add,update and delete
			verify(DriveSync, atLeastOnce()).addFile(file);
			verify(DriveSync, atLeastOnce()).updateFile(file);
			verify(DriveSync, atLeastOnce()).deleteFile(file);

			t.interrupt();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
