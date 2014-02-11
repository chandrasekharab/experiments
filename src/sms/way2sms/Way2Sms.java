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

import java.net.HttpURLConnection;
import java.util.Calendar;

import com.sun.xml.internal.messaging.saaj.util.Base64;

public class Way2Sms {
	private static int responseCode = -1;
	private static String userCredentials;
	private static String cookie;
	private static String token;
	private static String site;
	private static String actionStr;
	private static String random1;
	private static String random2;
	private static String random3;
	private static Credentials credentials = new Credentials();

	public static void main(String[] args) {
		// setProxy("127.0.0.1", 8080);
		login("7799185456", Base64.base64Decode("QkNTUkFPMDg="));
		sendSMS("7799185456", "Hello Buddy...");
		// sendSMS(new String[] { "receivers mobile no. 1",
		// "receivers mobile no. 2", "..." }, "message");
		System.out.println("Message has been sent successfully!");
	}

	// Set Proxy
	private static void setProxy(String host, int port) {
		URLConnector.setProxy(host, port);
	}

	// Login
	private static void login(String uid, String pwd) {
		getSite();
		preHome();

		String location = null;

		credentials.set("username", uid);
		credentials.append("password", pwd);
		credentials.append("userLogin", "no");
		credentials.append("button", "Login");
		userCredentials = credentials.getUserCredentials();

		URLConnector.connect("http://" + site + "/w2sauth.action", false,
				"POST", cookie, userCredentials);
		responseCode = URLConnector.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_MOVED_TEMP
				&& responseCode != HttpURLConnection.HTTP_OK)
			exit("authentication failed!");
		else
			location = URLConnector.getLocation();
		URLConnector.disconnect();

		URLConnector.connect(location, false, "GET", cookie, null);
		responseCode = URLConnector.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_MOVED_TEMP
				&& responseCode != HttpURLConnection.HTTP_OK)
			exit("redirection failed!");
		URLConnector.disconnect();
	}

	// Send SMS
	private static void sendSMS(String receiversMobNo, String msg) {
		getActionString();
		credentials.reset();

		credentials.set("t_15_k_5", random1);
		credentials.append("i_m", "sn2sms");
		credentials.append("m_15_b", random2);
		if (actionStr != null)
			credentials.append("kriya", actionStr);
		else
			exit("Action string missing!");
		credentials.append(random3, "");
		credentials.append(random1, token);
		credentials.append("catnamedis", "Birthday");
		credentials.append("chkall", "on");
		credentials.append("diffNo", Calendar.getInstance().getTime()
				.toString().split(" ")[3]);
		credentials.append(random2, receiversMobNo);
		credentials.append("txtLen", "" + (140 - msg.length()));
		credentials.append("textArea", msg);

		userCredentials = credentials.getUserCredentials();
		URLConnector.connect("http://" + site + "/jsp/w2ssms.action", false,
				"POST", cookie
						+ "; 12489smssending34908=67547valdsvsikerexzc435457",
				userCredentials);
		responseCode = URLConnector.getResponseCode();

		if (responseCode != HttpURLConnection.HTTP_MOVED_TEMP
				&& responseCode != HttpURLConnection.HTTP_OK)
			exit("sendSMS failed!");
		URLConnector.disconnect();
	}

	// Send SMS (Same message to multiple users)
	private static void sendSMS(String[] receiversMobNos, String msg) {
		int noOfReceivers = receiversMobNos.length;

		for (int i = 0; i < noOfReceivers; i++)
			sendSMS(receiversMobNos[i], msg);
	}

	/*****************************
	 * UTILITY METHODS ***************************
	 **************************************************************************/

	// Get Site
	private static void getSite() {
		URLConnector.connect("http://www.way2sms.com/", false, "GET", null,
				null);
		responseCode = URLConnector.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_MOVED_TEMP
				&& responseCode != HttpURLConnection.HTTP_OK)
			exit("getSite failed!");
		else {
			site = URLConnector.getLocation();
			if (site != null)
				site = site.substring(7, site.length() - 1);
		}
		URLConnector.disconnect();
	}

	// Pre Home
	private static void preHome() {
		URLConnector.connect("http://" + site + "/content/prehome.jsp", false,
				"GET", null, null);
		responseCode = URLConnector.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_MOVED_TEMP
				&& responseCode != HttpURLConnection.HTTP_OK)
			exit("preHome failed");
		else {
			cookie = URLConnector.getCookie();
			token = cookie.substring(cookie.indexOf("~") + 1);
		}
		URLConnector.disconnect();
	}

	// Get Action String
	private static void getActionString() {
		URLConnector.connect("http://" + site + "/jsp/SingleSMS.jsp?Token="
				+ token, false, "GET", cookie, null);
		responseCode = URLConnector.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP
				|| responseCode == HttpURLConnection.HTTP_OK) {
			String str = URLConnector.getResponse();

			String aStr = "id='kriya' value='";

			int index = str.indexOf(aStr);
			if (index > 0) {
				index = index + aStr.length();

				StringBuffer actionStringChars = new StringBuffer();
				char ch = str.charAt(index);

				while (ch != '\'') {
					actionStringChars.append(ch);
					ch = str.charAt(++index);
				}

				actionStr = actionStringChars.toString();
				getRandom1(str);
				getRandom2(str);
				getRandom3(str);
			}
		} else
			exit("getActionString failed!");
		URLConnector.disconnect();
	}

	// Random 1
	private static void getRandom1(String str) {
		String aStr = "id='t_15_k_5'";

		int index = str.indexOf(aStr);
		if (index > 0) {
			index = index + aStr.length();

			StringBuffer random1Chars = new StringBuffer();
			char ch = str.charAt(index);

			if (ch == '>') {
				ch = str.charAt(++index);

				while (ch != '<') {
					random1Chars.append(ch);
					ch = str.charAt(++index);
				}
			} else {
				index += 8;
				ch = str.charAt(index);

				while (ch != '\'') {
					random1Chars.append(ch);
					ch = str.charAt(++index);
				}
			}

			random1 = random1Chars.toString();
		}
	}

	// Random 2
	private static void getRandom2(String str) {
		String aStr = "id='m_15_b'";

		int index = str.indexOf(aStr);
		if (index > 0) {
			index = index + aStr.length();

			StringBuffer random2Chars = new StringBuffer();
			char ch = str.charAt(index);

			if (ch == '>') {
				ch = str.charAt(++index);

				while (ch != '<') {
					random2Chars.append(ch);
					ch = str.charAt(++index);
				}
			} else {
				index += 8;
				ch = str.charAt(index);

				while (ch != '\'') {
					random2Chars.append(ch);
					ch = str.charAt(++index);
				}
			}

			random2 = random2Chars.toString();
		}
	}

	// Random 3
	private static void getRandom3(String str) {
		String aStr = "dnipb";

		int index = str.lastIndexOf(aStr);
		if (index > 0) {

			index -= 40;

			StringBuffer random3Chars = new StringBuffer();
			char ch = str.charAt(index);

			while (ch != '\"') {
				random3Chars.append(ch);
				ch = str.charAt(--index);
			}

			random3 = random3Chars.reverse().toString();
		}
	}

	// Exit
	private static void exit(String errorMsg) {
		System.err.println(errorMsg);
		System.exit(1);
	}
}