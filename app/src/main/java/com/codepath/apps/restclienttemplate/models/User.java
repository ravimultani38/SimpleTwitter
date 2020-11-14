package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Parcel
@Entity
public class User {

    @ColumnInfo
    @PrimaryKey
    public long id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String screeNname;

    @ColumnInfo
    public String profileimageurl;


    // empty constructor needed by the Parceler library
    public User() {
    }


    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.id = jsonObject.getLong("id");
        user.name=jsonObject.getString("name");
        user.screeNname=jsonObject.getString("screen_name");
        user.profileimageurl=jsonObject.getString("profile_image_url_https");
        return user;

    }

    public static List<User> fromJsonTweetArray(List<Tweet> tweetsFromNetwork) {
        List<User> users = new ArrayList<>();
        for(int i =0; i<tweetsFromNetwork.size();i++){
            users.add(tweetsFromNetwork.get(i).user);

        }
        return users;
    }

}
