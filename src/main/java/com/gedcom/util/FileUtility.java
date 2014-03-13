package com.gedcom.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility Class for file related activities.
 * @author Venkata S S R Murthy Manda
 *
 */
public class FileUtility {

	/**
	 * Returns a file reader for the passed inputFile
	 * @param inputFileFullPathName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static BufferedReader getFileReader(String inputFileFullPathName) throws FileNotFoundException {
		File inputFile = new File(inputFileFullPathName);
		FileReader inputFileReader = new FileReader(inputFile);
		BufferedReader bufferedReader = new BufferedReader(inputFileReader);
		return bufferedReader;
	}

	/**
	 * Creates file with passed fileName and data.
	 * @param data
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public static File createFile(String data, String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		BufferedWriter bufferedWriter = null;
		try {
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bufferedWriter !=null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}
}
