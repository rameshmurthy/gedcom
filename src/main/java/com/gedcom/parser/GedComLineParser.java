package com.gedcom.parser;

import java.text.ParseException;

import com.gedcom.elements.LineData;
import com.gedcom.util.LineParser;

/**
 * GEDCOM Line Parser - parses GEDCOM formatted line and creates {@link LineData}.
 * @author Venkata S S R Murthy Manda
 *
 */
public class GedComLineParser implements LineParser<LineData> {
	
	private static GedComLineParser gedComLineParser;
	
	/**
	 * singleton instance of GedComLineParser
	 * @return
	 */
	public static GedComLineParser getInstance() {
		synchronized (GedComLineParser.class) {
			if(gedComLineParser == null) {
				gedComLineParser = new GedComLineParser();
			}
		}
		return gedComLineParser;
	}

	/**
	 * Parses GEDCOM Line and generates LineData object
	 * Ex: 1 @I0001@ INDI --> to a  LineData object
	 */
	public LineData parseLine(String gedComStrLine) throws ParseException {

		if(gedComStrLine == null || gedComStrLine.trim().length() == 0) {
			throw new ParseException("Either null or empty string is passed", 0);
		}
		gedComStrLine = gedComStrLine.trim();
		boolean startsWith = Character.isDigit(gedComStrLine.charAt(0));
		if(!startsWith) {
			throw new ParseException("Passed string'"+gedComStrLine+"' doesnt start with interger. Not in GEDCOM format", 1);
		}

		String tag = null;
		String remainder = null;
		String id = null;
		String[] split = gedComStrLine.split(" ");
		if(split.length<2) {
			throw new ParseException("Passed string'"+gedComStrLine+"' is not in GEDCOM format, short of required white spaces (LEVEL TAG_OR_ID [DATA])", 0);
		}
		int level = Integer.parseInt(split[0]);
		
		//Variable whitespace is allowed between the level and the tag. -- to accomodate this -- following loop helps in identifying
		// when the tag or id starts after white spaces.
		int indexOfStartOfTagOrIdElemnt = 1;
		for(int i=1;i<split.length;i++){
			if(split[i].length() > 0) {
				indexOfStartOfTagOrIdElemnt = i;
				break;
			}
		}
		
		if(split[indexOfStartOfTagOrIdElemnt].matches("@\\w*@")) {
			id = split[indexOfStartOfTagOrIdElemnt];
		} else {
			tag = split[indexOfStartOfTagOrIdElemnt];
			if(tag.length() < 3 || tag.length() > 4){
				throw new ParseException("Passed string'"+gedComStrLine+"' is not in GEDCOM format, TAG lenght is not either 3 0r 4.", 0);
			}
		}
		
		for(int i=indexOfStartOfTagOrIdElemnt+1;i<split.length;i++){
			if( i == indexOfStartOfTagOrIdElemnt+1) {
				remainder = "";
			} else	if(i < split.length) {
				remainder = remainder + " ";
			}
			remainder = remainder + split[i];
		}
		
		//2 DATE  6 Mar 2004 ---> for this GEDCOM String there are two white spaces after DATE. Due to this remainder = " 6 Mar 2004" -- by trimming white space before 6 is removed
		if(remainder !=null) {
			remainder = remainder.trim();
		}
		LineData lineDate = new LineData(level,tag,id,remainder);

		return lineDate;
	}
	
}
