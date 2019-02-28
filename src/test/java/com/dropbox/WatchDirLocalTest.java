package com.dropbox;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Test;

public class WatchDirLocalTest {

	@Test
	public void testRegister() {
		boolean thrown = true;

		  try {
		    new WatchDirLocal().register();
		  } catch (IOException e) {
		    thrown = false;
		  }
		  assertTrue(thrown);
	}
	
	@Test
	public void testProcessEvents() {
		GoogleDriveManager googleDrive = (GoogleDriveManager) mock(GoogleDriveManager.class);
		//new WatchDirLocal().processEvents(googleDrive);
	}
}
