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
    private String activityStartDate;
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
    private String contactPerson;
    private String activityContact;
    private String foundationName;
    private int volunteerCount;
    private String volunteerStatus;
    private String foundationImage;


    public ActivityModel(String activityId, String foundationId, String activityName, String activityImage,
                         String activityQr, String activityDes, String activityLocation,
                         String activityStart, String activityEnd, String activityStartDate, String activityGroup, String activityLong,
                         String activityLat,
                         String activityPoints, String activityStatus, String activityCreated, String activityUpdated, String contactPerson,
                         String activityContact, String foundationName, int volunteerCount, String volunteerStatus, String foundationImage) {
        this.activityId = activityId;
        this.foundationId = foundationId;
        this.activityName = activityName;
        this.activityImage = activityImage;
        this.activityQr = activityQr;
        this.activityDes = activityDes;
        this.activityStartDate = activityStartDate;
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
        this.contactPerson = contactPerson;
        this.activityContact = activityContact;
        this.foundationName = foundationName;
        this.volunteerCount = volunteerCount;
        this.volunteerStatus = volunteerStatus;
        this.foundationImage = foundationImage;
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
        return activityStartDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityStartDate = activityDate;
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

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getActivityContact() {
        return activityContact;
    }

    public void setActivityContact(String activityContact) {
        this.activityContact = activityContact;
    }

    public String getFoundationName() {
        return foundationName;
    }

    public void setFoundationName(String foundationName) {
        this.foundationName = foundationName;
    }

    public int getVolunteerCount() {
        return volunteerCount;
    }

    public void setVolunteerCount(int volunteerCount) {
        this.volunteerCount = volunteerCount;
    }

    public String getVolunteerStatus() {
        return volunteerStatus;
    }

    public void setVolunteerStatus(String volunteerStatus) {
        this.volunteerStatus = volunteerStatus;
    }

    public String getFoundationImage() {
        return foundationImage;
    }

    public void setFoundationImage(String foundationImage) {
        this.foundationImage = foundationImage;
    }
}
