package com.gedcom.elements;

import org.junit.Assert;
import org.junit.Test;

import com.gedcom.elements.GedComAttribute;

/**
 * GEDCOM Data -- attributes are defined here
 * @author Venkata S S R Murthy Manda
 *
 */
public class GedComAttributeTest {

	@Test
	public void attributeIdName() {
		GedComAttribute id = GedComAttribute.ID;
		Assert.assertEquals("GedComAttribute.ID's name is not equal to 'id'","id", id.getName());
	}
	
	@Test
	public void attributeValueName() {
		GedComAttribute value = GedComAttribute.TAG;
		Assert.assertEquals("GedComAttribute.VALUE's name is not equal to 'value'","value",value.getName());
	}
}
