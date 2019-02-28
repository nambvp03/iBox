package com.dropbox;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

public class GoogleDriveManager {

	static final boolean RECURSIVE = false;
	static final String PATH_LOCAL = "/home/dell/Desktop/CPP/CS5850/iBox_local/";
	static final String PATH_DRIVE = "1xiIwOJphDnuBK4vPGUz3DVOjPIfTydjb";

	public static boolean createFile(Drive service, Path fileName) throws IOException {
		try {           
			File fileMetadata = new File();

			fileMetadata.setName(fileName.toString());
			fileMetadata.setParents(Collections.singletonList(PATH_DRIVE));

			java.io.File filePath = new java.io.File(PATH_LOCAL + fileName.toString());
			FileContent mediaContent = new FileContent("*/*", filePath);
			File file = service.files().create(fileMetadata, mediaContent)
					.setFields("id")
					.execute();
			return !file.getId().isEmpty();
		}
		catch (Exception e) {
			return false;
		}
	}

	public static boolean deleteFile(Drive service, Path fileName) {
		try {
			String fileId = getFileId(service, fileName);

			if (fileId != "") {
				service.files().delete(fileId).execute();
				return true;
			}
			return false;
		} 
		catch (IOException e) {
			System.out.println("An error occurred: " + e);
			return false;
		}
	}

	public static boolean modifyFile(Drive service, Path fileName) {
		try {
			String fileId = getFileId(service, fileName);
			if(fileId != "") {
				File fileMetadata = new File();
				fileMetadata.setName(fileName.toString());

				java.io.File filePath = new java.io.File(PATH_LOCAL + fileName.toString());
				FileContent mediaContent = new FileContent("*/*", filePath);
				File file = service.files().update(fileId, fileMetadata, mediaContent).execute();
				return !file.getId().isEmpty();
			} else {
				return createFile(service, fileName);
			}
		}
		catch (Exception e) {
			return false;
		}
	}

	public static String getFileId(Drive service, Path fileName) throws IOException {

		String fileId = "";
		FileList result = service.files().list()
				.setPageSize(100)
				.setFields("nextPageToken, files(id, name, parents)")
				.execute();
		List<File> files = result.getFiles();

		if (files == null || files.isEmpty()) {
			System.out.println("No files found.");
		} else {
			for (File file : files) {
				if(fileName.toString().equals(file.getName())
						&& PATH_DRIVE.equals(file.getParents().get(0)))
				{
					fileId = file.getId();
					break;
				}
			}
		}
		return fileId;
	}
}
