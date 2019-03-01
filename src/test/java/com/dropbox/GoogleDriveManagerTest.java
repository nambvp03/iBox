package com.dropbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

	@PrepareForTest(File.class)
	@Test
	public void testCreateFile() throws IOException, GeneralSecurityException {

		Drive serviceMock = mock(Drive.class);
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
	public void testDeleteFile() throws IOException, GeneralSecurityException {

		/*Drive serviceMock = mock(Drive.class);
		Drive.Files mock2 = mock(Drive.Files.class);
		Drive.Files.List mock3 = mock(Drive.Files.List.class);
		FileList mock4 = mock(FileList.class);
		GoogleDriveManager googleDriveManagerMock = mock(GoogleDriveManager.class);
		Path path = Paths.get("a.txt");

		when(serviceMock.files()).thenReturn(mock2);
		when(mock2.list()).thenReturn(mock3);
		when(mock3.execute()).thenReturn(mock4);
		when(googleDriveManagerMock.getFileId(serviceMock, path)).thenReturn("");

		assertFalse(GoogleDriveManager.deleteFile(serviceMock, path));
		verify(serviceMock, times(1)).files();*/	
	}

	@PrepareForTest(FileList.class)
	@Test
	public void testModifyFile() throws IOException, GeneralSecurityException {
		
		Drive serviceMock = mock(Drive.class);
		Drive.Files mock2 = mock(Drive.Files.class);
		Drive.Files.List mock3 = mock(Drive.Files.List.class);
		FileList mock4 = mock(FileList.class);
		when(serviceMock.files()).thenReturn(mock2);
		when(mock2.list()).thenReturn(mock3);
		when(mock3.execute()).thenReturn(mock4);

		Path path = Paths.get("a.txt");
		assertFalse(GoogleDriveManager.modifyFile(serviceMock, path));
		verify(serviceMock, times(1)).files();		
	}

	@PrepareForTest(FileList.class)
	@Test
	public void testGetFileId()  {		
		Drive serviceMock = mock(Drive.class);
		Drive.Files mock2 = mock(Drive.Files.class);
		Drive.Files.List mock3 = mock(Drive.Files.List.class);
		FileList mock4 = mock(FileList.class);
		
		Path path = Paths.get("a.txt");
		try {
			when(serviceMock.files()).thenReturn(mock2);
			when(mock2.list()).thenReturn(mock3);
			when(mock3.setPageSize(100)).thenReturn(mock3);
			when(mock3.setFields("nextPageToken, files(id, name, parents)")).thenReturn(mock3);
			when(mock3.execute()).thenReturn(mock4);
			when(mock4.getFiles()).thenReturn(null);
			
			assertEquals("", GoogleDriveManager.getFileId(serviceMock, path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
