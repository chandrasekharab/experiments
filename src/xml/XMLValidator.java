/*
 * $Id$
 *
 * Copyright (c) 2014  Pegasystems Inc.
 * All rights reserved.
 *
 * This  software  has  been  provided pursuant  to  a  License
 * Agreement  containing  restrictions on  its  use.   The  software
 * contains  valuable  trade secrets and proprietary information  of
 * Pegasystems Inc and is protected by  federal   copyright law.  It
 * may  not be copied,  modified,  translated or distributed in  any
 * form or medium,  disclosed to third parties or used in any manner
 * not provided for in  said  License Agreement except with  written
 * authorization from Pegasystems Inc.
 */
package xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XMLValidator {

	static String xml = "<a><b/></a>";
	static String xsd = "<xs:schema xmlns:xs='http://www.w3.org/2001/XMLSchema'>"
							+ "<xs:element name='a'>"
								+ "<xs:complexType>"
									+ "<xs:sequence>"
										+ "<xs:element ref='b' minOccurs='0' maxOccurs='unbounded' />"
									+ "</xs:sequence>"
								+ "</xs:complexType>"
							+ "</xs:element>"
							+ "<xs:element name='b' />"
						+ "</xs:schema>";

	public static void main(String[] args) {
		System.out.println(validateXMLSchema("flow.xsd", "flow.xml"));

	}

	public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			
			InputStream xsdSource = new ByteArrayInputStream(xsd.getBytes());
			Schema schema = factory.newSchema(new StreamSource(xsdSource));
			Validator validator = schema.newValidator();
			
			InputStream xmlSource = new ByteArrayInputStream(xml.getBytes());
			validator.validate(new StreamSource(xmlSource));
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}
		return true;
	}
}
