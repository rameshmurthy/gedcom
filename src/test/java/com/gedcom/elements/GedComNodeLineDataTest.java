package com.gedcom.elements;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.gedcom.elements.GedComAttribute;
import com.gedcom.elements.GedComNode;
import com.gedcom.elements.LineData;
import com.gedcom.parser.GedComLineParser;

/**
 * Constructor of {@link GedComNode} takes LineData object, to test whether it is able to construct 
 * {@link GedComNode} from {@link LineData} or not
 * @author Venkata S S R Murthy Manda
 *
 */
@RunWith(value=Parameterized.class)
public class GedComNodeLineDataTest {
	
	private String gedComString;
	private String tag;
	private String id;
	private String data;
	private String level;
	
	public GedComNodeLineDataTest(String gedComString,String level,String tag,String id,String data) {
		this.gedComString = gedComString;
		this.tag = tag;
		this.id = id;
		this.data = data;
		this.level = level;
	}
	
	
	@Parameters
	public static Collection<String[]> getGedComParameters() {
																						//Level, TAG,    ID,	data		
		return Arrays.asList(new String[][]{{"0 @I0001@ INDI",							 	 "0",null,"@I0001@","INDI"},//GedComString - Level,Tag,Id,Data
											{"1 NAME Elizabeth Alexandra Mary /Windsor/",	 "1","NAME",null,"Elizabeth Alexandra Mary /Windsor/"},
											{"1    NAME Elizabeth Alexandra Mary /Windsor/", "1","NAME",null,"Elizabeth Alexandra Mary /Windsor/"}, // Variable white space
											// between level and tag or id
											{"1 BIRT",										 "1","BIRT",null,null},
											{"2 PLAC 17 Bruton Street, London, W1",			 "2", "PLAC", null, "17 Bruton Street, London, W1"},
											{"1 FAMC @F0002@",								 "1", "FAMC", null, "@F0002@"},
											{"22 FAMC @F0002@",								 "22", "FAMC", null, "@F0002@"},
											{"1 SEX M",										 "1", "SEX", null, "M"},
											{"2 DATE  6 Mar 2004",							 "2","DATE",null,"6 Mar 2004"},
											{"1 NAME (female)",								 "1","NAME",null,"(female)"}});
	}
	
	@Test
	public void createGedComNodeFromLineData() {
		
		GedComLineParser gedComLineParser = GedComLineParser.getInstance();
		try {
			LineData lineData = gedComLineParser.parseLine(gedComString);
			GedComNode expectedNode = null;
			if(id == null){
				expectedNode = new GedComNode(Integer.parseInt(level),tag, (data != null?GedComAttribute.TAG:null), data, null);
			} else {
				expectedNode = new GedComNode(Integer.parseInt(level),data, GedComAttribute.ID, id, null);
			}
			
			GedComNode actualNode = new GedComNode(lineData, null);
			Assert.assertEquals("GedComNode(lineData,parent) constructor not working as expected",expectedNode, actualNode);
			
		} catch (ParseException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	
}
