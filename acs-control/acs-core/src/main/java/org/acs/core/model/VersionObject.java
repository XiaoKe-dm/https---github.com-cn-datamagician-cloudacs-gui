package org.acs.core.model;

import java.util.Date;

public class VersionObject {

	private boolean _writable;
	private String _value;
	private String _type;
	public boolean is_writable() {
		return _writable;
	}
	public void set_writable(boolean _writable) {
		this._writable = _writable;
	}
	public String get_value() {
		return _value;
	}
	public void set_value(String _value) {
		this._value = _value;
	}
	public String get_type() {
		return _type;
	}
	public void set_type(String _type) {
		this._type = _type;
	}
	
	
}
