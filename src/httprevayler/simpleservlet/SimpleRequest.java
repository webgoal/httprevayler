package httprevayler.simpleservlet;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class SimpleRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String _requestURI;
	private String _method;
	private Map<String, String[]> _parameterMap;

	private StringBuffer _requestURL;

	public SimpleRequest(HttpServletRequest request) {
		_requestURI = request.getRequestURI();
		_requestURL = request.getRequestURL();
		_method = request.getMethod();
		_parameterMap = request.getParameterMap();
		
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
	
}
