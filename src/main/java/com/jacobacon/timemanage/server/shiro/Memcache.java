package com.jacobacon.timemanage.server.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import com.google.appengine.api.memcache.AsyncMemcacheService;
import com.google.appengine.api.memcache.Expiration;
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

	@SuppressWarnings("unchecked")
	@Override
	public V get(K k) throws CacheException {
		try {
			return (V) memcacheService.get(wrap(k)).get();

		} catch (InterruptedException caught) {
			return null;
		} catch (ExecutionException caught) {
			return null;
		}
	}

	@Override
	public Set<K> keys() {

		return new HashSet<K>();
	}

	@Override
	public V put(K k, V v) throws CacheException {
		memcacheService.put(wrap(k), v, Expiration.byDeltaSeconds(EXPIRES));
		return null;
	}

	public V putSync(K k, V v) throws CacheException {
		try {
			memcacheService.put(wrap(k), v, Expiration.byDeltaSeconds(EXPIRES)).get();
			return null;
		} catch (InterruptedException caught) {
			return null;
		} catch (ExecutionException caught) {
			return null;
		}

	}

	@Override
	public V remove(K k) throws CacheException {
		memcacheService.delete(wrap(k));
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Collection<V> values() {

		return new HashSet<V>();
	}

	private Wrap wrap(K k) {
		return new Wrap<K>(name, k);
	}

	private static class Wrap<K> implements Serializable {

		private String name;
		private K wrapped;

		Wrap(String name, K wrapped) {
			this.name = name;
			this.wrapped = wrapped;
		}
	}

}
