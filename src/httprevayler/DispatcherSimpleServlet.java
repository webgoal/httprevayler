package httprevayler;

import httprevayler.simpleservlet.SimpleRequest;
import httprevayler.simpleservlet.SimpleResponse;
import httprevayler.simpleservlet.SimpleServlet;

import org.eclipse.jetty.server.Response;

import sneer.bricks.hardware.io.log.Logger;
import sneer.bricks.hardware.io.log.exceptions.ExceptionLogger;
import static sneer.foundation.environments.Environments.my;

public class DispatcherSimpleServlet implements SimpleServlet {

	private final String _resourcesPackage;
	private String _baseUri = "/api/";

	DispatcherSimpleServlet(String resourcesPackage) {
		_resourcesPackage = resourcesPackage + ".";
	}

	@Override
	public void service(SimpleRequest simpleRequest, SimpleResponse simpleResponse)
			throws Exception {
		
		String requestURI = afterBaseUri(simpleRequest.getRequestURI());
		my(Logger.class).log(simpleRequest.getMethod() + ": " + requestURI);
				
		String resourceClassName = resourceClassNameFor(requestURI);
		
		try {
			BaseResource resource = (BaseResource) Class.forName(resourceClassName).newInstance();
			resource.service(simpleRequest, simpleResponse);
		} catch (ClassNotFoundException e) {
			my(ExceptionLogger.class).log(e);
			simpleResponse.setStatus(Response.SC_NOT_FOUND);
			simpleResponse.getWriter().write("Error: " + e.getMessage());
		}
	}

	private String resourceClassNameFor(String requestURI) {
		String[] splitedLowerCasedResourceURI = requestURI.toLowerCase().split("_");
		String result = _resourcesPackage;
		for (String part : splitedLowerCasedResourceURI)
			result += part.substring(0, 1).toUpperCase() + tail(part);
		
		return result + "Resource";
	}

	private String afterBaseUri(String request) {
		return request.substring(_baseUri.length());
	}

	private String tail(String request) {
		return request.substring(1);
	}

}
