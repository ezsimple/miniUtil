package net.ion.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ObjectUtils;

public class StringUtil {
	public static String trim(String str) {
		return StringUtils.trim(str);
	}
	
	public boolean isEmptyArray(String [] array){
		return ArrayUtils.isEmpty(array);
	}	
	
	public boolean isEmpty(String[] array) {
		return ListUtil.isEmpty(array);
	}

	public static String escape(String str) {
		return StringEscapeUtils.escapeXml(str);
	}

	public static String unescape(String str) {
		return StringEscapeUtils.unescapeXml(str);
	}
	
	// null => ""
	public static String trimToEmpty(Object obj) {
		if(ObjectUtils.isEmpty(obj))
			return "";

		String str = String.valueOf(obj);
		if(StringUtils.isEmpty(str))
			return "";

		str = StringUtils.trimToEmpty(String.valueOf(obj));
		if(StringUtils.isEmpty(str)) 
			return "";

		return str;
	}
	
	// 디버깅용
	// Obect 객체를, String 형태로 출력해 준다.
	// 예 : l2u.cmm.usr.dom.User@759bc41f 형태를 
	// ==> 이렇게 표시해준다. l2u.cmm.usr.dom.User@759bc41f[username=devel1,pasㅏsword=$2a$10$s2eqytAKIW/xen4ZmoaoVerzQjpNzldcPrgk0bwq5b3JcacsU7tK6,name=개발자1,isAccountNonExpired=true,isAccountNonLocked=true,isCredentialsNonExpired=true,isEnabled=true,authorities=<null>,email=devel1@link2us.co.kr,phone=00000000001,regdate=Sun Nov 18 20:31:17 EST 2018,asgndate=Sun Nov 18 20:31:17 EST 2018,moddate=Sun Nov 18 20:31:17 EST 2018,pchgdate=Sun Nov 18 20:21:17 EST 2018,isDel=false]
	public static String toString(Object object) {
		return ObjectUtil.toString(object);
	}
}