package models;

/**
 * Created by laurencewelch on 11/14/14.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import play.db.ebean.Model;
import javax.persistence.*;

import play.libs.Json;
import twitter4j.Status;

import java.util.TreeMap;

@Entity
public class Tweet extends Model{
    @Id
    Long id;

    Long tweet_id;
    String text;
    String source;
    boolean truncated;

    boolean is_favorited;
    int favorite_count;

    boolean is_retweet;
    boolean is_sensitive;

    String lang;

    @OneToMany(mappedBy = "location")
 //   Location location;

    DateTime received_date_time;

    public Tweet(){
        received_date_time = DateTime.now();
    }

    public static Finder<Long, Tweet> find =new Finder<Long, Tweet>(Long.class, Tweet.class);

    public static Tweet find_by_tweet_id(Long lookup){
        if (contains(lookup)){
            return find.where().eq("tweet_id", lookup).findList().get(0);
        }
        return null;
    }

    public static Tweet add_tweet(Status incoming_tweet){
        Tweet result = new Tweet();
        result.tweet_id = incoming_tweet.getId();
        result.text = incoming_tweet.getText();
        result.source = incoming_tweet.getSource();
        result.truncated = incoming_tweet.isTruncated();
        result.is_favorited = incoming_tweet.isFavorited();
        if (result.is_favorited){
            result.favorite_count = incoming_tweet.getFavoriteCount();
        }
        result.is_retweet = incoming_tweet.isRetweet();
        result.is_sensitive = incoming_tweet.isPossiblySensitive();
       // result.location = new Location(incoming_tweet.getGeoLocation());
        result.save();
        return result;
    }

    public String toJson(){
        TreeMap<String, String> data = new TreeMap<String, String>();
        data.put("id",""+ id);
        data.put("tweet_id",""+ tweet_id);
        data.put("text",text);
        data.put("source",source);
        ObjectMapper mapper = new ObjectMapper();

        return Json.stringify(mapper.valueToTree(data));
    }

    public static boolean contains(Long lookup){
        return find.where().eq("tweet_id", lookup).findList().size() > 0;
    }
}
