package com.jacobacon.timemanage.server.shiro;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.Key;

public class BaseDAO<T> {

	private final Class clazz;

	public BaseDAO(Class clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	public T get(String id) {
		if ((id == null) | ("".equals(id))) {
			return null;
		}

		T result = (T) ofy().load().key(Key.create(clazz, id)).now();

		return result;

	}

	public void put(T object) {
		ofy().save().entity(object).now();
	}

	@SuppressWarnings("unchecked")
	public void delete(String id) {
		ofy().delete().key(Key.create(clazz, id)).now();

	}

}
