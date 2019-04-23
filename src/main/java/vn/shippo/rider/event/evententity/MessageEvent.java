package vn.shippo.rider.event.evententity;

import java.sql.Timestamp;

public class MessageEvent {
    private String eventName;
    private Timestamp eventTime;
    private String eventData;

    public MessageEvent(String eventName, Timestamp eventTime, String eventData) {
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventData = eventData;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }



}
