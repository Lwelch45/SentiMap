package models;

/**
 * Created by laurencewelch on 11/14/14.
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import play.db.ebean.Model;
import play.libs.Json;
import twitter4j.GeoLocation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.TreeMap;

@Entity
public class Location extends Model{

    @Id
    Long id;

    double longitude;
    double latitude;

    ArrayList<Tweet> tweets;

    public Location (GeoLocation loc){

       longitude = loc.getLongitude();
       latitude = loc.getLatitude();
    }

    public String toJson(){
        TreeMap<String, String> data = new TreeMap<String, String>();
        data.put("longitude",""+ longitude);
        data.put("latitude",""+ latitude);
        ObjectMapper mapper = new ObjectMapper();

        return Json.stringify(mapper.valueToTree(data));
    }
}
