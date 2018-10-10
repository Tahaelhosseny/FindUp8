package khaled.example.com.findup.models;

public class Distance {
    private int distance_id;
    private String distance_name;
    private String distance_short_name;
    private int distance_parent_id;
    private int distance_parent_value;
    private int user_distance_id;

    public int getDistance_id() {
        return distance_id;
    }

    public void setDistance_id(int distance_id) {
        this.distance_id = distance_id;
    }

    public String getDistance_name() {
        return distance_name;
    }

    public void setDistance_name(String distance_name) {
        this.distance_name = distance_name;
    }

    public String getDistance_short_name() {
        return distance_short_name;
    }

    public void setDistance_short_name(String distance_short_name) {
        this.distance_short_name = distance_short_name;
    }

    public int getDistance_parent_id() {
        return distance_parent_id;
    }

    public void setDistance_parent_id(int distance_parent_id) {
        this.distance_parent_id = distance_parent_id;
    }

    public int getDistance_parent_value() {
        return distance_parent_value;
    }

    public void setDistance_parent_value(int distance_parent_value) {
        this.distance_parent_value = distance_parent_value;
    }

    public int getUser_distance_id() {
        return user_distance_id;
    }

    public void setUser_distance_id(int user_distance_id) {
        this.user_distance_id = user_distance_id;
    }
}
