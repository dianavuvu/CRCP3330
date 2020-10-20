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
	String status = "";
	
	//class scope to print tweet
	ArrayList<String> genTweet = new ArrayList<String>();
	
	//init Markov Generator
	MarkovOrderM<String> trainTweet = new MarkovOrderM(2);
	ArrayList<String> seed = new ArrayList<String>();

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
		
		//create seed for tweet
		seed.add("Trust");
		seed.add("me");

		//set words to search for
		String badAdvice = "Bad Advice";
		String badTip = "Bad Tip";
		String questChoices = "Questionable Choices";
		String adviceMe = "advisemebro"; //bad advice twitter user
		String bAdvice = "Badvice"; //Jimmy Fallon segment
		String worstAdvice = "Worst Advice Ever";
		String badD = "Bad Decision";
		
		
		//search and train
		search(badAdvice);
		search(badTip);
		search(questChoices);
		search(adviceMe);
		search(bAdvice);
		search(worstAdvice);
		search(badD);
		
		//generating tweet
		genTweet = trainTweet.generate(seed, 19); //generate ONE tweet
		
		//testing before posting to Twitter
		for (int i = 0; i < genTweet.size(); i++) {
			System.out.print(genTweet.get(i) + " ");
		}
	}
	
	void search(String s) {
		ArrayList<String> tweetResults = tweet.searchForTweets(s);
		for (int i = 0; i < tweetResults.size(); i++) {
			
			//just prints out the results for now, use to train markov chain
			//println(tweetResults.get(i)); 
			
			//init array list to hold tweets
			ArrayList<String> tweets = new ArrayList<String>();
			//tweets.add(tweetResults.get(i));
			
			TextTokenizer tokenizer = new TextTokenizer(tweetResults.get(i));			
				
			ArrayList<String> t = tokenizer.parseSearchText();
			
			int length = t.size() - 1; //init length of tokenizer
			
			//searching through data and getting rid of things I don't want
			for(int j = length; j >= 0; j--) {
				if(t.get(j).contains("@"))
					t.remove(j);
				else if(t.get(j).contains("/"))
					t.remove(j);
				else if(t.get(j).contains(":"))
					t.remove(j);
				else if(t.get(j).contains("com"))
					t.remove(j);
				else if(t.get(j).contains("-"))
					t.remove(j);
			}
			
			//add the tweets I want after cleaning them up
			tweets.addAll(t);
			
			//training
			trainTweet.train(tweets);
		}
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
		text("Press 1 to send a tweet!\n", width/10, height/6);
		
		fill(0, 200, 0);
		text("Press 2 to run ALL unit tests\n", width/10, height/4);
	}
	
	public void keyPressed() {
		
		//instantiate unit tests
		UnitOneTest test = new UnitOneTest();
		UnitTwoTest t = new UnitTwoTest();
		UnitThreeTest ts = new UnitThreeTest();
		
		if (key == ' ') {
			//player.reset();
			println("Melody started!");
		}
		else if (key == '1'){			
			for (int i = 0; i < genTweet.size(); i++) {
				//System.out.print(genTweet.get(i) + " ");
				status = status + genTweet.get(i) + " ";
			}
			
			//adding some hashtags
			status = status + " #nailedit " + "#botadvice";
			
			tweet.updateTwitter(status);
		}
		else if (key == '2') {
			//run unit 1
			test.run();
			//run unit 2
			t.run();
			//run unit 3
			ts.run();
		}
	}

}
