package edu.csupomona.cs585.ibox;

import junit.framework.TestCase;
import org.junit.*;
import org.mockito.internal.listeners.MockingProgressListener;

import static org.mockito.Mockito.*;


import java.io.IOException;
import java.util.ArrayList;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.services.drive.Drive;

import com.google.api.services.drive.Drive.Comments.Update;

import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Insert;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;

public class TestGoogleFileSyncManager extends TestCase {
	public Drive mockDrive = mock(Drive.class);
	public File file = new File();
	public java.io.File mockJavaFile = mock(java.io.File.class);
	public Files drivefiles = mock(Files.class);
	public Insert mockDriveInsert = mock(Insert.class);
	public GoogleDriveFileSyncManager DriveSync = new GoogleDriveFileSyncManager(mockDrive);
	public ArrayList<File> arrayList = new ArrayList<File>();
	public com.google.api.services.drive.Drive.Files.List mockDriveFilesList = mock(
			com.google.api.services.drive.Drive.Files.List.class);
	public FileList fileListObj = new FileList();
	public File FileObj = new File();
	public com.google.api.services.drive.Drive.Files.Update mockDriveUpdate = mock(com.google.api.services.drive.Drive.Files.Update.class);
	public com.google.api.services.drive.Drive.Files.Delete mockDriveDelete = mock(com.google.api.services.drive.Drive.Files.Delete.class);
	public File newFileObj = new File();
	
	

	@Test

	public void testAddFile() throws IOException {

		System.out.println("Test method for adding a file");
		when(mockDrive.files()).thenReturn(drivefiles);
		when(drivefiles.insert(isA(File.class), isA(AbstractInputStreamContent.class))).thenReturn(mockDriveInsert);
		when(mockDriveInsert.execute()).thenReturn(file);
		DriveSync.addFile(mockJavaFile);
		verify(mockDrive).files();
		verify(drivefiles).insert(isA(File.class), isA(AbstractInputStreamContent.class));
		verify(mockDriveInsert).execute();
	}

	@Test
	public void testUpdateFile() throws IOException {

		System.out.println("Test method for updating a file");
		String fileID = "1111";
		String FileName = "TestingTest.txt";

		FileObj.setTitle(FileName);
		FileObj.setId(fileID);
		arrayList.add(FileObj);
		fileListObj.setItems(arrayList);
		when(mockJavaFile.getName()).thenReturn(FileName);
		when(drivefiles.list()).thenReturn(mockDriveFilesList);
		when(mockDriveFilesList.execute()).thenReturn(fileListObj);
		when(mockDrive.files()).thenReturn(drivefiles);
		when(drivefiles.update(isA(String.class), isA(File.class), isA(AbstractInputStreamContent.class))).thenReturn(mockDriveUpdate);
		when(mockDriveUpdate.execute()).thenReturn(newFileObj);
		DriveSync.updateFile(mockJavaFile);
		verify(mockJavaFile, atLeast(1)).getName();
		verify(drivefiles).list();
		verify(mockDriveFilesList).execute();
		verify(mockDrive, atLeast(1)).files();
		verify(drivefiles).update(isA(String.class), isA(File.class), isA(AbstractInputStreamContent.class));
		verify(mockDriveUpdate).execute();

	}

	 @Test
	 public void testDeleteFile() throws
	 IOException {
	
		 System.out.println("Test method for deleting a file");
		 
		String fileID = "1111";
		String FileName = "TestingTest.txt";
		FileObj.setTitle(FileName);
		FileObj.setId(fileID);
		fileListObj.setItems(arrayList);
		arrayList.add(FileObj);
		when(mockJavaFile.getName()).thenReturn(FileName);
		when(drivefiles.list()).thenReturn(mockDriveFilesList);
		when(mockDriveFilesList.execute()).thenReturn(fileListObj);
		when(mockDrive.files()).thenReturn(drivefiles);
		when(drivefiles.delete(fileID)).thenReturn(mockDriveDelete);
		when(mockDriveDelete.execute()).thenReturn(null);
		DriveSync.deleteFile(mockJavaFile);
		verify(mockDriveFilesList).execute();
		verify(drivefiles).delete(fileID);
		verify(mockDriveDelete).execute();
			 
	 }

}
