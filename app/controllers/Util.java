package controllers;

import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by laurencewelch on 11/17/14.
 */
public class Util {

    public static AccessToken loadAccessToken(int useId){
        String token = play.Configuration.root().getString("twitter.token");// load from a persistent store
        String tokenSecret = play.Configuration.root().getString("twitter.tokenSecret");// load from a persistent store
        return new AccessToken(token, tokenSecret);
    }

    public static ConfigurationBuilder loadConfiguration(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(play.Configuration.root().getString("twitter.consumer"))
                .setOAuthConsumerSecret(play.Configuration.root().getString("twitter.consumerSecret"))
                .setOAuthAccessToken(play.Configuration.root().getString("twitter.token"))
                .setOAuthAccessTokenSecret(play.Configuration.root().getString("twitter.tokenSecret"));
        return cb;
    }
}
