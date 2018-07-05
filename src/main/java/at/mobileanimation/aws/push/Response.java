package at.mobileanimation.aws.push;

import java.util.HashMap;
import java.util.Map;

public class Response {
	
	private static final String NULL = "<null>";

	// Main response singleton
	private static Response instance;
	
	private static final char OPENCURLB = '{'; 
	private static final char CLOSECURLB = '}'; 
	private static final char OPENBRA = '['; 
	private static final char CLOSEBRA = ']';
	private static final char FLUSH = '\n';
	private static final String TAB = "  ";
	private static final char COMMA = ',';
	private static final char QUOTE = '"';
	
	private static int intendCounter = 0;
	
	// Members
	private String title;
	private Map<String,String> attr;
	private Map<String,Response> deepAttr;

	private StringBuilder sb;

	
	// Constructor
	public Response() {
		title = "";
		attr = new HashMap<>();
		deepAttr = new HashMap<>();
	}
	
	
	/*
	 * Methods
	 */	
	
	public Response setTitle(String title) {
		if (title == null)
			title = NULL;
		this.title = title;
		return this;
	}
	
	public Response addAttribute(String key, String value) {
		if (key == null)
			return this;
		if (value == null)
			value = NULL;
		attr.put(key, value);
		return this;
	}
	
	public Response addResponse(String key, Response response) {
		if (key == null)
			return this;
		if (response == null)
			response = new Response().setTitle(NULL);
		deepAttr.put(key, response);
		return this;
	}
	
	
	@Override
	public String toString() {
		sb = new StringBuilder();
		
		sb.append(OPENCURLB);
		sb.append(FLUSH);
		increaseIntend();
		
		if (title != null && !title.trim().isEmpty())
			sb.append(getIntend() + QUOTE +"response" + QUOTE + ": " + QUOTE + title + QUOTE + COMMA + FLUSH);
		
		sb.append(getIntend() + QUOTE + "data" + QUOTE + ": " + OPENCURLB + FLUSH);
		increaseIntend();
		appendAttr();
		if (!attr.isEmpty() && !deepAttr.isEmpty()) {
			sb.append(COMMA);
			sb.append(FLUSH);
		}
		
		appendDeepAttr();
		if (!attr.isEmpty() || !deepAttr.isEmpty())
			sb.append(FLUSH);
		decreaseIntend();
		sb.append(getIntend() + CLOSECURLB + FLUSH);
		
		decreaseIntend();
		sb.append(getIntend() +  CLOSECURLB);
		
		return sb.toString();
	}
	
	
	private String getIntend() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < intendCounter; i++)
			s.append(TAB);
		return s.toString();
	}

	private void appendDeepAttr() {
		if (deepAttr.isEmpty())
			return;
		sb.append(getIntend() +  OPENBRA + FLUSH);
		for (Map.Entry<String,Response> e : deepAttr.entrySet()) {
			
			increaseIntend();
			sb.append(getIntend() + QUOTE +  e.getKey() + QUOTE + ": "  + e.getValue().toString() + COMMA + FLUSH);
			decreaseIntend();
			
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append(FLUSH);
		sb.append(getIntend() + CLOSEBRA);
	}

	private void decreaseIntend() {
		if (intendCounter > 0)
			intendCounter--;
	}

	private void increaseIntend() {
		intendCounter++;
	}

	private void appendAttr() {
		if (attr.isEmpty())
			return;
		sb.append(getIntend() +  OPENBRA);
		sb.append(FLUSH);
		increaseIntend();
		
		for (Map.Entry<String,String> e : attr.entrySet())
			sb.append(getIntend() + QUOTE +  e.getKey() + QUOTE + ": " + QUOTE + e.getValue() + QUOTE + COMMA + FLUSH);
		sb.delete(sb.length()-2, sb.length());
		sb.append(FLUSH);
		decreaseIntend();
		sb.append(getIntend() +  CLOSEBRA);
	}

	// Singleton 
	public static Response getInststance() {
		if (instance == null)
			instance = new Response();
		return instance;
	}
}
