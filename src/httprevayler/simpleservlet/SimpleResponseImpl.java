package httprevayler.simpleservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

public class SimpleResponseImpl implements SimpleResponse {
	
	private HttpServletResponse _response;

	public SimpleResponseImpl(HttpServletResponse response) {
		_response = response;
	}

	@Override
	public void setContentType(String string) {
		_response.setContentType(string);
	}

	@Override
	public void setStatus(int status) {
		_response.setStatus(status);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return _response.getWriter();
	}
	
	public void writeEncodedResponse(String data) throws UnsupportedEncodingException, IOException {
		_response.getWriter().write(URLEncoder.encode(data, "UTF-8"));
	}

	@Override
	public void setHeader(String header, String value) {
		_response.setHeader(header, value);
	}
}