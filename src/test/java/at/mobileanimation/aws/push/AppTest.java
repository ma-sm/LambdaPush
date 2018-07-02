package at.mobileanimation.aws.push;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	
    	
        assertTrue( true );
    }
    
    public void testNoTitle()
    {
    	Response mainResponse = new Response();
    	System.out.println(mainResponse);
    	
        assertTrue( true );
    }
    
    public void testWithTitle() {
    	Response mainResponse = new Response().setTitle("Hallo");
    	System.out.println(mainResponse);
    	
    	assertTrue( true );
    }
    
    public void testWithAttr() {
    	Response mainResponse = new Response()
    			.setTitle("Hallo")
    			.addAttribute("Hallo", "Welt")
    			.addAttribute("Key", "Value");
    	System.out.println(mainResponse);
    	
    	assertTrue( true );
    }
    
    public void testNestedResponse() {
    	Response nested1 = new Response()
    			.setTitle("Number1")
    			.addAttribute("Inner1", "Response1")
    			.addAttribute("Inner2", "Response2");
    	
    	Response nested2 = new Response()
    			.addAttribute("Blargh", "Response1")
    			.addAttribute("Nargh", "Value2");
    	
    	Response mainResponse = new Response()
    			.setTitle("Hallo")
    			.addAttribute("Hallo", "Welt")
    			.addAttribute("Key", "Value")
    			.addResponse("First", nested1)
    			.addResponse("Second", nested2);
    	System.out.println(mainResponse);
    	
    	assertTrue( true );
    }
    
}
