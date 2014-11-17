package listeners;

import models.Tweet;
import play.libs.F;
import play.mvc.Results;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laurencewelch on 11/17/14.
 */
public class ChunkedListener {
    private static List<Results.Chunks.Out<String>> connections = new ArrayList<Results.Chunks.Out<String>>();

    public static void start(final Results.Chunks.Out<String> out){
        connections.add(out);
        out.onDisconnected(new F.Callback0(){
            public void invoke(){
                try {
                    connections.remove(out);
                }catch(Exception ex){
                }
            }
        });
    }

    public static void notifyAll(Tweet message){
        for (Results.Chunks.Out<String> out : connections) {
            try {
                out.write(message.toJson());
            }catch(Exception ex){
            }
        }
    }
}