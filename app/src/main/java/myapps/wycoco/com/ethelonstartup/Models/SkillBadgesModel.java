package myapps.wycoco.com.ethelonstartup.Models;

/**
 * Created by dell on 1/17/2018.
 */

public class SkillBadgesModel {

    private String badgeName;
    private String badgeImage;
    private int gaugeExp;
    private int star;

    public SkillBadgesModel(String badgeName, String badgeImage, int gaugeExp, int star) {
        this.badgeName = badgeName;
        this.badgeImage = badgeImage;
        this.gaugeExp = gaugeExp;
        this.star  = star;
    }

    public SkillBadgesModel() {

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

    public int getGaugeExp() {
        return gaugeExp;
    }

    public void setGaugeExp(int badgePercentage) {
        this.gaugeExp = gaugeExp;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
