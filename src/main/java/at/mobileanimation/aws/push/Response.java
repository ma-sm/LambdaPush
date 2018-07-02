package at.mobileanimation.aws.push;

import java.util.HashMap;
import java.util.Map;

public class Response {
	
	// Main response singleton
	private static Response instance;
	
	private static final char OPENCURLB = '{'; 
	private static final char CLOSECURLB = '}'; 
	private static final char OPENBRA = '['; 
	private static final char CLOSEBRA = ']';
	private static final char FLUSH = '\n';
	private static final String TAB = "  ";
	private static final char COMMA = ',';
	
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
		this.title = title;
		return this;
	}
	
	public Response addAttribute(String key, String value) {
		attr.put(key, value);
		return this;
	}
	
	public Response addResponse(String key, Response response) {
		deepAttr.put(key, response);
		return this;
	}
	
	
	@Override
	public String toString() {
		sb = new StringBuilder();
		
		sb.append(getIntend() +  OPENCURLB);
		sb.append(FLUSH);
		increaseIntend();
		
		if (title != null && !title.trim().isEmpty())
			sb.append(getIntend() + "response:" + title + COMMA + FLUSH);
		
		sb.append(getIntend() + "data: " + OPENCURLB + FLUSH);
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
		
		for (Map.Entry<String,Response> e : deepAttr.entrySet()) {
			sb.append(getIntend() +  OPENCURLB + FLUSH);
			increaseIntend();
			sb.append(getIntend() +  e.getKey() + ": " + FLUSH + e.getValue().toString() + FLUSH);
			decreaseIntend();
			sb.append(getIntend() + CLOSECURLB + COMMA + FLUSH);
		}

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
			sb.append(getIntend() +  e.getKey() + ": " + e.getValue() + COMMA + FLUSH);
		
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
