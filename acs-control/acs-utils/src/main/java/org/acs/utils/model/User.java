package org.acs.utils.model;

public class User extends AbstractEntity {

	public static final String SESSION_USERNAME = "USER";
	public static final String LOGIN_MEG = "msg";
	public static final String UNSUCCESS_INFO = "unsuccess";
	public static final String NOSESSION_INFO = "nosession";
	public static final String DEFAULT_PASSWORD = "123456";
	private String username;
	private String password;
	private boolean manager;
	private String oper;
	private long operId;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isManager() {
		return manager;
	}
	public void setManager(boolean manager) {
		this.manager = manager;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public long getOperId() {
		return operId;
	}
	public void setOperId(long operId) {
		this.operId = operId;
	}
	
}
