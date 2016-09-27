package com.jacobacon.timemanage.server.shiro;

import java.io.Serializable;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/*
 * 
 * The Actual User Object that is Stored in the Objectify DataStore.
 * 
 */

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = -7752278427631783068L;

	private static final Logger log = LoggerFactory.getLogger(User.class);

	@Id
	private String name;

	private String passwordHash;

	private Set<String> roles;

	private Set<String> permissions;

	public User() {

	}

}
