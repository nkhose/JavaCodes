package com.newfilemonitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;

public class ReadDirectory {

	public static void main(String[] args) {

		String path = "D:/eclipseWorkspace/FileMonitor/monitor/";
		inputFile(path);

	}

	private static void inputFile(final String folderPath) {

		try {

			FileSystemManager fsManager = VFS.getManager();
			FileObject listendir = fsManager.resolveFile(folderPath);


			File[] listOfFiles = (new File(folderPath)).listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
			    readfile(folderPath + listOfFiles[i].getName());
			  } 
//			  else if (listOfFiles[i].isDirectory()) {
//			    System.out.println("Directory " + listOfFiles[i].getName());
//			  }
			}
			
			
			
			DefaultFileMonitor fm = new DefaultFileMonitor(new FileListener() {

				@Override
				public void fileDeleted(FileChangeEvent event) throws Exception {
					// TODO Auto-generated method stub
					System.out.println("File Deleted:" + event.getFile().getName());
				}

				@Override
				public void fileCreated(FileChangeEvent event) throws Exception {
					// TODO Auto-generated method stub
					System.out.println("File Created:" + event.getFile().getName());
				}

				@Override
				public void fileChanged(FileChangeEvent event) throws Exception {
					// TODO Auto-generated method stub

					System.out.println("File Changed:" + event.getFile().getName());
					readfile(event.getFile().getName().toString().replace("file:///", ""));

				}

				
			});

			fm.start();
			boolean valid = true;
			do {
				fm.setRecursive(true);
				fm.addFile(listendir);

			} while (valid);

		} catch (Exception e) {
			System.out.println("Something went wrong/ File not found");
			e.printStackTrace();
		}
	}
	
	private static void readfile(String strPath) {
		// TODO Auto-generated method stub
		
		try (FileInputStream fis = new FileInputStream(strPath.toString())) {
			int content;
			System.out.println("--- "+"File : " + strPath+" ---\n");
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				System.out.print((char) content);
			}
			System.out.println("\n-------------------------------\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
