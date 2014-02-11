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

package printer;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

public class HelloWorld {

	static String data = "<html>\r\n" + 
			"<style>\r\n" + 
			"table.lamp\r\n" + 
			"{\r\n" + 
			"width:100%;\r\n" + 
			"padding:0px;\r\n" + 
			"border:1px solid #d4d4d4;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"table.lamp th\r\n" + 
			"{\r\n" + 
			"color:#000000;\r\n" + 
			"background-color:white;\r\n" + 
			"padding:10px;\r\n" + 
			"padding-right:5px;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"table.lamp td\r\n" + 
			"{\r\n" + 
			"padding:4px;\r\n" + 
			"padding-left:0px;\r\n" + 
			"padding-right:10px;\r\n" + 
			"background-color:#ffffff;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"table.tecspec th,table.tecspec td{\r\n" + 
			"	border:1px solid #d4d4d4;padding:5px;padding-top:7px;padding-bottom:7px;vertical-align:top;text-align:left;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"table.reference,table.tecspec{\r\n" + 
			"	border-collapse:collapse;width:100%;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"table.reference tr:nth-child(odd)	{background-color:#F6F4F0;}\r\n" + 
			"table.reference tr:nth-child(even)	{background-color:#ffffff;}\r\n" + 
			"\r\n" + 
			"table.reference tr.fixzebra			{background-color:#F6F4F0;}\r\n" + 
			"\r\n" + 
			"table.reference th{\r\n" + 
			"	color:#ffffff;background-color:#555555;border:1px solid #555555;padding:3px;vertical-align:top;text-align:left;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"table.reference th a:link,table.reference th a:visited{\r\n" + 
			"	color:#ffffff;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"table.reference th a:hover,table.reference th a:active{\r\n" + 
			"	color:#EE872A;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"table.reference td{\r\n" + 
			"	border:1px solid #d4d4d4;padding:5px;padding-top:7px;padding-bottom:7px;vertical-align:top;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"table.reference td.example_code\r\n" + 
			"{\r\n" + 
			"vertical-align:bottom;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"table.summary\r\n" + 
			"{\r\n" + 
			"border:1px solid #d4d4d4;\r\n" + 
			"padding:5px;\r\n" + 
			"font-size:100%;\r\n" + 
			"color:#555555;\r\n" + 
			"background-color:#fafad2;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"</style>\r\n" + 
			"<body>\r\n" + 
			"<table class=\"reference\" style=\"width:60%;\">\r\n" + 
			"	<tbody>\r\n" + 
			"	<tr>\r\n" + 
			"		<th>First Name</th>\r\n" + 
			"		<th>Last Name</th>		\r\n" + 
			"		<th>Points</th>\r\n" + 
			"	</tr>\r\n" + 
			"	<tr>\r\n" + 
			"		<td>Jill</td>\r\n" + 
			"		<td>Smith</td>		\r\n" + 
			"		<td>50</td>\r\n" + 
			"	</tr>\r\n" + 
			"	<tr>\r\n" + 
			"		<td>Eve</td>\r\n" + 
			"		<td>Jackson</td>		\r\n" + 
			"		<td>94</td>\r\n" + 
			"	</tr>\r\n" + 
			"	<tr>\r\n" + 
			"		<td>John</td>\r\n" + 
			"		<td>Doe</td>		\r\n" + 
			"		<td>80</td>\r\n" + 
			"	</tr>\r\n" + 
			"	<tr>\r\n" + 
			"		<td>Adam</td>\r\n" + 
			"		<td>Johnson</td>		\r\n" + 
			"		<td>67</td>\r\n" + 
			"	</tr>\r\n" + 
			"</tbody></table>\r\n" + 
			"</body>\r\n" + 
			"</html>\r\n";
	
	public static void main(String args[]) throws PrinterException,
			PrintException, UnsupportedEncodingException {

		PrintService ps = null;

		PrintService[] services = PrinterJob.lookupPrintServices();

		for (PrintService printService : services) {
			if (printService.getName().contains("CutePDF")) {
			//if (printService.getName().contains("Printer_HYD-Pega1-Mono1")) {
				ps = printService;
				break;
			}
		}

		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		pras.add(new Copies(1));
		JTextPane textComp = new JTextPane();
		textComp.setContentType("text/html");
		
		
		textComp.setText(data);		
		textComp.print(null, null, false, ps, pras, false);
		

	}
}
