package filters;

import models.Tweet;

/**
 * Created by laurencewelch on 11/17/14.
 */
public abstract interface IFilter {
    public boolean apply(Tweet incoming_tweet);
}
