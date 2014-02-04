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

package cache;
public class Logger {
	
	private static Logger logger = new Logger();
	
	public void log(String message) {
		System.out.println(message);
	}
	
	public static Logger getInstance() {
		return logger;
	}
}
