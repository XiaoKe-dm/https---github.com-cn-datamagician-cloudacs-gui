package org.acs.utils.tools;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtils {
	
	public static boolean IsNumber(String value) {
		if (value == null || "".equals(value)) {
			return false;
		}
		//Pattern pattern = Pattern.compile("^[0-9]\\d*(\\.?)\\d*$");
		Pattern pattern = Pattern.compile("^-?[0-9]+\\.?\\d*$");
        Matcher isNum = pattern.matcher(value); 
		return isNum.matches();
	}
	
	public static boolean IsObjNumber(Object value) {
		if (value == null || "".equals(value.toString())) {
			return false;
		}
		//Pattern pattern = Pattern.compile("^[0-9]\\d*$"); 
		Pattern pattern = Pattern.compile("^-?[0-9]+\\.?\\d*$"); 
        Matcher isNum = pattern.matcher(value.toString()); 
		return isNum.matches();
	}
	
	public static Byte convertToByte(String str) {
		Byte ret = null;
		if (IsNumber(str)) {
			try {
				ret = Byte.parseByte(str);
			} catch (NumberFormatException e) {
			}
		}
		return ret;
	}
	
	public static Integer convertToInt(String str) {
		Integer ret = null;
		if (IsNumber(str)) {
			try {
				ret = Integer.parseInt(str);
			} catch (NumberFormatException e) {
			}
		}
		return ret;
	}
	
	public static Long convertToLong(String str) {
		Long ret = null;
		if (IsNumber(str)) {
			try {
				ret = Long.parseLong(str);
			} catch (NumberFormatException e) {
			}
		}
		return ret;
	}
	
	public static Float convertToFloat(String str) {
		Float ret = null;
		try {
			ret = Float.parseFloat(str);
		} catch (NumberFormatException e) {
		}
		return ret;
	}
	
	public static Double convertToDouble(String str) {
		Double ret = null;
		try {
			ret = Double.parseDouble(str);
		} catch (NumberFormatException e) {
		}
		return ret;
	}
	
	public static int nullToZero(Integer value) {
		if (value == null) {
			return 0;
		}
		return value.intValue();
	}
	
	public static byte nullToZero(Byte value) {
		if (value == null) {
			return 0;
		}
		return value.byteValue();
	}
	
	public static double nullToZero(Double value) {
		if (value == null) {
			return 0;
		}
		return value.doubleValue();
	}
	
	public static float nullToZero(Float value) {
		if (value == null) {
			return 0;
		}
		return value.floatValue();
	}
	
	public static long nullToZero(Long value) {
		if (value == null) {
			return 0;
		}
		return value.longValue();
	}
	
	public static short nullToZero(Short value) {
		if (value == null) {
			return 0;
		}
		return value.shortValue();
	}
	
	public static String keepTwoNum(double sourceNum){
		DecimalFormat df = new DecimalFormat("#0.00");   
		return df.format(sourceNum); 
	}
}
