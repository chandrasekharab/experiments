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

public interface Logger {

	/**
	 * 
	 * @param error
	 */
	public void error(Object error);
	
	/**
	 * 
	 * @param info
	 */
	public void info(Object info);
	
	/**
	 * 
	 * @param debug
	 */
	public void debug(Object debug);
	
	/**
	 * 
	 * @param fatal
	 */
	public void fatal(Object fatal);
	
	/**
	 * 
	 * @param level
	 * @param message
	 */
	public void log(LEVEL level, Object message);

}
