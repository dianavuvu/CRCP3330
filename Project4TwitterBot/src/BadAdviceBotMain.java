/* Programmer: Diana Vu
 * Date: October 20, 2020
 * This class is a template for creating a twitterbot & also demonstrated web-scraping
 */


import processing.core.*;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.jaunt.JauntException;

//This class serves as a template for creating twitterbots and demonstrates string tokenizing and web scraping and the use of the 
//twitter API
public class BadAdviceBotMain extends PApplet {

	private ArrayList<String> tokens;
	private static String HEYER_TWITTER_URL = "https://twitter.com/BADadvicebot1"; //my twitter
	private static int TWITTER_CHAR_LIMIT = 140; //I understand this has changed... but forget limit
	
	//useful constant strings -- for instance if you want to make sure your tweet ends on a space or ending punctuation, etc.
	private static final String fPUNCTUATION = "\",.!?;:()/\\";
	private static final String fENDPUNCTUATION = ".!?;,";
	private static final String fREALENDPUNCTUATION = ".!?";

	private static final String fWHITESPACE = "\t\r\n ";
	
	//example twitter hastag search term
	private static final String fPASSIVEAGG = "badadvice";
	private static final String fCOMMA = ","; 
	
	//handles twitter api
	TwitterInteraction tweet; 
	
	//class scope to print tweet
	ArrayList<String> genTweet = new ArrayList<String>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("BadAdviceBotMain");  //Not really using processing functionality but ya know, you _could_. UI not required.
	}

	public void settings() {
		size(300, 300); //dummy window
	};

	public void setup() {
		//tweet = new TwitterInteraction(); 
		
		//loadNovel("data/The Grand Sophy excerpt.txt"); //TODO: must train from another source
		//println("Token size:"+tokens.size());
		
		trainTwitterStat(); //train on twitter hashtags
		
		//useScraper();
				
	}
	
	void trainTwitterStat() {
		tweet = new TwitterInteraction();
		
		//here is an example of searching twitter hashtag. You have to pay $$ to the man to get more results. :(
		//see TwitterInteraction class
		
		ArrayList<String> tweetResults = tweet.searchForTweets("Bad Advice");
		for (int i = 0; i < tweetResults.size(); i++) {
			
			//just prints out the results for now, use to train markov chain
			//println(tweetResults.get(i)); 
			
			//init array list to hold tweets
			ArrayList<String> tweets = new ArrayList<String>();
			//tweets.add(tweetResults.get(i));
			
			TextTokenizer tokenizer = new TextTokenizer(tweetResults.get(i));
			ArrayList<String> t = tokenizer.parseSearchText();
			tweets.addAll(t);
			
			//init Markov Generator
			MarkovOrderM<String> trainTweet = new MarkovOrderM(10);
			MarkovGenerator<String> initTweet = new MarkovGenerator<String>();
			
			//training
			trainTweet.train(tweets);
			initTweet.train(tweets);
			
			//initialize new array for generated tweets  
			ArrayList<String> initT = new ArrayList<String>();
			
			initT = initTweet.generate(20);
			genTweet = trainTweet.generate(initT, 20); //generate ONE tweet
		}
		
		//testing before posting to Twitter
		for (int i = 0; i < genTweet.size(); i++) {
			System.out.println(genTweet.get(i) + " ");
		}
		
		//Make sure within Twitter limits (used to be 140 but now is more?)
//		String status = genTweet + " ";
//		tweet.updateTwitter(status);
	}
	
	void useScraper() {
		tweet = new TwitterInteraction();
		
		//prints the text content of the sites that come up with the google search of dogs
		//you may use this content to train your AI too
		
		Scraper scraper = new Scraper(); 
		ArrayList<String> results;
		try {
			results = scraper.scrapeGoogleResults("badadvice");
			
			//print your results
			System.out.println(results); 
			
			scraper.scrape("http://google.com",  "badadvice"); //see class documentation

		} catch (JauntException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//this loads the novel 'The Grand Sophy' given a path p -- but really will load any file.
	void loadNovel(String p) {
		tweet = new TwitterInteraction();
		String filePath = getPath(p);
		Path path = Paths.get(filePath);
		tokens = new ArrayList<String>();

		try {
			List<String> lines = Files.readAllLines(path);

			for (int i = 0; i < lines.size(); i++) {

				TextTokenizer tokenizer = new TextTokenizer(lines.get(i));
				ArrayList<String> t = tokenizer.parseSearchText();
				tokens.addAll(t);
			}

		} catch (Exception e) {
			e.printStackTrace();
			println("Oopsie! We had a problem reading a file!");
		}
	}
	
	void printTokens() {
		for (int i = 0; i < tokens.size(); i++)
			print(tokens.get(i) + " ");
	}

	//get the relative file path 
	String getPath(String path) {

		String filePath = "";
		try {
			filePath = URLDecoder.decode(getClass().getResource(path).getPath(), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}

	public void draw() {
		// ellipse(width / 2, height / 2, second(), second());
		
		textSize(15);
		fill(0, 0, 200);
		text("Press 1 to start Unit Test One\n", width/10, height/6);
		
		fill(0, 200, 0);
		text("Press 2 to start Unit Test Two\n", width/10, height/4);
		
		fill(200, 0, 0);
		text("Press 3 to start Unit Test Three\n", width/10, height/3);

	}

}
