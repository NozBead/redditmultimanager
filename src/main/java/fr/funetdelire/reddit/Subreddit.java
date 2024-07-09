package fr.funetdelire.reddit;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import fr.funetdelire.reddit.api.RedditApiConfiguration;

public class Subreddit {
	private String name;
	private String title;
	private String description;
	private URL url;
	
	public Subreddit(String name, String title, String description, URL url) {
		this.name = name;
		this.title = title;
		this.description = description;
		this.url = url;
	}
	
	public Subreddit(JSONObject json) throws MalformedURLException, JSONException {
		JSONObject sub = json.getJSONObject("data");
		name = sub.getString("display_name");
		title = sub.getString("title");
		description = sub.getString("description");
		url = new URL(RedditApiConfiguration.getInstance().base, sub.getString("url"));
	}
	
	public URL getUrl() {
		return url;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Name: ").append(name).append('\n');
		builder.append("Title: ").append(title).append('\n');
		builder.append("Description:\n").append(description).append('\n');
		builder.append("URL: ").append(url);
		return builder.toString();
	}
}
