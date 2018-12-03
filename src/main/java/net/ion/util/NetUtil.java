package net.ion.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NetUtil {
	
	public static String getMAC() throws UnknownHostException, SocketException {
		InetAddress ip = InetAddress.getLocalHost();
		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
		byte[] mac = network.getHardwareAddress();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
		    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : "").toUpperCase());
		}
		String macAddress = sb.toString();
		log.debug("IP : {}, MAC : {}",ip.getHostAddress(), macAddress);
		return macAddress;
	}

	// http://localhost:8100 => return "localhost"
	public static String getDomain(ServletRequest request) {
		String host = request.getServerName();
		return host;
	}
	
	public static String getHost(ServletRequest request) {
		String host = request.getServerName();
		int port = request.getServerPort();
		String scheme = request.getScheme();
		ServletContext ctx = request.getServletContext();
		String _ctx = ctx.getContextPath();
		String hostname = scheme+"://"+host+":"+port+"/"+_ctx;
		return hostname;
	}

	public static String getHost() {
		HttpServletRequest request = getHttpServletRequest();
		String host = request.getServerName();
		int port = request.getServerPort();
		String scheme = request.getScheme();
		ServletContext ctx = request.getServletContext();
		String _ctx = ctx.getContextPath();
		String hostname = scheme+"://"+host+":"+port+"/"+_ctx;
		return hostname;
	}
	
	public static String getPagename() {
		HttpServletRequest req = getHttpServletRequest();
		String uri = req.getRequestURI();
		log.debug("uri : {}", uri);
		if(StringUtils.isEmpty(uri)) {
			log.warn("uri is empty");
			return "";
		}
		String pageName = String.valueOf(uri.subSequence(uri.lastIndexOf("/")+1, uri.lastIndexOf('.')));
		if(StringUtils.isEmpty(pageName)) {
			log.warn("pageName is empty");
			return "";
		}
		return pageName;
					
	}
	
	public static String getClientIP() {
		HttpServletRequest req = getHttpServletRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		log.info("TEST : X-FORWARDED-FOR : "+ip);
		if (ip == null) {
		ip = req.getHeader("Proxy-Client-IP");
			log.info("TEST : Proxy-Client-IP : "+ip);
		}
		if (ip == null) {
			ip = req.getHeader("WL-Proxy-Client-IP");
			log.info("TEST : WL-Proxy-Client-IP : "+ip);
		}
		if (ip == null) {
			ip = req.getHeader("HTTP_CLIENT_IP");
			log.info("TEST : HTTP_CLIENT_IP : "+ip);
		}
		if (ip == null) {
			ip = req.getHeader("HTTP_X_FORWARDED_FOR");
			log.info("TEST : HTTP_X_FORWARDED_FOR : "+ip);
		}
		if (ip == null) {
			ip = req.getRemoteAddr();
			log.info("TEST : getRemoteAddr : "+ip);
		}
        return ip;
	}
	
	public static HttpServletRequest getHttpServletRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	@Test
	public void Test() throws UnknownHostException, SocketException {
		getMAC();
	}

}
