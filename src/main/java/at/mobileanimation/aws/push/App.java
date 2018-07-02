package at.mobileanimation.aws.push;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * Hello world!
 *
 */
public class App implements RequestHandler<Void, Response>
{
	

	@Override
	public Response handleRequest(Void arg0, Context arg1) {
		
		
		
		
		
		
		return null;
	}
	
	
	
	/*
	 * Constructor
	 */
	public App() {
		Response.getInststance()
			.setTitle("PushLambda");
	}
	
	/*
	 * Main for testing
	 */
    public static void main( String[] args )
    {
    	App app = new App();
        Response.getInststance()
        	.setTitle("Testing");
        app.handleRequest(null, null);    
    }
}
