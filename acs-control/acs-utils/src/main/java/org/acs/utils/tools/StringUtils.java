package org.acs.utils.tools;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
    /**
     * Returns <code>true</code> if str is empty
     * 
     * @param str
     * @return result
     */
    public static boolean isEmpty(String str) {
    	return str == null || str.trim().equals("");
    }
    
    /**
     * Returns <code>true</code> if all elements is empty
     * 
     * @param str
     * @return result
     */
    public static boolean isEmpty(String[] str) {
		if (str == null || str.length == 0) {
		    return true;
		}
		for (int i = 0; i < str.length; i++) {
		    if (!isEmpty(str[i]))
			return false;
		}
		return true;
    }
    
    public static String valueOf(Object o) {
    	if (o == null) return "";
    	if (o instanceof String) {
    		return o.toString().trim();
    	} else if (o instanceof Number) {
    		return String.valueOf(o);
    	} 
    	else {
    		return "";
    	}
    }
    
    public static String buildHtmlEnter(String... elements) {
    	StringBuilder stringBuffer = new StringBuilder(70);
    	stringBuffer.append("<html>");
    	for (int i = 0; i < elements.length; i++) {
    		if (i == 0) {
    			stringBuffer.append(elements[i]);
    		} else {
    			stringBuffer.append("<br>").append(elements[i]);
    		}
		}
    	stringBuffer.append("</html>");
    	return stringBuffer.toString();
    }
    
    public static String buildHtmlEnter(String head, List<String> list) {
    	StringBuilder stringBuffer = new StringBuilder();
    	stringBuffer.append("<html>");
    	stringBuffer.append(head);
    	for (String str : list) {
    		stringBuffer.append("<br>").append(str);
    	}
    	stringBuffer.append("</html>");
    	return stringBuffer.toString();
    }
    
    /**
     * xml encode
     * @param text
     * @return
     */
    public static String xmlEncode(String text) {
    	int length = text.length();
    	if (text != null && length > 0) {
    	    StringBuffer ret = new StringBuffer(length * 12 / 10);

    	    int last = 0;
    	    for (int i = 0; i < length; i++) {
    		char c = text.charAt(i);
    		switch (c) {
	    		//case ' ' : ret.append("&nbsp;"); break;
	    		case '&':
	    		    if (last < i) {
	    		    	ret.append(text.substring(last, i));
	    		    }
	    		    last = i + 1;
	
	    		    ret.append("&amp;");
	    		    break;
	    		case '>':
	    		    if (last < i) {
	    		    	ret.append(text.substring(last, i));
	    		    }
	    		    last = i + 1;
	
	    		    ret.append("&gt;");
	    		    break;
	    		case '<':
	    		    if (last < i) {
	    		    	ret.append(text.substring(last, i));
	    		    }
	    		    last = i + 1;
	
	    		    ret.append("&lt;");
	    		    break;
	    		case '\"':
	    		    if (last < i) {
	    		    	ret.append(text.substring(last, i));
	    		    }
	    		    last = i + 1;
	
	    		    ret.append("&quot;");
	    		    break;
	    		case '\'':
	    		    if (last < i) {
	    		    	ret.append(text.substring(last, i));
	    		    }
	    		    last = i + 1;
	
	    		    ret.append("&apos;");
	    		    break;
	
	    		default:
	    		    break;
	    		}
    	    }

    	    if (last < length) {
    	    	ret.append(text.substring(last));
    	    }

    	    return ret.toString();
    	}

    	return text;
	}
    
    /**
     * Concatenate string elements
     * 
     *  @param elements
     *  list of elements.
     *  @return the string.
     *  
     **/
    public static String buildString(String... elements) {
		StringBuilder stringBuffer = new StringBuilder(70);
		for (int i = 0; i < elements.length; i++) {
		    stringBuffer.append(elements[i]);
		}
		return stringBuffer.toString();
    }
    
    public static boolean isDottedIPv4(String ip) {
    	if (ip == null || "".equals(ip)) {
			return false;
		}
    	Pattern pattern = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)$"); 
        Matcher match = pattern.matcher(ip);
		return match.matches() && 
				Integer.valueOf(match.group(1)) <= 255 && 
				Integer.valueOf(match.group(2)) <= 255 && 
				Integer.valueOf(match.group(3)) <= 255 && 
				Integer.valueOf(match.group(4)) <= 255;
    }
}
