package com.newfilemonitor;

import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;

public class FileMonitorJavaOld {

	public static void main(String[] args) throws IOException {
		FileSystemManager fsManager = VFS.getManager();
		FileObject listendir = fsManager.resolveFile("D:/eclipseWorkspace/FileMonitor/monitor/");

		// DefaultFileMonitor fm = new DefaultFileMonitor(new
		// CustomFileListener());

		String fileName1 = "D:/eclipseWorkspace/FileMonitor/monitor/chk.txt";

		int i;
		FileReader fileReader = new FileReader(fileName1);

		System.out.println("\n--------------Existing------------\n");
		while ((i = fileReader.read()) != -1)
			System.out.print((char) i);
		System.out.println("\n--------------------------\n");

		DefaultFileMonitor fm = new DefaultFileMonitor(new FileListener() {

			@Override
			public void fileDeleted(FileChangeEvent arg0) throws Exception {
				// TODO Auto-generated method stub

			}

			@Override
			public void fileCreated(FileChangeEvent arg0) throws Exception {
				// TODO Auto-generated method stub

			}

			@Override
			public void fileChanged(FileChangeEvent arg0) throws Exception {
				// TODO Auto-generated method stub

				String fileName = "D:/eclipseWorkspace/FileMonitor/monitor/chk.txt";
				System.out.println("File MODIFY:" + fileName);
				if (fileName.equals(fileName)) {
					// String s1 = path.toString() + "\\" + fileName;

					FileReader fileReader = new FileReader(fileName);

					int i;
					System.out.println("\n--------------Updated------------\n");
					while ((i = fileReader.read()) != -1)
						System.out.print((char) i);
					System.out.println("\n--------------------------\n");

				}
			}
		});

		fm.start();
		boolean valid = true;
		do{
			fm.setRecursive(true);
			fm.addFile(listendir);
			
		}while (valid);
		

	}

}
