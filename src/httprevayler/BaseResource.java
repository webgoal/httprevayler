package httprevayler;

import org.eclipse.jetty.server.Response;

import flexjson.JSONSerializer;
import httprevayler.simpleservlet.SimpleRequest;
import httprevayler.simpleservlet.SimpleResponse;

public abstract class BaseResource {
	protected SimpleRequest _request;
	protected SimpleResponse _response;
	
	public void service(SimpleRequest simpleRequest, SimpleResponse simpleResponse) throws Exception {
		configure(simpleRequest, simpleResponse);
		String method = simpleRequest.getMethod();
		
		if (!beforeService()) return;
		
		if (method.equals("GET")) respondToGet();
		if (method.equals("POST")) respondToPost();
		if (method.equals("DELETE")) doDelete();
	}

	protected abstract boolean beforeService() throws Exception;

	private void respondToGet() throws Exception {
		writeEncoded(doGet());
	}
	
	private void respondToPost() throws Exception {
		writeEncoded(doPost());
	}

	private void configure(SimpleRequest simpleRequest, SimpleResponse simpleResponse) {
		_request = simpleRequest;
		_response = simpleResponse;
	}

	protected Object doGet() throws Exception {
		_response.setStatus(Response.SC_METHOD_NOT_ALLOWED);
		return null;
	}
	
	protected Object doPost() throws Exception {
		_response.setStatus(Response.SC_METHOD_NOT_ALLOWED);
		return null;
	}

	protected void doDelete() throws Exception {
		_response.setStatus(Response.SC_METHOD_NOT_ALLOWED);
	}
	
	protected void writeEncoded(Object response) throws Exception {
		JSONSerializer serializer = new JSONSerializer();
		_response.writeEncodedResponse(serializer.serialize(response));
	}

}
