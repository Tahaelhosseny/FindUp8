package khaled.example.com.findup.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khaled on 7/4/18.
 */

@Entity
public class Event {

    @PrimaryKey @NonNull
    private String event_id;
    private String event_name;
    private String event_photo;
    private String event_start_date;
    private String event_days;
    private String event_time;
    private String event_cost;
    private String event_desc;
    private String event_address;
    private String store_id;
    private String event_longitude;
    private String event_latitude;
    private String publish_flag;
    private String block_flag;
    private String if_saved;

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_photo() {
        return event_photo;
    }

    public void setEvent_photo(String event_photo) {
        this.event_photo = event_photo;
    }

    public String getEvent_start_date() {
        return event_start_date;
    }

    public void setEvent_start_date(String event_start_date) {
        this.event_start_date = event_start_date;
    }

    public String getEvent_days() {
        return event_days;
    }

    public void setEvent_days(String event_days) {
        this.event_days = event_days;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public String getEvent_cost() {
        return event_cost;
    }

    public void setEvent_cost(String event_cost) {
        this.event_cost = event_cost;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public void setEvent_desc(String event_desc) {
        this.event_desc = event_desc;
    }

    public String getEvent_address() {
        return event_address;
    }

    public void setEvent_address(String event_address) {
        this.event_address = event_address;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getEvent_longitude() {
        return event_longitude;
    }

    public void setEvent_longitude(String event_longitude) {
        this.event_longitude = event_longitude;
    }

    public String getEvent_latitude() {
        return event_latitude;
    }

    public void setEvent_latitude(String event_latitude) {
        this.event_latitude = event_latitude;
    }

    public String getPublish_flag() {
        return publish_flag;
    }

    public void setPublish_flag(String publish_flag) {
        this.publish_flag = publish_flag;
    }

    public String getBlock_flag() {
        return block_flag;
    }

    public void setBlock_flag(String block_flag) {
        this.block_flag = block_flag;
    }

    public String getIf_saved() {
        return if_saved;
    }

    public void setIf_saved(String if_saved) {
        this.if_saved = if_saved;
    }
}
