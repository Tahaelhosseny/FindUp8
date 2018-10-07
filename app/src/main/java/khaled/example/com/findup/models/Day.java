package khaled.example.com.findup.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Day {
    @NonNull @PrimaryKey
    private int day_id;
    @NonNull
    private String day_name;

    public Day(@NonNull int day_id, @NonNull String day_name) {
        this.day_id = day_id;
        this.day_name = day_name;
    }

    @NonNull
    public int getDay_id() {
        return day_id;
    }

    public void setDay_id(@NonNull int day_id) {
        this.day_id = day_id;
    }

    @NonNull
    public String getDay_name() {
        return day_name;
    }

    public void setDay_name(@NonNull String day_name) {
        this.day_name = day_name;
    }
}
