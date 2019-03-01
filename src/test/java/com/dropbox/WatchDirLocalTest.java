package com.dropbox;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;

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
		boolean flag = false;
		try {
			TimerThread tt = new TimerThread();
			tt.start();
			new WatchDirLocal().processEvents();
			String absoluteFilePath = GoogleDriveManager.PATH_LOCAL + "infinite_test.txt";
			java.io.File file = new java.io.File(absoluteFilePath);
			file.createNewFile();
			file.setLastModified(new Date().getTime());
			file.delete();
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(flag);
	}
}
