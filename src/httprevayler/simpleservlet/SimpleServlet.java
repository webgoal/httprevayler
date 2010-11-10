package httprevayler.simpleservlet;


public interface SimpleServlet {

	public abstract void service(SimpleRequest simpleRequest,
			SimpleResponse response) throws Exception;

}