package com.gedcom.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;

import com.gedcom.elements.GedComNode;
import com.gedcom.elements.LineData;
import com.gedcom.util.FileParser;
import com.gedcom.util.FileUtility;
import com.gedcom.util.LineParser;

/**
 * GedComFileParser - parses a file using {@link LineParser} and creates 
 * {@link GedComNode} object
 * @author Venkata S S R Murthy Manda
 *
 */
public class GedComFileParser implements FileParser<GedComNode> {
	
	private LineParser<LineData> gedComLineParser;
	
	public GedComFileParser() {
		gedComLineParser = GedComLineParser.getInstance();
	}
	
	public GedComFileParser(LineParser<LineData> lineParser) {
		gedComLineParser = lineParser;
	}
	
	/**
	 * Core logic of parsing the file and creation of XMLGenerator
	 * 
	 * @param inputFileFullPathName
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@Override
	public GedComNode parse(String inputFileFullPathName) throws IOException, ParseException {
		GedComNode rootNode = new GedComNode(-1,"gedcom",null,null,null);
		BufferedReader bufferedReader = FileUtility.getFileReader(inputFileFullPathName);
		String currentLine = null;
		GedComNode previousNode = null;

		while((currentLine = bufferedReader.readLine()) !=null) {
			if(currentLine.length()>0) {
				LineData lineData = gedComLineParser.parseLine(currentLine);
				if(lineData.getLevel() == 0) { // Start of the new sub tree under gedcom node
					GedComNode levelZeroNode = new GedComNode(lineData, rootNode);
					rootNode.addChildren(levelZeroNode);
					previousNode = levelZeroNode;
				} else {
					int currentLevel = lineData.getLevel();
					int previousLevel = previousNode.getLevel();
					if(currentLevel == previousLevel ) {
						GedComNode parent = previousNode.getParent();
						GedComNode currentNode = new GedComNode(lineData, parent);
						parent.addChildren(currentNode);
						previousNode = currentNode;
					} else if (currentLevel == previousLevel+1) {
						GedComNode currentNode = new GedComNode(lineData, previousNode);
						previousNode.addChildren(currentNode);
						previousNode = currentNode;
					} else if(currentLevel > previousLevel+1){
						throw new ParseException("Level Mismatch -- CurrentLevel is > previous level +1 , current Line : "+currentLevel, 1);
					} else {
						int differenceOfLevels = previousLevel - currentLevel; //How many parents we have to check
						GedComNode previousParent = previousNode.getParent();
						while((previousParent.getLevel() != currentLevel) && differenceOfLevels >= 0) {
							previousParent = previousParent.getParent();
							differenceOfLevels = previousParent.getLevel() - currentLevel;
						}
						GedComNode parent = previousParent.getParent();
						GedComNode currentNode = new GedComNode(lineData, parent);
						parent.addChildren(currentNode);
						previousNode = currentNode;
					}
				}

			}
		}
		return rootNode;
	}
	
}
