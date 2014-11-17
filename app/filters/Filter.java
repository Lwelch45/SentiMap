package filters;

import models.Tweet;

/**
 * Created by laurencewelch on 11/17/14.
 */
public abstract class Filter implements IFilter {
    public abstract boolean apply(Tweet incoming_tweet);
}
