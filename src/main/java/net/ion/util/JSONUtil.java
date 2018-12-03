package net.ion.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {

	public static String getString(JSONObject params, String key) {
        if(params == null || params.isEmpty())
        	return null;
      	JSONArray arr = params.getJSONArray(key);
      	return arr.getString(0);
	}

	public static void clear(Object obj) {
	
		if (obj instanceof net.sf.json.JSONObject) {
				((net.sf.json.JSONObject) obj).clear();
    }

		if (obj instanceof net.sf.json.JSONArray) {
			((net.sf.json.JSONArray) obj).clear();
		}

		if (obj instanceof org.json.JSONObject) {
      while(((org.json.JSONObject) obj).length()>0)
        ((org.json.JSONObject)obj).remove((String) ((org.json.JSONObject)obj).keys().next());
    }
    
		if (obj instanceof org.json.JSONArray) {
      for(int i=0; i<((org.json.JSONArray) obj).length(); i++) {
        ((org.json.JSONArray) obj).remove(i);
      }
		}

	}

	// ------------------------------------------------------------------------
	// classpath:파일과 절대경로 파일을 모두 지원한다.
	// ------------------------------------------------------------------------
	public static org.json.JSONObject toJSON(String filepath) throws Exception {
		final String CLASSPATH = "classpath:";
		if (StringUtils.startsWith(filepath, CLASSPATH)) {
			String path = StringUtils.replace(filepath, CLASSPATH, "");
			InputStream is = null;
			if ((is = JSONUtil.class.getResourceAsStream(path)) == null)
				return null;
			return new org.json.JSONObject(IOUtils.toString(is, "UTF-8"));
		}
		FileInputStream fis = new FileInputStream(new File(filepath));
		String str = IOUtils.toString(fis, "UTF-8");
		return new org.json.JSONObject(str);
	}

}
