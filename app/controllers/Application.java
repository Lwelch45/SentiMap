package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import listeners.*;
import models.Tweet;
import play.mvc.*;

import twitter4j.*;


public class Application extends Controller {

    public static boolean connected_to_twitter = false;

    public static Result stream_chunked(){
        Chunks<String> chunks = new StringChunks() {
            public void onReady(Chunks.Out<String> out) {
                ChunkedListener.start(out);
            }
        };
        return ok(chunks);
    }

    public static Result connect_to_twitter(){
        if(!connected_to_twitter){
            final StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(twitter4j.Status status) {
                ChunkedListener.notifyAll(Tweet.add_tweet(status));
                WebSocketListener.notifyAll(Tweet.add_tweet(status));
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }

            @Override
            public void onTrackLimitationNotice(int i) {

            }

            @Override
            public void onScrubGeo(long l, long l2) {

            }

            @Override
            public void onStallWarning(StallWarning stallWarning) {
                try{
                    Thread.sleep(10000);
                }catch (Exception e){

                }
            }

            @Override
            public void onException(Exception e) {
                e.printStackTrace();
            }
        };
            TwitterStream ts = new TwitterStreamFactory(Util.loadConfiguration().build()).getInstance();
            ts.addListener(listener);
            ts.sample();
            response().setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            connected_to_twitter = true;
            return ok("connection started");
        }else{
            return ok("connection already opened");
        }
    }


    public static WebSocket<JsonNode> stream_socket(){
        return new WebSocket<JsonNode>(){
            public void onReady(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out){
                WebSocketListener.start(in, out);
            }
        };
    }


}
