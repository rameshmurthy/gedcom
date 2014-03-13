package com.gedcom.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.gedcom.util.FileUtility;

/**
 * Tests the FileUtility class
 * files to be present are  "./testdata/getFileReaderTest.txt" and "./testdata/createFileTest.txt"
 * @author Venkata S S R Murthy Manda 
 *
 */
public class FileUtilityTest {

	@Test
	public void getFileReader() {
		BufferedReader fileReader = null;
		try {
			fileReader = FileUtility.getFileReader("./testdata/getFileReaderTest.txt");
			String actual = fileReader.readLine();
			Assert.assertEquals("File Reading - failed, acutal and expected data doesnot match", "firstLine",actual);
		} catch (FileNotFoundException e) {
			Assert.fail(e.getMessage());
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		} finally {
			if(fileReader !=null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void createFile() {
		String data = "createFileTestData";
		String fileName = "./testdata/createFileTest.txt";
		BufferedReader fileReader = null;
		try {
			FileUtility.createFile(data,fileName);
			fileReader = FileUtility.getFileReader(fileName);
			String actual = fileReader.readLine();
			Assert.assertEquals("File Reading - failed, acutal and expected data doesnot match", data,actual);
		} catch (FileNotFoundException e) {
			Assert.fail(e.getMessage());
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		} finally {
			if(fileReader !=null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
	}
}
