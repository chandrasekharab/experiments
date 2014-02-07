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
public class Log {	
	private LEVEL level;
	private Object message;
	
	public enum LEVEL {
		ERROR, INFO, DEBUG, FATAL
	}
	
	public Log(LEVEL level) {
		this.level = level;
	}
	
	public Log(LEVEL level, Object message) {
		this.level = level;
		this.message = message;
	}
	
	public void setMessage(Object message) {
		this.message = message;
	}
	
	public Object getMessage() {
		return this.message;
	}
	
	@Override
	public String toString() {
		return this.level.name() + " : " + (String)this.message;
	}
}
