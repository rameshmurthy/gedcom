package com.gedcom.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import com.gedcom.parser.GedComFileParser;
import com.gedcom.util.FileUtility;
import com.gedcom.util.XMLGenerator;

public class GedComFileParserTest {
	
	@Test
	public void parse() {
		GedComFileParser fileParser = new GedComFileParser();
		try {
			XMLGenerator xmlGenerator = fileParser.parse("./testdata/parseGedComFileTest.txt");
			File acutalParsed = FileUtility.createFile(xmlGenerator.getXMLString(), 
											"./testdata/parseGedComFileTestActual.xml");
			File expected = new File("./testdata/parseGedComFileTestExpected.xml");
			
			BufferedReader expectedReader = new BufferedReader(new FileReader(expected));
			BufferedReader actualReader = new BufferedReader(new FileReader(acutalParsed));
			
			String expectedLine = expectedReader.readLine();
			String actualLine = actualReader.readLine();
			boolean read = true;
			int lineNumber = 0;
			while(read) {
				lineNumber++;
				if(expectedLine == null && actualLine == null) {
					read = false;
					break;
				}
				
				if(expectedLine == null) {
					Assert.fail("./testdata/createFileTestExpected.xml  --- and --- " +
							"./testdata/createFileTestActual.xml doesnot match, " +
							"expectedLine is null but actualLine : "+actualLine+", at lineNumber : "+lineNumber);
					read = false;
				}
				
				if(actualLine == null) {
					Assert.fail("./testdata/createFileTestExpected.xml  --- and --- " +
							"./testdata/createFileTestActual.xml doesnot match, " +
							"actualLine is null but expectedLine : "+expectedLine+", at lineNumber : "+lineNumber);
					read = false;
				}
				
				boolean equals = expectedLine.equals(actualLine);
				if(!equals) {
					Assert.fail("./testdata/createFileTestExpected.xml  --- and --- " +
							"./testdata/createFileTestActual.xml doesnot match, " +
							"actualLine :"+ actualLine+", but expectedLine : "+expectedLine+", at lineNumber : "+lineNumber);
					read = false;
				}
				expectedLine = expectedReader.readLine();
				actualLine = actualReader.readLine();
			}

		} catch (IOException e) {
			Assert.fail(e.getMessage());
		} catch (ParseException e) {
			Assert.fail(e.getMessage());
		} 
	}
}
