package com.jacobacon.timemanage.server.shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.cmd.Query;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO<User> {

	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

	// Register the User Entity with Objectify
	static {
		ObjectifyService.register(User.class);
		ObjectifyService.register(UserCounter.class);
	}

	// Call the BaseDAO constructor.
	public UserDAO() {
		super(User.class);
		log.debug("User DAO Initialized.");
	}

	public User saveUser(final User user, final Boolean changeCount) {
		return ofy().transact(new Work<User>() {

			@Override
			public User run() {
				put(user);

				if (changeCount) {
					changeCount(1L);
				}

				return user;
			}

		});

	}

	public User deleteUser(final User user) {
		return ofy().transact(new Work<User>() {

			@Override
			public User run() {
				delete(user.getName());
				changeCount(-1L);
				return user;
			}

		});

	}

	public User findUser(String username) {
		return get(username);
	}

	public List<User> getUsers() {
		List<User> users;
		Query<User> query = ofy().load().type(User.class);
		users = new ArrayList<User>(query.list());
		return users;
	}
	
	public List<String> getUserList(){
		List<String> userList = new ArrayList<String>();
		Query<User> query = ofy().load().type(User.class);
		for(int i = 0; i < query.list().size(); i++){
			userList.add(query.list().get(i).getName());
		}
		return userList;
	}

	public long getCount() {
		UserCounterDAO dao = new UserCounterDAO();
		UserCounter count = dao.get(UserCounter.COUNTER_ID);
		return (count == null) ? 0 : count.getCount();
	}

	public void changeCount(final long delta) {
		UserCounterDAO dao = new UserCounterDAO();
		UserCounter count = dao.get(UserCounter.COUNTER_ID);
		if (count == null) {
			count = new UserCounter(UserCounter.COUNTER_ID);
		}

		count.changeCount(delta);
		dao.put(count);

	}

}
