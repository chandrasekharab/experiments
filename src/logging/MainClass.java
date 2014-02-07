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

package logging;

import logging.Log.LEVEL;

public class MainClass {
	public static void main(String[] args) {
		new Thread(new ThreadTest()).start();
		new Thread(new ThreadTest()).start();
	}
	
	
}
class ThreadTest implements Runnable {
	Logger logger = LogManager.getLoggerInstance();
	
	public void run() {
		while (true) {
			System.out.println("From thread " + Thread.currentThread().getId());
			logger.fatal("This testing fatal");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.debug("This testing debug");
			
			logger.log(LEVEL.ERROR, "This testing info");
		}
	}
}