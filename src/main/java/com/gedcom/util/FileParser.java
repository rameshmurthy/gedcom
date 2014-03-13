package com.gedcom.util;

import java.io.IOException;
import java.text.ParseException;

/**
 * Generic file parser interface.
 * Implemented classes will have details of parsing logic
 * 
 * Ex: Input file in GEDCOM format
 * @author Venkata S S R Murthy Manda
 *
 */
public interface FileParser<T> {

	/**
	 * parse the file from the passed filename, to the required format and return with the passed
	 * output file name.
	 * @param inputfileName
	 * @param outputFileName
	 * @return
	 */
	T parse(String inputfileName) throws IOException, ParseException;
}
