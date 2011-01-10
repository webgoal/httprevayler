package httprevayler.simpleprevalent;

import httprevayler.simpleservlet.SimpleResponse;

import java.io.IOException;
import java.io.PrintWriter;


public class DummySimpleResponse implements SimpleResponse {

	private static final SimpleResponse SINGLETON = new DummySimpleResponse();

	@Override
	public void setContentType(String string) {
	}

	@Override
	public void setStatus(int status) {
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(System.out, true);
	}

	public static SimpleResponse singleton() {
		return SINGLETON;
	}

	@Override
	public void writeEncodedResponse(String data) throws IOException {
		getWriter().write(data);
	}

	@Override
	public void setHeader(String string, String string2) {
	}

}
