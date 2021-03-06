package edu.csula.datascience.acquisition;

import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import me.jhenrique.model.Tweet;

import org.bson.Document;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TwitterCollector implements Collector<Tweet, Tweet> {
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;

	public TwitterCollector() {
		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDatabase("bigdata");
		this.collection = database.getCollection("twitterdata");
	}

	@Override
	public Collection<Tweet> mungee(Collection<Tweet> src) {
		List<Tweet> list = Lists.newArrayList();
		for (Iterator<Tweet> iterator = src.iterator(); iterator.hasNext();) {
			Tweet t = iterator.next();
			if (t.getText() != null && t.getUsername() != null && t.getHashtags() != null && t.getDate() != null) {
				list.add(t);
			}
		}
		return list;
	}

	@Override
	public void save(Collection<Tweet> data) {

		for (Iterator<Tweet> iterator = data.iterator(); iterator.hasNext();) {
			Tweet t = iterator.next();
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("date", t.getDate());
			map.put("text", t.getText());
			map.put("favorites", t.getFavorites());
			map.put("geo", t.getGeo());
			map.put("hashtags", t.getHashtags());
			map.put("id", t.getId());
			map.put("mentions", t.getMentions());
			map.put("permalink", t.getPermalink());
			map.put("retweets", t.getRetweets());
			map.put("username", t.getUsername());

			this.collection.insertOne(new Document(map));
			// System.out.println(t.getText());

		}

	}

}
