package com.newfilemonitor;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;

public class FinalFileMonitorJavaOld {

	public static void main(String[] args) {

		String path = "D:/eclipseWorkspace/FileMonitor/monitor/chk.txt";
		inputFile(path);

	}

	private static void inputFile(final String strPath) {

		try {

			String[] s = strPath.split("/", -1);
			// for(int i=0; i<s.length;i++){
			// System.out.println(i + " - " + s[i]);
			// }
			String fName = s[s.length - 1];
			// System.out.println("f - "+f_Name);
			String folderName = strPath.replace(fName, "");
			// System.out.println("path - "+ strPath.replace(f_Name, ""));

			FileSystemManager fsManager = VFS.getManager();
			FileObject listendir = fsManager.resolveFile(folderName);

			int i;
			FileReader fileReader = new FileReader(strPath);

			System.out.println("\n--------------Existing------------\n");
			while ((i = fileReader.read()) != -1) {
				System.out.print((char) i);
			}
			System.out.println("\n----------------------------------\n");

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

					// String fileName = "D:/eclipseWorkspace/FileMonitor/monitor/chk.txt";
					System.out.println("File Changed:" + event.getFile().getName());
					readfile(strPath);

				}

				private void readfile(String strPath) {
					// TODO Auto-generated method stub
					System.out.println("File MODIFY path: " + strPath);

					// File file = new File(strPath.toString());

					try (FileInputStream fis = new FileInputStream(strPath.toString())) {
						int content;
						System.out.println("-------------Updated-------------\n");
						while ((content = fis.read()) != -1) {
							// convert to char and display it
							System.out.print((char) content);
						}
						System.out.println("\n-------------------------------\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
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

}
