package org.acs.utils.model;


/**
 * AbstractEntity.java
 */

public abstract class AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = 2035013017939483936L;
	
	/** 序列主键 */
	private long sid;
	

	public long getSid() {
		return sid;
	}


	public void setSid(long sid) {
		this.sid = sid;
	}


	@Override
	public String toString() {
		// System.out.println();
		return "";  
	}

}