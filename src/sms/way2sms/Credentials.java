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

package sms.way2sms;

import java.net.URLEncoder;
import java.util.ArrayList;

public class Credentials {
	private ArrayList<String> list = new ArrayList<String>();

	// Set
	public void set(String name, String value) {
		StringBuilder buffer = new StringBuilder();

		buffer.append(name);
		buffer.append("=");
		buffer.append(getUTF8String(value));

		add(buffer.toString());
	}

	// Append
	public void append(String name, String value) {
		StringBuilder buffer = new StringBuilder();

		buffer.append("&");
		buffer.append(name);
		buffer.append("=");
		buffer.append(getUTF8String(value));

		add(buffer.toString());
	}

	// Add
	private void add(String item) {
		list.add(item);
	}

	// Get UTF8 String
	private String getUTF8String(String value) {
		String encodedValue = null;

		try {
			encodedValue = URLEncoder.encode(value, "UTF-8");
		} catch (Exception exception) {
			System.err.println("Encoding error! Please try agian...");
			System.exit(1);
		}

		return encodedValue;
	}

	// Is Empty
	public boolean isEmpty() {
		return list.isEmpty();
	}

	// Reset
	public void reset() {
		list.clear();
	}

	// Get User Credentials
	public String getUserCredentials() {
		StringBuilder buffer = new StringBuilder();
		int size = list.size();

		for (int i = 0; i < size; i++)
			buffer.append(list.get(i));

		return buffer.toString();
	}
}
