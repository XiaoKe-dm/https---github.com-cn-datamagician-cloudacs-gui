package org.acs.utils.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ACSConfiguration {

	private static Properties prop;
	private static final String PROP_FILE_NAME = "acs.properties";
	static {
		prop = new Properties();
		InputStream fs;
		try {
			fs = ACSConfiguration.class.getClassLoader().getResourceAsStream(PROP_FILE_NAME); 
			prop.load(fs);
			fs.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//ebm解析是否多数据源
	public static final String ace_nbi_url = getProperty("ace.nbi.url").trim();
	
	private static String getProperty(String key) {
		return prop.getProperty(key);
	}
	
}
