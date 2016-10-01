package com.jacobacon.timemanage.server.shiro;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;
import com.google.gwt.thirdparty.guava.common.base.Preconditions;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/*
 * 
 * The Actual User Object that is Stored in the Objectify DataStore.
 * 
 */

@Cache
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = -7752278427631783068L;

	private static final Logger log = LoggerFactory.getLogger(User.class);

	@Id
	private String name;

	private String passwordHash;

	private byte[] salt;

	private Set<String> roles;

	private Set<String> permissions;

	@Index
	private Date dateRegistered;

	private Boolean isDisabled;

	public User() {
		this.roles = new HashSet<String>();
		this.permissions = new HashSet<String>();
	}

	public User(String name) {
		this(name, null, new HashSet<String>(), new HashSet<String>());
	}

	public User(String name, String password) {
		this(name, password, new HashSet<String>(), new HashSet<String>());

	}

	public User(String name, Set<String> roles, Set<String> permissions) {
		this(name, null, roles, permissions);

	}

	public User(String name, String password, Set<String> roles, Set<String> permissions) {
		this.name = name;
		this.salt = salt().getBytes();
		this.passwordHash = hash(password, salt);
		this.roles = Collections.unmodifiableSet(roles);
		this.permissions = Collections.unmodifiableSet(permissions);
		this.dateRegistered = new Date();

	}

	public String getName() {
		return name;
	}
	
	public void setPassword(String password){
		Preconditions.checkNotNull(password);
		this.salt = salt().getBytes();
		this.passwordHash = hash(password, salt);
	}
	
	private static ByteSource salt(){
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		return rng.nextBytes();
	}
	
	
	private static String hash(String password, byte[] salt){
		return (password == null) ? null : new Sha256Hash(password, new SimpleByteSource(salt), 1).toHex(); 
	}
	
    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User u = (User)o;
            return Objects.equal(getName(), u.getName()) &&
                   Objects.equal(getPasswordHash(), u.getPasswordHash());
        } else {
            return false;
        }
    }

	public Boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public byte[] getSalt() {
		return salt;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public Date getDateRegistered() {
		return dateRegistered;
	}
    
    @Override
    public int hashCode() {
        return Objects.hashCode(name, passwordHash);
    }

}
