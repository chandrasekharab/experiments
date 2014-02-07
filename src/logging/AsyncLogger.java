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

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import logging.Log.LEVEL;

public class AsyncLogger implements Logger, Runnable{

	private Queue<Log> messages = new ConcurrentLinkedQueue<>();
	private Appender appender;
	
	public AsyncLogger() {
		this.appender = new ConsoleAppender();
	}
	
	public AsyncLogger(Appender appender) {
		this.appender = appender;
	}
	
	@Override
	public void error(Object aError) {
		messages.add(new Log(LEVEL.ERROR, aError));
	}

	@Override
	public void info(Object aInfo) {
		messages.add(new Log(LEVEL.INFO, aInfo));
	}

	@Override
	public void debug(Object aDebug) {
		messages.add(new Log(LEVEL.DEBUG, aDebug));
		
	}

	@Override
	public void fatal(Object aFatal) {
		messages.add(new Log(LEVEL.FATAL, aFatal));
		
	}

	@Override
	public void log(LEVEL aLevel, Object aMessage) {
		messages.add(new Log(aLevel, aMessage));
		
	}

	@Override
	public void run() {
		while (true) {
			if (!this.messages.isEmpty()) {
				Log log = this.messages.remove();
				this.appender.write(log);
			}
		}
		
	}
	
}
