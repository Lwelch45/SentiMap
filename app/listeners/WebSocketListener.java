package listeners;


import akka.actor.Actor;
import com.fasterxml.jackson.databind.JsonNode;
import models.Tweet;
import play.libs.F;
import play.libs.Json;
import play.mvc.Results;
import play.mvc.WebSocket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laurencewelch on 11/16/14.
 */
public class WebSocketListener {
    private static List<WebSocket.Out<JsonNode>> connections = new ArrayList<WebSocket.Out<JsonNode>>();

    public static void start(WebSocket.In<JsonNode> in, final WebSocket.Out<JsonNode> out){

        connections.add(out);

        in.onClose(new F.Callback0(){
            public void invoke(){
                try {
                    connections.remove(out);
                }catch(Exception ex){

                }
            }
        });
    }

    public static void notifyAll(Tweet message){
        for (WebSocket.Out<JsonNode> out : connections) {
            out.write(Json.toJson(message));
        }
    }
}


