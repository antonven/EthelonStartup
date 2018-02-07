package myapps.wycoco.com.ethelonstartup.Models;

import java.io.Serializable;

/**
 * Created by dell on 12/28/2017.
 */

public class BadgeModel {

    private String badgeName;
    private String badgeImage;
    private float badgeProgress;

    public BadgeModel() {
    }

    public BadgeModel(String badgeName, String badgeImage) {
        this.badgeName = badgeName;
        this.badgeImage = badgeImage;
//        this.badgeProgress = badgeProgress;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getBadgeImage() {
        return badgeImage;
    }

    public void setBadgeImage(String badgeImage) {
        this.badgeImage = badgeImage;
    }

    public float getBadgeProgress() {
        return badgeProgress;
    }

    public void setBadgeProgress(float badgeProgress) {
        this.badgeProgress = badgeProgress;
    }
}
