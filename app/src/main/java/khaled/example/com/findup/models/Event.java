package khaled.example.com.findup.models;

/**
 * Created by khaled on 7/4/18.
 */

public class Event {
    private String eventName, eventDescription,eventDat, eventImg;

    public Event(String eventName, String eventDescription, String eventDat, String eventImg) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDat = eventDat;
        this.eventImg = eventImg;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDat() {
        return eventDat;
    }

    public void setEventDat(String eventDat) {
        this.eventDat = eventDat;
    }

    public String getEventImg() {
        return eventImg;
    }

    public void setEventImg(String eventImg) {
        this.eventImg = eventImg;
    }
}
