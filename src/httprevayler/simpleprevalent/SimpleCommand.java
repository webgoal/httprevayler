package httprevayler.simpleprevalent;

import static sneer.foundation.environments.Environments.my;
import httprevayler.simpleservlet.SimpleRequest;
import httprevayler.simpleservlet.SimpleResponse;
import httprevayler.simpleservlet.SimpleServlet;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import sneer.bricks.hardware.io.log.exceptions.ExceptionLogger;
import sneer.foundation.environments.Bindings;
import sneer.foundation.environments.Environment;
import sneer.foundation.environments.EnvironmentUtils;
import sneer.foundation.environments.Environments;
import sneer.foundation.lang.Closure;

@SuppressWarnings("serial")
public class SimpleCommand implements Serializable {
	
	private final SimpleRequest _request;
	private static SimpleResponse _response = DummySimpleResponse.singleton(); // Not serialize

	public SimpleCommand(SimpleRequest request, SimpleResponse response) {
		_request = request;
		_response = response;
	}
	
	protected Object process(Object aplicacao, Date date) {
		final SimpleServlet servlet = my(SimpleServlet.class);

		Environment withAplicacao = EnvironmentUtils.compose(new Bindings(aplicacao).environment(), my(Environment.class));
		
		Environments.runWith(withAplicacao, new Closure() { @Override public void run() {
			serviceWith(servlet);
		}});

		return null;
	}

	private void serviceWith(final SimpleServlet servlet) {
		try {
			servlet.service(_request, _response);
		} catch (Exception ex) {
			try {
				_response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				ex.printStackTrace(_response.getWriter());
				my(ExceptionLogger.class).log(ex);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
