package fr.funetdelire.reddit.api;

import java.net.MalformedURLException;
import java.net.URL;

public class RedditApiConfiguration {
	private static final String BASE = "https://reddit.com";
	private static final String BASE_API = "https://oauth.reddit.com";
	
	private static RedditApiConfiguration instance;
	
	public URL base;
	public URL baseApi;
	public String agent = "linux:9YwLN3L56_3QrWLTMtgqAQ:1.0.0";
	
	public static RedditApiConfiguration getInstance() {
		if (instance == null) {
			instance = new RedditApiConfiguration();
		}
		
		return instance;
	}
	
	private RedditApiConfiguration() {
		try {
			base = new URL(BASE);
			baseApi = new URL(BASE_API);
		}catch (MalformedURLException e) {
			base = null;
			baseApi = null;
		}
	}
}
