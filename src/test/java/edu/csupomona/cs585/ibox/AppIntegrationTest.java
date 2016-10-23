package edu.csupomona.cs585.ibox;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.List;
import com.google.api.services.drive.model.FileList;

import edu.csupomona.cs585.ibox.sync.FileSyncManager;
import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;
import edu.csupomona.cs585.ibox.sync.GoogleDriveServiceProvider;

public class AppIntegrationTest {
	private Drive googleDriveClient;
	public WatchDir watchdir;

	@Before
	public void Initial() {
		this.googleDriveClient = GoogleDriveServiceProvider.get()
				.getGoogleDriveClient();

	}

	@Test
	public void integrationTesting() {

		/*
		 * FileSyncManager Drivesync = new GoogleDriveFileSyncManager(
		 * googleDriveClient);
		 * 
		 * Path ConvertedDir = Paths.get("C:\\iboxLocal"); try { WatchDir
		 * watchdir = new WatchDir(ConvertedDir, Drivesync);
		 * watchDirProcessEventThread thread = new watchDirProcessEventThread(
		 * watchdir); Thread t = new Thread(thread); t.start(); File file = new
		 * File(ConvertedDir + "\\testing.txt"); if (!file.exists()) {
		 * file.createNewFile(); assertTrue(FileExist(file.getName()));
		 * System.out.println("create"); } // Updating the file by Renaming
		 * file.renameTo(new File(ConvertedDir + "\\newtesting.txt"));
		 * assertTrue(FileExist(file.getName())); System.out.println("update");
		 * 
		 * // Deleting the file file.delete();
		 * assertTrue(FileExist(file.getName())); System.out.println("Del");
		 * t.interrupt();
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * }
		 * 
		 * public boolean FileExist(String fileName) { try { List request =
		 * googleDriveClient.files().list(); FileList files = request.execute();
		 * for (com.google.api.services.drive.model.File file : files
		 * .getItems()) { if (file.getTitle().equals(fileName)) { return true; }
		 * } } catch (IOException e) { System.out.println("An error occurred: "
		 * + e); } return true; }
		 */
	}
}