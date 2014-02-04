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

import java.util.LinkedHashMap;

public class LRUCache<K, V> implements Cache<K, V> {
	/**
	 * Holds the cache
	 */
	private LinkedHashMap<K, V> cache = null;
	
	public LRUCache(int size) {
		this.cache = new LinkedHashMap<>(size); 
	}
	
	@Override
	public V get(K key) {
		V value = this.cache.get(key);
		Logger.getInstance().log("from cache ...");
		if (value == null) {
			// Query the db
			synchronized (this.cache) {
				
				value = this.cache.get(key);
				if (value == null) {
					value = (V) DBUtil.findById(key);
					if (value != null) {
						this.cache.put(key, value);
					}
				}
			}
		}
		return value;
	}

	@Override
	public V put(K key, V value) {		
		return this.cache.put(key, value);
	}

	@Override
	public V remove(K key) {
		V value = null;
		synchronized (this.cache) {
			value = this.cache.remove(key);
		}		
		return value;
	}
}
