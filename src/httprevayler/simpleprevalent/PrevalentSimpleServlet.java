package httprevayler.simpleprevalent;

import static sneer.foundation.environments.Environments.my;
import httprevayler.simpleservlet.SimpleRequest;
import httprevayler.simpleservlet.SimpleResponse;
import httprevayler.simpleservlet.SimpleServlet;

import java.io.IOException;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

import sneer.foundation.environments.Bindings;
import sneer.foundation.environments.Environment;
import sneer.foundation.environments.EnvironmentUtils;
import sneer.foundation.environments.Environments;
import sneer.foundation.lang.Closure;
import sneer.foundation.lang.ClosureX;

public class PrevalentSimpleServlet implements SimpleServlet {

	private final Environment _servletEnvironment;
	private Prevayler _prevayler;

	public PrevalentSimpleServlet(SimpleServlet delegate, Object application) throws Exception {
		final PrevaylerFactory factory = new PrevaylerFactory();
		factory.configurePrevalentSystem(application);
		factory.configureTransactionFiltering(false);

		_servletEnvironment = EnvironmentUtils.compose(new Bindings(delegate).environment(), my(Environment.class));
		
		Environments.runWith(_servletEnvironment, new ClosureX<Exception>() { @Override public void run() throws Exception {
			_prevayler = factory.create();
		}});		
	}

	@Override
	public void service(final SimpleRequest request, final SimpleResponse response) throws IOException {
		Environments.runWith(_servletEnvironment, new Closure() { @Override public void run() {
			if (request.getMethod().equals("GET")) {
				try {
					_prevayler.execute(new SimpleQuery(request, response));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				_prevayler.execute(new SimpleTransaction(request, response));
			}
		}});

	}

}
