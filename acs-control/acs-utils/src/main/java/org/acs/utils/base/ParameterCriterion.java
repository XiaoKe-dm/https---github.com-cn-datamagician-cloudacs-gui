package org.acs.utils.base;

public class ParameterCriterion {

	public final static String EQ = "eq";
	public final static String ALLEQ = "allEq";
	public final static String GT = "gt";
	public final static String GE = "ge";
	public final static String LT = "lt";
	public final static String LE = "le";
	public final static String BETWEEN = "between";
	public final static String LIKE = "like";
	public final static String IN = "in";
	public final static String AND = "and";
	public final static String OR = "or";
	public final static String ASC = "asc";
	public final static String DESC = "desc";
	private String name;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
