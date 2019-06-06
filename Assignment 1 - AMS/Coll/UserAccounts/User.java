package coll.UserAccounts;

import java.util.ArrayList;

public class User {

	/**
	 * Constructs a user with a given username and password. If a user is
	 * successfully constructed, their username is added to the list of usernames.
	 *
	 * @param username
	 * @param password
	 * @throws Exception.
	 *             Throws a UserException if the username or password is invalid
	 *             (use the below methods badUsername and badPassword to assist you
	 *             with this).
	 */

	static ArrayList<String> usernames = new ArrayList<String>();
	String password;
	String username;
	public User(String username, String password) throws Exception {
		this.username = username;
		this.password = password;
		if(badUsername(this.username)) {
			throw new UserException();
		} else if(!badUsername(this.username)) {
			usernames.add(username);
		}

		if(badPassword(this.password)) {
			throw new UserException();
		} else if(!badPassword(this.password)) {
			this.password = password;
		}
	}

	/**
	 * Gets the username of the user.
	 *
	 * @return
	 */
	public String getUsername() {
		for (int i = 0; i < usernames.size(); i++)
	    {
			String person = usernames.get(i);
	        if (username == person)
	        {
	            return usernames.get(i);
	        }
	    }
		return null;

	}

	/**
	 * Gets the password of the user.
	 *
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Checks if a given username is invalid
	 *
	 * @param username
	 * @return true if the username already exist, false otherwise.
	 */
	public static boolean badUsername(String username) {
		for (int i = 0; i < usernames.size(); i++)
	    {
			String duplicate = usernames.get(i);
	        if (username == duplicate)
	        {
	            return true;
	        }
	    }
		return false;
	}

	/**
	 * Checks if a given password is invalid.
	 *
	 * @param password
	 * @return true if the password is fewer than 8 characters long, false
	 *         otherwise.
	 */
	public static boolean badPassword(String password) {
		if(password.length() < 8) {
			return true;
		}
		return false;
	}

}
