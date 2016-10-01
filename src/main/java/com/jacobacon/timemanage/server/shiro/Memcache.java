package com.jacobacon.timemanage.server.shiro;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import com.google.appengine.api.memcache.AsyncMemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class Memcache<K, V> implements Cache<K, V> {
	
	private static final int EXPIRES = 300;
	
	private final String name;
	private final AsyncMemcacheService memcacheService;
	
	public Memcache(String name) {
		this.name = name;
		this.memcacheService = MemcacheServiceFactory.getAsyncMemcacheService();
	}

	@Override
	public void clear() throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V get(K k) throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K arg0, V arg1) throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K arg0) throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
