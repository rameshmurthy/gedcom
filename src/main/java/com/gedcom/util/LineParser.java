package com.gedcom.util;

import java.text.ParseException;

/**
 * Parser interface, different parsers has to implement this.
 * @author Venkata S S R Murthy Manda
 *
 * @param <T>
 */
public interface LineParser<T> {

	T parseLine(String strLine) throws ParseException;
}
