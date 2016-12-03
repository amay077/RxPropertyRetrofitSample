package net.amay077.rxretrofitsample.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hrnv on 2016/12/03.
 */

public class User {
    @SerializedName("name")
    private String name;
    @SerializedName("location")
    private String location;
    @SerializedName("bio")
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}

