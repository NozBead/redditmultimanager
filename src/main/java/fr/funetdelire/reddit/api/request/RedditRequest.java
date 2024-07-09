package fr.funetdelire.reddit.api.request;

public abstract class RedditRequest<T> {
	public void send(HttpClient client);
	public T getReponse();
}
