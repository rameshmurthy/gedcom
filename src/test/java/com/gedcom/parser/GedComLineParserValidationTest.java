package com.gedcom.parser;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import com.gedcom.parser.GedComLineParser;

/**
 * To test {@link GedComLineParser}, throws a parse exception or not, when passed error GEDCOM line strings.
 * @author Venkata S S R Murthy Manda
 *
 */
public class GedComLineParserValidationTest {
	
	@Test
	public void parseGedComDataWithoutLevel() {
		GedComLineParser gedComLineParser = new GedComLineParser();
		String gedComStrLine = "@I0001@ INDI";
		try {
			gedComLineParser.parseLine(gedComStrLine);
			Assert.fail("GEDCOM line : '"+gedComStrLine+"' doesnot started with level, exception should be thorwn.");
		} catch (ParseException e) {
			Assert.assertNotNull(e.getMessage(), e);
		}
	}
	
	@Test
	public void parseEmptyGedComData() {
		GedComLineParser gedComLineParser = new GedComLineParser();
		String gedComStrLine = "";
		try {
			gedComLineParser.parseLine(gedComStrLine);
			Assert.fail("GEDCOM line : '"+gedComStrLine+"' is empty, ParseException should be thorwn.");
		} catch (ParseException e) {
			Assert.assertNotNull(e.getMessage(), e);
		}
	}
	
	@Test
	public void parseNullGedComData() {
		GedComLineParser gedComLineParser = new GedComLineParser();
		String gedComStrLine = null;
		try {
			gedComLineParser.parseLine(gedComStrLine);
			Assert.fail("GEDCOM line : '"+gedComStrLine+"' is null, ParseException should be thorwn.");
		} catch (ParseException e) {
			Assert.assertNotNull(e.getMessage(), e);
		}
	}
}
