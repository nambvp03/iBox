package com.dropbox;

import java.util.Date;

public class TimerThread extends Thread {

	@Override
	public void run() {
		try {
			Thread.sleep(10*1000);
			WatchDirLocal.exitFlag = true;
			String absoluteFilePath = GoogleDriveManager.PATH_LOCAL + "dummy.txt";
			java.io.File file = new java.io.File(absoluteFilePath);
			file.createNewFile();
			file.setLastModified(new Date().getTime());
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Path path = Paths.get("modify.txt");
	}
}
