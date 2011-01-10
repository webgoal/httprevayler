package httprevayler.simpleservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public interface SimpleResponse {

	void setContentType(String string);

	void setStatus(int scOk);

	PrintWriter getWriter() throws IOException;
	
	void writeEncodedResponse(String data) throws UnsupportedEncodingException, IOException;

	void setHeader(String string, String string2);
	
}
