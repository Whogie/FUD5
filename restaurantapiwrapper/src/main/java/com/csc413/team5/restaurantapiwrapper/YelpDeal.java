package com.csc413.team5.restaurantapiwrapper;

import android.net.Uri;

/**
 * Representation of a Yelp Deal as an object.
 * <p>
 * Created on 6/30/2015.
 *
 * @author Eric C. Black
 */
public class YelpDeal {
    protected String id;
    protected String title;
    protected String whatYouGet;
    protected Uri dealUrl;
    protected int dealStartTime;
    protected int dealEndTime;
    protected boolean dealEnds;

    public YelpDeal() {
        id = "";
        title = "";
        whatYouGet = "";
        dealUrl = null;
        dealStartTime = -1;
        dealEndTime = -1;
        dealEnds = false;
    }

    /**
     * @return the Yelp identifier of this deal
     */
    public String getId() {
        return id;
    }

    /**
     * @return the title of the deal
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return additional details for the deal, separated by newlines
     */
    public String getWhatYouGet() {
        return whatYouGet;
    }

    /**
     * @return URL for the deal
     */
    public Uri getDealUrl() {
        return dealUrl;
    }

    /**
     * @return the deal start time as Unix timestamp
     */
    public int getDealStartTime() {
        return dealStartTime;
    }

    /**
     * @return the deal end time as Unix timestamp
     */
    public int getDealEndTime() {
        return dealEndTime;
    }

    /**
     * @return true if the deal has an end time, null otherwise
     */
    public boolean dealEnds() {
        return dealEnds;
    }

    @Override
    public String toString() {
        return "YelpDeal{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", whatYouGet='" + whatYouGet + '\'' +
                ", dealUrl=" + dealUrl +
                ", dealStartTime=" + dealStartTime +
                ", dealEndTime=" + dealEndTime +
                ", dealEnds=" + dealEnds +
                '}';
    }
}
