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

import com.mongodb.DBObject;

public class MainClass {
	public static void main(String[] args) {
		
		final LRUCache<Object, Object> cache = new LRUCache<>(12);
		DBObject dbo = (DBObject)cache.get("52b42f411a54f195b2930e42"); 
		int i=0;
		while (i<10) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					cache.remove("52b42f411a54f195b2930e42");
					System.out.println(cache.get("52b42f411a54f195b2930e42"));
					System.out.println("Thread : " + Thread.currentThread().getId());
					
				}
			});
			
			t.start();
			i++;
		}
		
		
	}

}
