package myapps.wycoco.com.ethelonstartup.Models;

/**
 * Created by dell on 1/17/2018.
 */

public class SkillBadgesModel {

    private String badgeName;
    private String badgeImage;
    private String badgeRank;
    private int badgePercentage;
    private int gaugeExp;
    private int star;
    private String skill;
    private int status;

    public SkillBadgesModel(String badgeName, String badgeImage, String badgeRank,int badgePercentage, int gaugeExp, int star, String skill, int status) {
        this.badgeName = badgeName;
        this.badgeImage = badgeImage;
        this.gaugeExp = gaugeExp;
        this.badgeRank = badgeRank;
        this.star  = star;
        this.skill = skill;
        this.status = status;
        this.badgePercentage = badgePercentage;
    }

    public SkillBadgesModel() {

    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSkill() {
        return this.skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
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

    public String getBadgeRank() {
        return badgeRank;
    }

    public void setBadgeRank(String badgeRank) {
        this.badgeRank = badgeRank;
    }

    public int getBadgePercentage() {
        return badgePercentage;
    }

    public void setBadgePercentage(int badgePercentage) {
        this.badgePercentage = badgePercentage;
    }
}
