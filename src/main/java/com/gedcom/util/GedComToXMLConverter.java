package com.gedcom.util;

import java.io.IOException;
import java.text.ParseException;

import com.gedcom.elements.GedComNode;
import com.gedcom.parser.GedComFileParser;

/**
 * GEDCOM data is parsed using a {@link FileParser} and then is converted to XML.
 * @author Venkata S S R Murthy Manda
 *
 */
public class GedComToXMLConverter {

	private FileParser<GedComNode> fileParser;

	public GedComToXMLConverter() {
		fileParser = new GedComFileParser();
	}

	public void convertGedComToXML(String inputFileName,String outputFileName){
		try {
			System.out.println("------------------- converting : "+inputFileName+", to : "+outputFileName);
			XMLGenerator xmlGenerator = fileParser.parse(inputFileName);
			FileUtility.createFile(xmlGenerator.getXMLString(), outputFileName);
			System.out.println("------------------------------------ Successfully conveted ------------------------------");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param fileParser the fileParser to set
	 */
	public void setFileParser(FileParser<GedComNode> fileParser) {
		this.fileParser = fileParser;
	}

	public static void main(String[] args) {

		if(args.length<2) {
			System.err.println("Input Argument lenght is less than 2, try : " +
					"java -classpath aconexgedcom.jar com.aconex.util.GedComToXMLConverter");
			System.exit(0);
		}
		GedComToXMLConverter gedComToXMLConveter = new GedComToXMLConverter();
		String inputFileName = args[0];
		String outputFileName = args[1];
		gedComToXMLConveter.convertGedComToXML(inputFileName, outputFileName);

	}
}
