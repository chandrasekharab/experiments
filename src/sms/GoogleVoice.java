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

package sms;


import java.io.IOException;

import com.sun.xml.internal.messaging.saaj.util.Base64;
import com.techventus.server.voice.Voice;

public class GoogleVoice {
	public static void main(String[] args) throws IOException {
		Voice voice = new Voice("", "");
		voice.login();
		voice.sendSMS("7799185456", "Hello Wolrd");		
	}

}
