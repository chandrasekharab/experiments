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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

public class URLConnector {
	private static HttpURLConnection connection;
	private static Proxy proxy;

	// Set Proxy
	public static void setProxy(String host, int port) {
		proxy = new Proxy(Proxy.Type.HTTP,
				java.net.InetSocketAddress.createUnresolved(host, port));
	}

	// Connect
	public static void connect(String urlPath, boolean redirect, String method,
			String cookie, String credentials) {
		try {
			URL url = new URL(urlPath);

			if (null != proxy)
				connection = (HttpURLConnection) url.openConnection(proxy);
			else
				connection = (HttpURLConnection) url.openConnection();

			connection.setInstanceFollowRedirects(redirect);

			if (cookie != null)
				connection.setRequestProperty("Cookie", cookie);

			if (method != null && method.equalsIgnoreCase("POST")) {
				connection.setRequestMethod(method);
				connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
			}

			connection
					.setRequestProperty("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:10.0.4) Gecko/20100101 Firefox/10.0.4");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			if (credentials != null) {
				DataOutputStream wr = new DataOutputStream(
						connection.getOutputStream());
				wr.writeBytes(credentials);
				wr.flush();
				wr.close();
			}
		} catch (Exception exception) {
			System.out.println("Connection error");
		}
	}

	// Get Cookie
	public static String getCookie() {
		String cookie = null;

		if (connection != null) {
			String headerName = null;

			for (int i = 1; (headerName = connection.getHeaderFieldKey(i)) != null; i++) {
				if (headerName.equals("Set-Cookie")) {
					cookie = connection.getHeaderField(i).split(";")[0];
					break;
				}
			}
		}

		return cookie;
	}

	// Get Location
	public static String getLocation() {
		String location = null;

		if (connection != null) {
			String headerName = null;

			for (int i = 1; (headerName = connection.getHeaderFieldKey(i)) != null; i++) {
				if (headerName.equals("Location")) {
					location = connection.getHeaderField(i).split(";")[0];
					break;
				}
			}
		}

		return location;
	}

	// Get Response Code
	public static int getResponseCode() {
		int responseCode = -1;

		if (connection != null) {
			try {
				responseCode = connection.getResponseCode();
			} catch (Exception exception) {
				System.err.println("Response code error");
			}
		}

		return responseCode;
	}

	// Get Response
	public static String getResponse() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		if (connection != null) {
			try {
				InputStream is = connection.getInputStream();

				int next = is.read();

				while (next > -1) {
					bos.write(next);
					next = is.read();
				}

				bos.flush();
			} catch (Exception exception) {
				System.err.println("Response error");
			}
		}

		return new String(bos.toByteArray());
	}

	// Get Error Message
	public static String getErrorMessage() {
		StringBuilder errorMessage = new StringBuilder();

		if (connection != null) {
			try {
				InputStream es = connection.getErrorStream();
				BufferedReader rd = new BufferedReader(
						new InputStreamReader(es));

				String line;
				while ((line = rd.readLine()) != null) {
					errorMessage.append(line);
					errorMessage.append("\r\n");
				}

				rd.close();
			} catch (Exception exception) {
				System.err.println("Error in getting error message");
			}
		}

		return errorMessage.toString();
	}

	// Disconnect
	public static void disconnect() {
		if (connection != null)
			connection.disconnect();
	}
}
