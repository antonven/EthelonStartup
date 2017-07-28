package myapps.wycoco.com.ethelonstartup.Models;

/**
 * Created by dell on 7/22/2017.
 */

public class ActivityModel {
    private String activityId;
    private String foundationId;
    private String activityName;
    private String activityImage;
    private String activityQr;
    private String activityDes;
    private String activityDate;
    private String activityLocation;
    private String activityStart;
    private String activityEnd;
    private String activityGroup;
    private String activityLong;
    private String activityLat;
    private String activityPoints;
    private String activityStatus;
    private String activityCreated;
    private String activityUpdated;


    public ActivityModel(String activityId, String foundationId, String activityName, String activityImage,
                         String activityQr, String activityDes,  String activityLocation,
                         String activityStart, String activityEnd, String activityDate, String activityGroup, String activityLong,
                         String activityLat,
                         String activityPoints ,String activityStatus, String activityCreated, String activityUpdated) {
        this.activityId = activityId;
        this.foundationId = foundationId;
        this.activityName = activityName;
        this.activityImage = activityImage;
        this.activityQr = activityQr;
        this.activityDes = activityDes;
        this.activityDate = activityDate;
        this.activityLocation = activityLocation;
        this.activityStart = activityStart;
        this.activityEnd = activityEnd;
        this.activityGroup = activityGroup;
        this.activityLong = activityLong;
        this.activityLat = activityLat;
        this.activityPoints = activityPoints;
        this.activityStatus = activityStatus;
        this.activityCreated = activityCreated;
        this.activityUpdated = activityUpdated;
    }

    public ActivityModel() {
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActvitiyId(String activityId) {
        this.activityId = activityId;
    }

    public String getFoundationId() {
        return foundationId;
    }

    public void setFoundationId(String foundationId) {
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

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
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

    public String getActivityGroup() {
        return activityGroup;
    }

    public void setActivityGroup(String activitySkill) {
        this.activityGroup = activitySkill;
    }



    public String getActivityPoints() {
        return activityPoints;
    }

    public void setActivityPoints(String activityPoints) {
        this.activityPoints = activityPoints;
    }

    public String getActivityLong() {
        return activityLong;
    }

    public void setActivityLong(String activityLong) {
        this.activityLong = activityLong;
    }

    public String getActivityLat() {
        return activityLat;
    }

    public void setActivityLat(String activityLat) {
        this.activityLat = activityLat;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getActivityCreated() {
        return activityCreated;
    }

    public void setActivityCreated(String activityCreated) {
        this.activityCreated = activityCreated;
    }

    public String getActivityUpdated() {
        return activityUpdated;
    }

    public void setActivityUpdated(String activityUpdated) {
        this.activityUpdated = activityUpdated;
    }
}
