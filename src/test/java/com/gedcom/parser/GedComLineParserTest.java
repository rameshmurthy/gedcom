package com.gedcom.parser;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.gedcom.elements.LineData;
import com.gedcom.parser.GedComLineParser;

/**
 * To test {@link GedComLineParser} is able to successfully create LineData object from the
 * passed GEDCOM Line String
 * @author Venkata S S R Murthy Manda
 *
 */
@RunWith(value=Parameterized.class)
public class GedComLineParserTest {
	
	private String gedComString;
	private String tag;
	private String id;
	private String data;
	private String level;
	
	public GedComLineParserTest(String gedComString,String level,String tag,String id,String data) {
		this.gedComString = gedComString;
		this.tag = tag;
		this.id = id;
		this.data = data;
		this.level = level;
	}
	
	@Parameters
	public static Collection<String[]> getGedComParameters() {
																						//Level, TAG,    ID,	data
		return Arrays.asList(new String[][]{{"0 @I0001@ INDI",							 	"0",null,"@I0001@","INDI"},//GedComString - Level,Tag,Id,Data
											{"1 NAME Elizabeth Alexandra Mary /Windsor/",	"1","NAME",null,"Elizabeth Alexandra Mary /Windsor/"},
											{"1    NAME Elizabeth Alexandra Mary /Windsor/", "1","NAME",null,"Elizabeth Alexandra Mary /Windsor/"}, // Variable white space
											// between level and tag or id
											{"1 BIRT",										"1","BIRT",null,null},
											{"2 PLAC 17 Bruton Street, London, W1",			"2", "PLAC", null, "17 Bruton Street, London, W1"},
											{"1 FAMC @F0002@",								"1", "FAMC", null, "@F0002@"},
											{"22 FAMC @F0002@",								"22", "FAMC", null, "@F0002@"},
											{"1 SEX M",										"1", "SEX", null, "M"},
											{"2 DATE  6 Mar 2004",							"2","DATE",null,"6 Mar 2004"},
											{"1 NAME (female)",								"1","NAME",null,"(female)"}});
	}
	
	@Test
	public void parseParameterizedGedComData() {
		GedComLineParser gedComLineParser = new GedComLineParser();
		LineData actual;
		try {
			actual = gedComLineParser.parseLine(gedComString);
			LineData expectedLineData = new LineData(Integer.parseInt(level),tag,id,data);
			Assert.assertEquals("GEDCOM line : "+gedComString+", not properly parsed. Test Failed!", expectedLineData,actual);
		} catch (ParseException e) {
			Assert.fail(e.getMessage());
		}

	}

}
