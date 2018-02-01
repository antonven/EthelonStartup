package myapps.wycoco.com.ethelonstartup.Models;

/**
 * Created by dell on 1/30/2018.
 */

public class Badge_Level_Model {

    private String badge_level_name;
    private String badge_level_image;
    private String badge_skill;
    private String badge_status;

    public Badge_Level_Model(String badge_level_name, String badge_level_image, String badge_skill, String badge_status) {
        this.badge_level_name = badge_level_name;
        this.badge_level_image = badge_level_image;
        this.badge_skill = badge_skill;
        this.badge_status =badge_status;
    }

    public Badge_Level_Model() {
    }

    public String getBadge_level_name() {
        return badge_level_name;
    }

    public void setBadge_level_name(String badge_level_name) {
        this.badge_level_name = badge_level_name;
    }

    public String getBadge_level_image() {
        return badge_level_image;
    }

    public void setBadge_level_image(String badge_level_image) {
        this.badge_level_image = badge_level_image;
    }

    public String getBadge_skill() {
        return badge_skill;
    }

    public void setBadge_skill(String badge_skill) {
        this.badge_skill = badge_skill;
    }

    public String getBadge_status() {
        return badge_status;
    }

    public void setBadge_status(String badge_status) {
        this.badge_status = badge_status;
    }
}
