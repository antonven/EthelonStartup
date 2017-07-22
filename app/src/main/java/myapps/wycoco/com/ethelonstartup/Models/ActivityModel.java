package myapps.wycoco.com.ethelonstartup.Models;

/**
 * Created by dell on 7/22/2017.
 */

public class ActivityModel {
    private int activityId;
    private int foundationId;
    private String activityName;
    private String activityImage;
    private String activityQr;
    private String activityDes;
    private String activityLocation;
    private String contactId;
    private String activityStart;
    private String activityEnd;
    private String activitySkill;
    private String activityAddress;
    private String activityPoints;


    public ActivityModel(int activityId, int foundationId, String activityName, String activityImage, String activityQr, String activityDes, String activityLocation, String contactId, String activityStart, String activityEnd, String activitySkill, String activityAddress, String activityPoints) {
        this.activityId = activityId;
        this.foundationId = foundationId;
        this.activityName = activityName;
        this.activityImage = activityImage;
        this.activityQr = activityQr;
        this.activityDes = activityDes;
        this.activityLocation = activityLocation;
        this.contactId = contactId;
        this.activityStart = activityStart;
        this.activityEnd = activityEnd;
        this.activitySkill = activitySkill;
        this.activityAddress = activityAddress;
        this.activityPoints = activityPoints;
    }

    public ActivityModel() {
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActvitiyId(int activityId) {
        this.activityId = activityId;
    }

    public int getFoundationId() {
        return foundationId;
    }

    public void setFoundationId(int foundationId) {
        this.foundationId = foundationId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(String activityImage) {
        this.activityImage = activityImage;
    }

    public String getActivityQr() {
        return activityQr;
    }

    public void setActivityQr(String activityQr) {
        this.activityQr = activityQr;
    }

    public String getActivityDes() {
        return activityDes;
    }

    public void setActivityDes(String activityDes) {
        this.activityDes = activityDes;
    }

    public String getActivityLocation() {
        return activityLocation;
    }

    public void setActivityLocation(String activityLocation) {
        this.activityLocation = activityLocation;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getActivityStart() {
        return activityStart;
    }

    public void setActivityStart(String activityStart) {
        this.activityStart = activityStart;
    }

    public String getActivityEnd() {
        return activityEnd;
    }

    public void setActivityEnd(String activityEnd) {
        this.activityEnd = activityEnd;
    }

    public String getActivitySkill() {
        return activitySkill;
    }

    public void setActivitySkill(String activitySkill) {
        this.activitySkill = activitySkill;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public String getActivityPoints() {
        return activityPoints;
    }

    public void setActivityPoints(String activityPoints) {
        this.activityPoints = activityPoints;
    }
}
