package com.dropbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;

import org.junit.Before;
import org.junit.Test;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;

public class GoogleDriveManagerIntegrationTest {

	static Drive service;
	@Before
	public void setup() throws GeneralSecurityException, IOException {
		String APPLICATION_NAME = "Google Drive API Java Quickstart";
		JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

		NetHttpTransport HTTP_TRANSPORT;
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, WatchDirLocal.getCredentials(HTTP_TRANSPORT))
					.setApplicationName(APPLICATION_NAME)
					.build();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateFileIntegration() throws IOException, GeneralSecurityException {
		
		String absoluteFilePath = GoogleDriveManager.PATH_LOCAL+"upload.txt";
		java.io.File file = new java.io.File(absoluteFilePath);
		file.createNewFile();    	

		Path path = Paths.get("upload.txt");
		assertTrue(GoogleDriveManager.createFile(service, path));
		assertFalse(GoogleDriveManager.getFileId(service, path).equals(""));

		file.delete();
		GoogleDriveManager.deleteFile(service, path);
	}

	@Test
	public void testCreateFileFailIntegration() throws IOException, GeneralSecurityException {	
		
		Path path = Paths.get("nonexistent.txt");
		assertFalse(GoogleDriveManager.createFile(service, path));
		assertEquals(GoogleDriveManager.getFileId(service, path),"");
	}

	@Test
	public void testDeleteFileIntegration() throws GeneralSecurityException, IOException  {
		
		String absoluteFilePath = GoogleDriveManager.PATH_LOCAL + "delete.txt";
		java.io.File file = new java.io.File(absoluteFilePath);
		file.createNewFile();  
		Path path = Paths.get("delete.txt");
		GoogleDriveManager.createFile(service, path);

		assertTrue(GoogleDriveManager.deleteFile(service, path));
		assertEquals(GoogleDriveManager.getFileId(service, path),"");

		file.delete();

	}

	@Test
	public void testDeleteFileFailIntegration() throws GeneralSecurityException, IOException  {
		
		Path path = Paths.get("nonexistent.txt");		
		assertFalse(GoogleDriveManager.deleteFile(service, path));        
	}

	@Test
	public void testModifyFileIntegration() throws GeneralSecurityException, IOException  {
		
		String absoluteFilePath = GoogleDriveManager.PATH_LOCAL + "modify.txt";
		java.io.File file = new java.io.File(absoluteFilePath);
		file.createNewFile();  
		Path path = Paths.get("modify.txt");
		GoogleDriveManager.createFile(service, path);

		assertTrue(GoogleDriveManager.modifyFile(service, path));
		assertFalse(GoogleDriveManager.getFileId(service, path).equals(""));

		file.delete();
		GoogleDriveManager.deleteFile(service, path);   
	}

	@Test
	public void testModifyFileFailIntegration() throws GeneralSecurityException, IOException  {
		
		Path path = Paths.get("nonexistent.txt");

		assertFalse(GoogleDriveManager.modifyFile(service, path));
		assertEquals(GoogleDriveManager.getFileId(service, path),"");
	}
}
