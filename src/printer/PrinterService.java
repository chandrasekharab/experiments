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

import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.JTextPane;

public class PrinterService {

	private String printer;
	private int numberOfCopies = 1;
	private Printable printable;
	
	public PrinterService(String printer, int numberOfCopies) {
		this.printer = printer;
		this.numberOfCopies = numberOfCopies;
		this.printable = new DefaultPrintable();
	}
	
	public PrinterService(String printer, int numberOfCopies, Printable printable) {
		this(printer, numberOfCopies);
		this.printable = printable;
	}
	
	/**
	 * 
	 * @param data
	 * @param printerName
	 * @throws PrinterException
	 */
	public static void print(String data, String printerName) throws PrinterException {
		PrintService ps = null;

		PrintService[] services = PrinterJob.lookupPrintServices();

		for (PrintService printService : services) {
			if (printService.getName().contains(printerName)) {
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
	
	public void print() throws PrinterException {

		PrinterJob job = PrinterJob.getPrinterJob();
		PrintService ps = null;

		PrintService[] services = PrinterJob.lookupPrintServices();

		for (PrintService printService : services) {
			if (printService.getName().contains(this.printer)) {
				ps = printService;
				break;
			}
		}

		if (ps == null) {
			System.out.println("No printer found " + this.printer);
		}
		
		job.setPrintable(this.printable);
		job.setPrintService(ps);
		job.setCopies(this.numberOfCopies);
		job.print();
	}

}
