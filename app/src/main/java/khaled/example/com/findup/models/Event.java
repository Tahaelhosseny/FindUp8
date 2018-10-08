package khaled.example.com.findup.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khaled on 7/4/18.
 */

@Entity
public class Event {
    @PrimaryKey
    @ColumnInfo(name = "event_id")
    private int event_id;
    @NonNull
    private String event_name;
    @NonNull
    private String event_desc;
    @NonNull
    private String event_start_date;
    @NonNull
    private String event_photo;
    @NonNull
    private String event_address;
    @NonNull
    private double event_latitude;
    @NonNull
    private double event_longitude;
    @NonNull
    private int publish_flag;
    @NonNull
    private int block_flag;
    @NonNull
    private int store_id;
    @NonNull
    private double event_cost;
    @NonNull
    private String event_time;
    @NonNull
    private String event_days;



    public Event(int event_id, String event_name, String event_desc, String event_start_date, String event_photo, String event_address, double event_latitude, double event_longitude, int publish_flag, int block_flag, int store_id, double event_cost, String event_time, String event_days) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_desc = event_desc;
        this.event_start_date = event_start_date;
        this.event_photo = event_photo;
        this.event_address = event_address;
        this.event_latitude = event_latitude;
        this.event_longitude = event_longitude;
        this.publish_flag = publish_flag;
        this.block_flag = block_flag;
        this.store_id = store_id;
        this.event_cost = event_cost;
        this.event_time = event_time;
        this.event_days = event_days;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public void setEvent_desc(String event_desc) {
        this.event_desc = event_desc;
    }

    public String getEvent_start_date() {
        return event_start_date;
    }

    public void setEvent_start_date(String event_start_date) {
        this.event_start_date = event_start_date;
    }

    public String getEvent_photo() {
        return event_photo;
    }

    public void setEvent_photo(String event_photo) {
        this.event_photo = event_photo;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEvent_address() {
        return event_address;
    }

    public void setEvent_address(String event_address) {
        this.event_address = event_address;
    }

    public double getEvent_latitude() {
        return event_latitude;
    }

    public void setEvent_latitude(double event_latitude) {
        this.event_latitude = event_latitude;
    }

    public double getEvent_longitude() {
        return event_longitude;
    }

    public void setEvent_longitude(double event_longitude) {
        this.event_longitude = event_longitude;
    }

    public int getPublish_flag() {
        return publish_flag;
    }

    public void setPublish_flag(int publish_flag) {
        this.publish_flag = publish_flag;
    }

    public int getBlock_flag() {
        return block_flag;
    }

    public void setBlock_flag(int block_flag) {
        this.block_flag = block_flag;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public double getEvent_cost() {
        return event_cost;
    }

    public void setEvent_cost(double event_cost) {
        this.event_cost = event_cost;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public String getEvent_days() {
        return event_days;
    }

    public void setEvent_days(String event_days) {
        this.event_days = event_days;
    }
}
