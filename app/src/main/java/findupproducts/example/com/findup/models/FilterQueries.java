package findupproducts.example.com.findup.models;

import retrofit2.http.Field;
import retrofit2.http.Query;

public class FilterQueries {

    private int account_id;
    private String search_text;
    private String filter_price;
    private String filter_rate;
    private String filter_opennow;
    private String filter_distance;
    private String search_from;
    private String longitude;
    private String latitude;
    private String filter_by;
    private String filter_byid;

    public void resetFilter(FilterQueries f){
        f.setAccount_id(0);
        f.setSearch_from("");
        f.setSearch_text("");
        f.setFilter_by("");
        f.setFilter_byid("");
        f.setFilter_opennow("");
        f.setFilter_distance("");
        f.setLatitude("");
        f.setLongitude("");
        f.setFilter_price("");
        f.setFilter_rate("");
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getSearch_text() {
        return search_text;
    }

    public void setSearch_text(String search_text) {
        this.search_text = search_text;
    }

    public String getFilter_price() {
        return filter_price;
    }

    public void setFilter_price(String filter_price) {
        this.filter_price = filter_price;
    }

    public String getFilter_rate() {
        return filter_rate;
    }

    public void setFilter_rate(String filter_rate) {
        this.filter_rate = filter_rate;
    }

    public String getFilter_opennow() {
        return filter_opennow;
    }

    public void setFilter_opennow(String filter_opennow) {
        this.filter_opennow = filter_opennow;
    }

    public String getFilter_distance() {
        return filter_distance;
    }

    public void setFilter_distance(String filter_distance) {
        this.filter_distance = filter_distance;
    }

    public String getSearch_from() {
        return search_from;
    }

    public void setSearch_from(String search_from) {
        this.search_from = search_from;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getFilter_by() {
        return filter_by;
    }

    public void setFilter_by(String filter_by) {
        this.filter_by = filter_by;
    }

    public String getFilter_byid() {
        return filter_byid;
    }

    public void setFilter_byid(String filter_byid) {
        this.filter_byid = filter_byid;
    }
}
