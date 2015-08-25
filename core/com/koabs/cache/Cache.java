package com.koabs.cache;

import java.util.concurrent.locks.ReadWriteLock;

import org.apache.ibatis.cache.CacheKey;

public interface Cache {
	/**
	   * @return The identifier of this cache
	   */
	  String getId();

	  /**
	   * @param key Can be any object but usually it is a {@link CacheKey}
	   * @param value The result of a select.
	   */
	  void putObject(Object key, Object value);

	  /**
	   * @param key The key
	   * @return The object stored in the cache.
	   */
	  Object getObject(Object key);

	  /**
	   * As of 3.3.0 this method is only called during a rollback 
	   * for any previous value that was missing in the cache.
	   * This lets any blocking cache to release the lock that 
	   * may have previously put on the key.
	   * A blocking cache puts a lock when a value is null 
	   * and releases it when the value is back again.
	   * This way other threads will wait for the value to be 
	   * available instead of hitting the database.
	   *
	   * 
	   * @param key The key
	   * @return Not used
	   */
	  Object removeObject(Object key);

	  /**
	   * Clears this cache instance
	   */  
	  void clear();

	  /**
	   * Optional. This method is not called by the core.
	   * 
	   * @return The number of elements stored in the cache (not its capacity).
	   */
	  int getSize();
	  
	  /** 
	   * Optional. As of 3.2.6 this method is no longer called by the core.
	   *  
	   * Any locking needed by the cache must be provided internally by the cache provider.
	   * 
	   * @return A ReadWriteLock 
	   */
	  ReadWriteLock getReadWriteLock();
}
