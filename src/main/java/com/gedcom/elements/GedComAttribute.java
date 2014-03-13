package com.gedcom.elements;

/**
 * GEDCOM Data -- attributes are defined here.
 * It is used to represent {@link GedComNode} is holding id or tag attribute.
 * @author Venkata S S R Murthy Manda
 *
 */
public enum GedComAttribute {

	ID("id"),
	TAG("value");
	
	private String name;
	GedComAttribute(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
