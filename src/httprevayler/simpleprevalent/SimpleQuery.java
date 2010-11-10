package httprevayler.simpleprevalent;

import httprevayler.simpleservlet.SimpleResponse;
import httprevayler.simpleservlet.SimpleRequest;

import java.util.Date;

import org.prevayler.Query;

@SuppressWarnings("serial")
public class SimpleQuery extends SimpleCommand implements Query {

	public SimpleQuery(SimpleRequest request, SimpleResponse response) {
		super(request, response);
	}

	@Override
	public Object query(Object aplicacao, Date date) {
		return process(aplicacao, date);
	}

}
