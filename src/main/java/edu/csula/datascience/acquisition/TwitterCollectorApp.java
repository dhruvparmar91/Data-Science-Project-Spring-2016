package edu.csula.datascience.acquisition;

import twitter4j.Status;

import java.util.Collection;

import me.jhenrique.manager.TweetManager;
import me.jhenrique.manager.TwitterCriteria;
import me.jhenrique.model.Tweet;

/**
 * A simple example of using Twitter
 */
public class TwitterCollectorApp {
    public static void main(String[] args) {
    	
    	TwitterSource source = new TwitterSource();
    	TwitterCollector collector = new TwitterCollector();
    	
    	 while (source.hasNext()) {
           Collection<Tweet> tweets = source.next();
           Collection<Tweet> cleanedTweets = collector.mungee(tweets);
           collector.save(cleanedTweets);
       }
    	
//        TwitterSource source = new TwitterSource(Long.MAX_VALUE, "#bigdata");
//        TwitterCollector collector = new TwitterCollector();
//
//        while (source.hasNext()) {
//            Collection<Status> tweets = source.next();
//            Collection<Status> cleanedTweets = collector.mungee(tweets);
//            collector.save(cleanedTweets);
//        }
//    	 TwitterCriteria criteria = TwitterCriteria.create()
//                 .setQuerySearch("#BeerHaus" + "#LasVegas")
//                 .setMaxTweets(100);
//    	 long count = 0;
//    	 for(Tweet t : TweetManager.getTweets(criteria)) {
//    		 System.out.println(count++);
//    		 System.out.println(t.getText());
//    	 }
    }
}
