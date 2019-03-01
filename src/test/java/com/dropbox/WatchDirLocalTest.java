package com.dropbox;

import static org.junit.Assert.assertTrue;

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
		try {
			TimerThread tt = new TimerThread();
			tt.start();
			new WatchDirLocal().processEvents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
