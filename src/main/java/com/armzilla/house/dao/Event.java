package com.armzilla.house.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by arm on 1/2/15.
 */
@Document(indexName = "home", type = "events", shards = 1, replicas = 0)
public class Event {
    @Id
    private String id;
    private int deviceId;
    private String eventType;
    private int value;
    private int previousValue;
    private long time;
    private String serviceName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(int previousValue) {
        this.previousValue = previousValue;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", deviceId=" + deviceId +
                ", eventType='" + eventType + '\'' +
                ", value=" + value +
                ", previousValue=" + previousValue +
                ", time=" + time +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
