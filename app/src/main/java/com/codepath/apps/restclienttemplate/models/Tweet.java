package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Parcel
@Entity(foreignKeys = @ForeignKey(entity =  User.class, parentColumns = "id",  childColumns = "userId"))
public class Tweet {

    @PrimaryKey
    @ColumnInfo
    public long id;

    @ColumnInfo
    public String body;

    @ColumnInfo
    public String CreatedAt;

    @ColumnInfo
    public String userId;


    @Ignore
    public User user;



    //private Entities entities;
    @ColumnInfo
    public String mediaUrl;



    // empty constructor needed by the Parceler library
    public Tweet() {
    }

    public static Tweet fromjson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.CreatedAt = getFormattedTimeStamp(jsonObject.getString("created_at"));
        tweet.id = jsonObject.getLong("id");
        User user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.user = user;

        JSONObject entities = jsonObject.getJSONObject("entities");
        if (entities != null && entities.has("media")) {
            JSONArray medias = entities.getJSONArray("media");
            if (medias.length() > 0) {
                JSONObject media = (JSONObject) medias.get(0);
                if (media.has("media_url")) {
                    tweet.mediaUrl = media.getString("media_url");
                }
            }
        }
        /*JSONObject extended_entities = jsonObject.getJSONObject("extended_entities");
        if (extended_entities != null && extended_entities.has("media")) {
            JSONArray medias = extended_entities.getJSONArray("media");
            if (medias.length() > 0) {
                JSONObject media = (JSONObject) medias.get(0);
                if (media.has("variants")) {
                    JSONArray video = extended_entities.getJSONArray("variants");
                    if(video.length()>0){
                        JSONObject play = (JSONObject) video.get(0);
                        if(play.has("bitrate")){
                            tweet.bitrate = play.getInt("bitrate");
                            if(tweet.bitrate == 320000){
                                tweet.video = media.getString("url");
                            }

                        }

                    }

                }
            }
        }*/


        return tweet;

    }

    public static List<Tweet> fromJsonArrary(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromjson(jsonArray.getJSONObject(i)));

        }
        return tweets;
    }

    public static String getFormattedTimeStamp(String CreatedAt) {

        return TimeFormatter.getTimeDifference(CreatedAt);

    }

}