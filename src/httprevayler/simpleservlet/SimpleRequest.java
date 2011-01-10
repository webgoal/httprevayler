package httprevayler.simpleservlet;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SimpleRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String _requestURI;
	private StringBuffer _requestURL;
	private String _method;
	private Map<String, String[]> _parameterMap;
	private Cookie[] _cookies;


	public SimpleRequest(HttpServletRequest request) {
		_requestURI = request.getRequestURI();
		_requestURL = request.getRequestURL();
		_method = request.getMethod();
		_parameterMap = request.getParameterMap();
		_cookies = request.getCookies();
		
		/*Enumeration<String> parameterNames = request.getParameterNames();
		System.out.println("Params:");
		while (parameterNames.hasMoreElements()) 
			System.out.println(parameterNames.nextElement());/**/
	}

	public String getRequestURI() {
		return _requestURI;
	}
	
	public StringBuffer getRequestURL() {
		return _requestURL;
	}
	
	public String getMethod() {
		return _method;
	}
	
	public String getParameter(String parameterName) {
		if (_parameterMap == null || !containsParameter(parameterName))
			return null;
		
		String[] parameter = _parameterMap.get(parameterName);
		return parameter[0];
	}

	public boolean containsParameter(String parameterName) {
		return _parameterMap.containsKey(parameterName);
	}

	public String getCookieValue(String name) {
		try {
			for (Cookie cookie : _cookies)
				if (cookie.getName().equals(name)) {
					System.out.println(URLDecoder.decode(cookie.getValue(), "UTF-8"));
					return URLDecoder.decode(cookie.getValue(), "UTF-8");
				}
		} catch (UnsupportedEncodingException e) { }
		return null;
	}
	
	public Cookie[] getCookies() {
		return _cookies;
	}
	
}
