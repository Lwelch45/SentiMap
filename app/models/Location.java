package models;

/**
 * Created by laurencewelch on 11/14/14.
 */
import play.db.ebean.Model;
import twitter4j.GeoLocation;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Location extends Model{

    @Id
    Long id;

    double longitude;
    double latitude;

    ArrayList<Tweet> tweets;

    public Location (GeoLocation loc){
//        longitude = loc.getLongitude();
//        latitude = loc.getLatitude();
    }
}
