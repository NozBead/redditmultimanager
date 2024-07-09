package fr.funetdelire;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import fr.funetdelire.reddit.Subreddit;
import fr.funetdelire.reddit.api.RedditApi;

public class MultiManager {
	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
		if(args.length != 2) {
			System.err.println("Usage: ACCESS_TOKEN USERNAME");
			System.exit(1);
		}
		
		RedditApi api = new RedditApi(args[0], args[1]);
		
		List<Subreddit> subs = api.getSubreddits();
		Iterator<Subreddit> iterator = subs.iterator();
		Subreddit current = iterator.next();
		
		Scanner sc = new Scanner(System.in);

		System.out.print("reddit>");
		while(sc.hasNext()) {
			String[] cmd = sc.nextLine().split(" ");
			
			switch(cmd[0]) {
				case "p":
					System.out.println(current);
				break;
				case "n":
					if (iterator.hasNext()) {
						current = iterator.next();
						System.out.println(current);
					}
					else {
						System.out.println("That was the last subreddit.");
					}
				break;
				case "a":
					if (cmd.length == 2) {
						System.out.println(api.addSubreddit(cmd[1], current.getUrl().getPath()));
					}
					else {
						System.out.println("Usage: a <multi>");
					}
				break;
				case "c":
					if (cmd.length == 2) {
						System.out.println(api.createMulti(cmd[1]));
					}
					else {
						System.out.println("Usage: c <multi>");
					}
				break;
			}
			System.out.print("reddit>");
		}
		
		sc.close();
	}
}
