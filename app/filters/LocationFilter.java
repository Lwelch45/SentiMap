package filters;

import models.Tweet;

/**
 * Created by laurencewelch on 11/17/14.
 */
public class LocationFilter extends Filter {
    public boolean apply(Tweet incoming_tweet){
        if(incoming_tweet.location == null){
            return false;
        }
        return true;
    }
}
