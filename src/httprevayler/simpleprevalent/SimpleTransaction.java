package httprevayler.simpleprevalent;

import httprevayler.simpleservlet.SimpleResponse;
import httprevayler.simpleservlet.SimpleRequest;

import java.util.Date;

import org.prevayler.SureTransactionWithQuery;

@SuppressWarnings("serial")
public class SimpleTransaction extends SimpleCommand implements SureTransactionWithQuery {

	public SimpleTransaction(SimpleRequest request, SimpleResponse response) {
		super(request, response);
	}

	@Override
	public Object executeAndQuery(Object aplicacao, Date date) {
		return process(aplicacao, date);
	}

}