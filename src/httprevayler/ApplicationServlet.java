package httprevayler;

import httprevayler.simpleprevalent.PrevalentSimpleServlet;
import httprevayler.simpleservlet.SimpleRequest;
import httprevayler.simpleservlet.SimpleResponseImpl;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ApplicationServlet extends HttpServlet implements Servlet {
	
	private final PrevalentSimpleServlet _prevalentSimpleServlet;
	
	public ApplicationServlet(Object application) throws Exception {
		super();
		_prevalentSimpleServlet = new PrevalentSimpleServlet(
			new DispatcherSimpleServlet(resourcesPackage(application)),
			application);
	}
	
	private String resourcesPackage(Object application) {
		String packge = application.getClass().getPackage().getName();
		String parent = packge.substring(0, packge.lastIndexOf("."));
		return parent + ".resources";
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		_prevalentSimpleServlet.service(new SimpleRequest(request),
				new SimpleResponseImpl(response));
	}

}
