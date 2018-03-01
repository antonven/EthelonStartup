package myapps.wycoco.com.ethelonstartup.Models;

import java.io.Serializable;

/**
 * Created by asus on 07/02/2018.
 */

public class BadgeUpdateModel implements Serializable {
    String update;
    String body;
    String badge;
    String url;
    String badge_name;
    int points;



    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBadge_name() {
        return badge_name;
    }

    public void setBadge_name(String badge_name) {
        this.badge_name = badge_name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public BadgeUpdateModel(String update, String body, String badge, String url, String badge_name, int points) {

        this.update = update;
        this.body = body;
        this.badge = badge;
        this.url = url;
        this.badge_name = badge_name;
        this.points = points;

    }
}
