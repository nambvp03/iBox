package com.dropbox;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "javax.net.ssl.*", "javax.security.*" })

public class GoogleDriveManagerTest {
	Drive serviceMock = mock(Drive.class, RETURNS_DEEP_STUBS);
	/*@Before
	public void setup() throws IOException {
		String fileName = "a.txt";
		File fileMetadata = new File();
		fileMetadata.setName(fileName);
		//java.io.File filePath = new java.io.File("./iboxLocalDrive/"+ fileName);
		//FileContent mediaContent = new FileContent("text/txt", filePath);

		Drive.Files mock2 = mock(Drive.Files.class);
		Drive.Files.Create mock3 = mock(Drive.Files.Create.class);
		Drive.Files.Create mock4 = mock(Drive.Files.Create.class);		
		File mock5 = mock(File.class);


		when(serviceMock.files()).thenReturn(mock2);
		when(mock2.create(any(File.class),any(FileContent.class))).thenReturn(mock3);
		when(mock3.setFields(eq("id"))).thenReturn(mock4);
		when(mock4.execute()).thenReturn(mock5);

		Drive.Files.Delete mock6 = mock(Drive.Files.Delete.class);
		when(mock2.delete(any(String.class))).thenReturn(mock6);
		when(mock6.execute()).thenReturn(null);
	}


	@PrepareForTest(File.class)
	@Test
	public void testCreateFile() throws IOException, GeneralSecurityException {
		assertNotNull(GoogleDriveManager.createFile(serviceMock, Paths.get("a.txt")));
		verify(serviceMock, times(1)).files();		
	}
	@PrepareForTest(File.class)
	@Test
	public void testDeleteFile() throws IOException, GeneralSecurityException {
		when(GoogleDriveManager.getFileId(any(Drive.class), any(Path.class))).thenReturn("dummyFileId");
		assertNull(GoogleDriveManager.deleteFile(serviceMock, Paths.get("a.txt")));
		//verify(serviceMock, times(1)).files();		
	}
	@PrepareForTest(File.class)
	@Test
	public void testModifyFile() throws IOException, GeneralSecurityException {

		assertFalse(GoogleDriveManager.modifyFile(serviceMock, Paths.get("a.txt")));
		verify(serviceMock, times(1)).files();		
	}*/

	@PrepareForTest(File.class)
	@Test
	public void testCreateFile() throws IOException, GeneralSecurityException {

		Drive.Files mock2 = mock(Drive.Files.class);
		Drive.Files.Create mock3 = mock(Drive.Files.Create.class);
		Drive.Files.Create mock4 = mock(Drive.Files.Create.class);		
		File mock5 = mock(File.class);

		when(serviceMock.files()).thenReturn(mock2);
		when(mock2.create(any(File.class),any(FileContent.class))).thenReturn(mock3);
		when(mock3.setFields(eq("id"))).thenReturn(mock4);
		when(mock4.execute()).thenReturn(mock5);
		
		assertNotNull(GoogleDriveManager.createFile(serviceMock, Paths.get("a.txt")));
		verify(serviceMock, times(1)).files();		
	}

	@PrepareForTest(FileList.class)
	@Test
	public void deleteFileUnitTest() throws IOException, GeneralSecurityException {

		/*Drive servicemock = mock(Drive.class);
		Drive.Files mock2 = mock(Drive.Files.class);
		Drive.Files.List mock3 = mock(Drive.Files.List.class);
		FileList mock4 = mock(FileList.class);

		when(servicemock.files()).thenReturn(mock2);
		when(mock2.list()).thenReturn(mock3);
		when(mock3.execute()).thenReturn(mock4);

		Path path = Paths.get("a.txt");
		assertFalse(GoogleDriveManager.deleteFile(servicemock, path));
		verify(servicemock, times(1)).files();	*/	
	}

	@PrepareForTest(FileList.class)
	@Test
	public void testModifyFile() throws IOException, GeneralSecurityException {
		/*
		Drive servicemock = mock(Drive.class);
		Drive.Files mock2 = mock(Drive.Files.class);
		Drive.Files.List mock3 = mock(Drive.Files.List.class);
		FileList mock4 = mock(FileList.class);
		when(servicemock.files()).thenReturn(mock2);
		when(mock2.list()).thenReturn(mock3);
		when(mock3.execute()).thenReturn(mock4);

		Path path = Paths.get("a.txt");
		assertFalse(GoogleDrive.deleteFile(servicemock, path));
		verify(servicemock, times(1)).files();	
		 */	
	}
/*
	@Test
	public void findFileIdTest()  {		
		java.util.List<File> files = new ArrayList<File>();

		Path path = Paths.get("a.txt");
		assertEquals(GoogleDriveManager.getFileId(files, path), "");		
	}*/
}
