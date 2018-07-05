package at.mobileanimation.aws.push;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * Hello world!
 *
 */
public class App implements RequestHandler<Request, Response>
{
	
	// main handler
	@Override
	public Response handleRequest(Request arg0, Context arg1) {
		
		Response.getInststance()
			.setTitle("Lambda")
			.addAttribute("id", arg0.getId());
		
		
		
		
		return Response.getInststance();
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
