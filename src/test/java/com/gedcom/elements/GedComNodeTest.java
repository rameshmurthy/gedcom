package com.gedcom.elements;

import org.junit.Assert;
import org.junit.Test;

import com.gedcom.elements.GedComAttribute;
import com.gedcom.elements.GedComNode;

/**
 * To test {@link GedComNode} and XML Generation of the node.
 * @author Venkata S S R Murthy Manda
 *
 */
public class GedComNodeTest {

	@Test
	public void isLeafNode() {
		GedComNode gedComNode = new GedComNode(1,"INDI", GedComAttribute.ID, "@I0001@", null);
		Assert.assertTrue("Not a leaf node", gedComNode.isLeafNode());
	}

	@Test
	public void isRootNode() {
		GedComNode gedComNode = new GedComNode(0,"INDI", GedComAttribute.ID, "@I0001@", null);
		Assert.assertTrue("Not a root node", gedComNode.isLeafNode());
	}
	
	/**
	 * 0 @I0001@ INDI
	 * 1 NAME Elizabeth Alexandra Mary /Windsor/
	 * 1 SEX F
	 * 1 BIRT
	 * 2 DATE 21 Apr 1926
	 * 2 PLAC 17 Bruton Street, London, W1
	 * 
	 * Result :
	 *  <gedcom> 
	 * 	<INDI id="@I0001@">
	 * 		<NAME>Elizabeth Alexandra Mary /Windsor/</NAME>
	 * 		<SEX>F</SEX>
	 * 		<BIRT>
	 *			<DATE>21 Apr 1926</DATE>
	 *			<PLAC>17 Bruton Street, London, W1</PLAC>
	 *		</BIRT>
	 *	</INDI>
	 *</gedCom>
	 */
	@Test
	public void getXMLString() {
		GedComNode rootNode = new GedComNode(-1,"gedcom",null,null,null);
		
		GedComNode gedComIndiNode = new GedComNode(0,"INDI", GedComAttribute.ID, "@I0001@", rootNode);
		rootNode.addChildren(gedComIndiNode);
		GedComNode nameChild = new GedComNode(1,"NAME", null, "Elizabeth Alexandra Mary /Windsor/", gedComIndiNode);
		GedComNode sexChild = new GedComNode(1,"SEX", null, "F", gedComIndiNode);
		
		GedComNode birthChild = new GedComNode(1,"BIRT", null, null, gedComIndiNode);
		GedComNode dateChild = new GedComNode(2,"DATE", null, "21 Apr 1926", birthChild);
		GedComNode placeChild = new GedComNode(2,"PLAC", null, "17 Bruton Street, London, W1", birthChild);
		
		birthChild.addChildren(dateChild);
		birthChild.addChildren(placeChild);
		
		gedComIndiNode.addChildren(nameChild);
		gedComIndiNode.addChildren(sexChild);
		gedComIndiNode.addChildren(birthChild);

		String actualXML = rootNode.getXMLString();
		
		String expectedXML = new String("<gedcom>\n" +
									"<INDI id=\"@I0001@\">\n"+
										"<NAME>Elizabeth Alexandra Mary /Windsor/</NAME>\n"+
										"<SEX>F</SEX>\n"+
										"<BIRT>\n"+
											"<DATE>21 Apr 1926</DATE>\n"+
											"<PLAC>17 Bruton Street, London, W1</PLAC>\n"+
										"</BIRT>\n"+
									"</INDI>\n"+
									"</gedcom>");
		Assert.assertEquals("Generated XML from GedComNode is not correct", expectedXML, actualXML);
	}
	
	@Test
	public void getXMLStringOfSingleNode() {
		GedComNode rootNode = new GedComNode(-1,"gedcom",null,null,null);

		GedComNode nameNode = new GedComNode(1,"NAME", null, "Elizabeth Alexandra Mary /Windsor/", rootNode);
		rootNode.addChildren(nameNode);
		String actualXML = rootNode.getXMLString();
		String expectedXML = new String("<gedcom>\n"+
											"<NAME>Elizabeth Alexandra Mary /Windsor/</NAME>\n"+
										"</gedcom>");

		Assert.assertEquals("Generated XML from GedComNode is not correct", expectedXML, actualXML);
		
		rootNode = new GedComNode(-1,"gedcom",null,null,null);
		GedComNode indiNode = new GedComNode(0,"INDI", GedComAttribute.ID, "@I0001@", rootNode);
		rootNode.addChildren(indiNode);
		nameNode = new GedComNode(1,"NAME", null, "Elizabeth Alexandra Mary /Windsor/", indiNode);
		indiNode.addChildren(nameNode);
		actualXML = rootNode.getXMLString();
		expectedXML = new String("<gedcom>\n"+
									"<INDI id=\"@I0001@\">\n"+
										"<NAME>Elizabeth Alexandra Mary /Windsor/</NAME>\n"+
									"</INDI>\n"+
								"</gedcom>");
		Assert.assertEquals("Generated XML from GedComNode is not correct", expectedXML, actualXML);
		
		rootNode = new GedComNode(-1,"gedcom",null,null,null);
		GedComNode trlrNode = new GedComNode(0,"TRLR", null, "", rootNode);
		rootNode.addChildren(trlrNode);
		actualXML = rootNode.getXMLString();
		expectedXML = new String("<gedcom>\n"+
											"<TRLR></TRLR>\n"+
								"</gedcom>");

		Assert.assertEquals("Generated XML from GedComNode is not correct", expectedXML, actualXML);

	}
	
	
}
