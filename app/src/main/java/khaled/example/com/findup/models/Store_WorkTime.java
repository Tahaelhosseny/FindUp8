package khaled.example.com.findup.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = @ForeignKey(entity = Store.class,
        parentColumns = "store_id",
        childColumns = "store_id",
        onDelete = ForeignKey.CASCADE))

public class Store_WorkTime {
    @NonNull @PrimaryKey
    private int worktime_id;

    @NonNull
    private int workday_id;

    @NonNull
    private String work_from_time;

    @NonNull
    private String work_to_time;

    @NonNull
    private int store_id;

    public Store_WorkTime(@NonNull int worktime_id, @NonNull int workday_id, @NonNull String work_from_time, @NonNull String work_to_time, @NonNull int store_id) {
        this.worktime_id = worktime_id;
        this.workday_id = workday_id;
        this.work_from_time = work_from_time;
        this.work_to_time = work_to_time;
        this.store_id = store_id;
    }

    @NonNull
    public int getWorktime_id() {
        return worktime_id;
    }

    public void setWorktime_id(@NonNull int worktime_id) {
        this.worktime_id = worktime_id;
    }

    @NonNull
    public int getWorkday_id() {
        return workday_id;
    }

    public void setWorkday_id(@NonNull int workday_id) {
        this.workday_id = workday_id;
    }

    @NonNull
    public String getWork_from_time() {
        return work_from_time;
    }

    public void setWork_from_time(@NonNull String work_from_time) {
        this.work_from_time = work_from_time;
    }

    @NonNull
    public String getWork_to_time() {
        return work_to_time;
    }

    public void setWork_to_time(@NonNull String work_to_time) {
        this.work_to_time = work_to_time;
    }

    @NonNull
    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(@NonNull int store_id) {
        this.store_id = store_id;
    }
}
