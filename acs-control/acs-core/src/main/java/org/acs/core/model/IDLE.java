package org.acs.core.model;

import java.util.Date;

public class IDLE {

	private boolean _object;
	private boolean _writable;
	private Date _timestamp;
	private VersionObject Enable;
	private VersionObject WaitTime;
	public boolean is_object() {
		return _object;
	}
	public void set_object(boolean _object) {
		this._object = _object;
	}
	public boolean is_writable() {
		return _writable;
	}
	public void set_writable(boolean _writable) {
		this._writable = _writable;
	}
	public Date get_timestamp() {
		return _timestamp;
	}
	public void set_timestamp(Date _timestamp) {
		this._timestamp = _timestamp;
	}
	public VersionObject getEnable() {
		return Enable;
	}
	public void setEnable(VersionObject enable) {
		Enable = enable;
	}
	public VersionObject getWaitTime() {
		return WaitTime;
	}
	public void setWaitTime(VersionObject waitTime) {
		WaitTime = waitTime;
	}
	
	
}
