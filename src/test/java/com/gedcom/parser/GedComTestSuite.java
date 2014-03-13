package com.gedcom.parser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.gedcom.elements.GedComAttributeTest;
import com.gedcom.elements.GedComNodeLineDataTest;
import com.gedcom.elements.GedComNodeTest;
import com.gedcom.util.FileUtilityTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	GedComFileParserTest.class,
	GedComLineParserTest.class,
	GedComLineParserValidationTest.class,
	GedComAttributeTest.class,
	GedComNodeLineDataTest.class,
	GedComNodeTest.class,
	FileUtilityTest.class
})
public class GedComTestSuite {

}
