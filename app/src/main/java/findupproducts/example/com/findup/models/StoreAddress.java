package findupproducts.example.com.findup.models;

public class StoreAddress {
    private int store_id;
    private double longitute;
    private double latitude;
    private String days;
    private String time;

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public double getLongitute() {
        return longitute;
    }

    public void setLongitute(double longitute) {
        this.longitute = longitute;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
//"store_id": "1",
//        "longitude": "39.86533563584089",
//        "latitude": "21.412829884564413",
//        "days": "Thursday - Wednesday",
//        "time": "from 6 to 9"