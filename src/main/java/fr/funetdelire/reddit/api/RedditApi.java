package fr.funetdelire.reddit.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.funetdelire.reddit.Subreddit;

public class RedditApi {
	private HttpClient client;
	private String username;
	
	
	public RedditApi(String oauthToken, String username) throws MalformedURLException {
		client = HttpClient.newHttpClient();
		this.username = username;
		
		headers = new String[] {
				"User-agent", RedditApiConfiguration.getInstance().agent + "(u/" + username + ")",
				"Authorization", "Bearer " + oauthToken
		};
	}
	
	public List<Subreddit> getSubreddits() throws JSONException, IOException, InterruptedException, URISyntaxException {
		URL apiBase = RedditApiConfiguration.getInstance().baseApi;
		HttpRequest req = HttpRequest.newBuilder(new URL(apiBase, "/subreddits/mine/subscriber").toURI())
			.GET()
			.headers(headers)
			.build();
		
		HttpResponse<String> response = client.send(req, BodyHandlers.ofString());
		JSONArray listing = new JSONObject(response.body()).getJSONObject("data").getJSONArray("children");
		
		List<Subreddit> subreddits = new LinkedList<>();
		for (Object sub : listing) {
			JSONObject subJson = (JSONObject) sub;
			subreddits.add(new Subreddit(subJson));
		}
		
		return subreddits;
	}
	
	public String addSubreddit(String multiName, String subName) throws JSONException, IOException, InterruptedException, URISyntaxException {
		URL apiBase = RedditApiConfiguration.getInstance().baseApi;
		String urlStr = "/api/multi/user/" + username + "/m/" + multiName + subName;
		String encoded = "{\"name\":\"none\"}";
		String body = "model=" + URLEncoder.encode(encoded, "utf-8").replace("+", "%20");;
		
		HttpRequest req = HttpRequest.newBuilder(new URL(apiBase, urlStr).toURI())
				.headers(headers)
				.header("Content-type", "application/x-www-form-urlencoded")
				.PUT(BodyPublishers.ofString(body))
				.build();
		
		return client.send(req, BodyHandlers.ofString()).body();
	}
	
	public String createMulti(String multiName) throws JSONException, IOException, InterruptedException, URISyntaxException {
		URL apiBase = RedditApiConfiguration.getInstance().baseApi;
		String urlStr = "/api/multi/user/" + username + "/m/" + multiName;
		String encoded = "{\"description_md\": \"\", \"display_name\": \"" + multiName + "\", \"icon_img\": \"https://m.media-amazon.com/images/I/518IVUZ7HAL._AC_SX425_.jpg\", \"key_color\": \"#AABBCC\", \"subreddits\": [], \"visibility\": \"private\"}";
		String body = "model=" + URLEncoder.encode(encoded, "utf-8").replace("+", "%20");
		
		HttpRequest req = HttpRequest.newBuilder(new URL(apiBase, urlStr).toURI())
			.headers(headers)
			.header("Content-type", "application/x-www-form-urlencoded")
			.PUT(BodyPublishers.ofString(body))
			.build();
		
		HttpResponse<String> response = client.send(req, BodyHandlers.ofString());
		return response.body();
	}
}
