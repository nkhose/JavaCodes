package com.filemonitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class MonitorFileFinal {

	public static void main(String[] args) {

		String path = "D:/eclipseWorkspace/FileRead/monitor/myFile.txt";
		// String path = "C:/Downloads/C2ImportGroupsSample.csv";
		inputFile(path);

	}

	private static void inputFile(String strPath) {
		// TODO Auto-generated method stub
		try {

			String[] s = strPath.split("/", -1);
			String f_Name = s[s.length - 1];
			Path path = Paths.get(strPath.replace(f_Name, ""));

			WatchService watchService = FileSystems.getDefault().newWatchService();
			path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

			FileReader fileReader = new FileReader(strPath);

			int i;
			System.out.println("\n--------------Existing------------\n");
			while ((i = fileReader.read()) != -1)
				System.out.print((char) i);
			System.out.println("\n----------------------------------\n");

			boolean valid = true;
			do {
				WatchKey watchKey = watchService.take();

				for (WatchEvent event : watchKey.pollEvents()) {
					WatchEvent.Kind kind = event.kind();

					if (StandardWatchEventKinds.ENTRY_MODIFY.equals(event.kind())) {
						String fileName = event.context().toString();
						// System.out.println("File MODIFY:" + fileName);
						if (fileName.equals(f_Name)) {
							readfile(strPath);
						}

					}

				}
				valid = watchKey.reset();

			} while (valid);
		} catch (Exception e) {
			System.out.println("Something went wrong/ File not found");
			e.printStackTrace();
		}

	}

	private static void readfile(String path) {
		// TODO Auto-generated method stub
		// System.out.println("File MODIFY path: " + path);

		File file = new File(path.toString());

		try (FileInputStream fis = new FileInputStream(path.toString())) {
			int content;
			System.out.println("-------------Updated----------------\n");
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				System.out.print((char) content);
			}
			System.out.println("\n----------------------------------\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
