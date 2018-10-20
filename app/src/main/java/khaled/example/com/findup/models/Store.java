package khaled.example.com.findup.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import khaled.example.com.findup.Helper.Location.LocationUtility;
import khaled.example.com.findup.Helper.Utility;

/**
 * Created by khaled on 7/4/18.
 */
@Entity
public class Store {
//    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "store_id")
    private int store_id;
//    @NonNull
    @ColumnInfo(name = "store_name")
    private String store_name;
    @ColumnInfo(name = "store_otherlang")
    private String store_otherlang;
//    @NonNull

    public String getStore_otherlang() {
        return store_otherlang;
    }

    public void setStore_otherlang(String store_otherlang) {
        this.store_otherlang = store_otherlang;
    }

    @ColumnInfo(name = "store_banner")
    private String store_banner;
//    @NonNull
    @ColumnInfo(name = "store_logo")
    private String store_logo;
    @ColumnInfo(name = "placeReview")
    private String placeReview;
//    @NonNull
    @ColumnInfo(name = "store_desc")
    private String store_desc;
//    @NonNull
    @ColumnInfo(name = "store_about")
    private String store_about;
//    @NonNull
    @ColumnInfo(name = "store_tags")
    private String store_tags;
//    @NonNull
    @ColumnInfo(name = "store_mobile")
    private String store_mobile;
//    @NonNull
    @ColumnInfo(name = "store_longitude")
    private Double store_longitude;
//    @NonNull
    @ColumnInfo(name = "store_latitude")
    private Double store_latitude;
//    @NonNull
    @ColumnInfo(name = "store_country_id")
    private String store_country_id;
//    @NonNull
    @ColumnInfo(name = "country_name_en")
    private String country_name_en;
//    @NonNull
    @ColumnInfo(name = "store_city_id")
    private String store_city_id;
//    @NonNull
    @ColumnInfo(name = "city_name_en")
    private String city_name_en;
//    @NonNull
    @ColumnInfo(name = "store_location_type")
    private String store_location_type;
//    @NonNull
    @ColumnInfo(name = "store_location_address")
    private String store_location_address;
//    @NonNull
    @ColumnInfo(name = "store_cat_id")
    private String store_cat_id;
//    @NonNull
    @ColumnInfo(name = "store_website_link")
    private String store_website_link;
//    @NonNull
    @ColumnInfo(name = "store_twitter_link")
    private String store_twitter_link;
    @ColumnInfo(name = "store_facebook_link")
    private String store_facebook_link;

    public String getStore_facebook_link() {
        return store_facebook_link;
    }

    public void setStore_facebook_link(String store_facebook_link) {
        this.store_facebook_link = store_facebook_link;
    }

    //    @NonNull
    @ColumnInfo(name = "store_instegram_link")
    private String store_instegram_link;
//    @NonNull
    @ColumnInfo(name = "store_gmail_link")
    private String store_gmail_link;

//    @NonNull
    @ColumnInfo(name = "store_rating")
    private String store_rating;

    @ColumnInfo(name = "if_saved")
    private int if_saved;

    @Ignore
    private List<StorePhoto> store_images;
    @Ignore
    private List<Comment> store_comments;
    @Ignore
    private List<Product> store_products;
    //@Ignore
    private String store_workdays;

    public String getStore_workdays() {
        return store_workdays;
    }

    public void setStore_workdays(String store_workdays) {
        this.store_workdays = store_workdays;
    }
    //@Ignore
    //private Store_WorkTime store_worktime;

    @Ignore
    public Store(){}

    public Store(int store_id, String store_name, String store_banner, String placeReview, String store_desc) {
        this.store_id = store_id;
        this.store_name = store_name;
        //this.placeDistane = "10km";
        this.store_banner = store_banner;
        this.placeReview = placeReview;
        this.store_desc = store_desc;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getPlaceDistane(Context mContext,Location location) {
        Location store_location = new Location(getStore_name());
        store_location.setLatitude(getStore_latitude());
        store_location.setLongitude(getStore_longitude());
        return LocationUtility.DisToS(mContext,LocationUtility.CalcDistance(store_location,location));
    }
    public Float getPlaceDistaneFloat(Location location) {
        Location store_location = new Location(getStore_name());
        store_location.setLatitude(getStore_latitude());
        store_location.setLongitude(getStore_longitude());
        return LocationUtility.CalcDistance(store_location,location);
    }
    ;
    public String getStore_banner() {
        return store_banner;
    }

    public void setStore_banner(String store_banner) {
        this.store_banner = store_banner;
    }

    public String getPlaceReview() {
        return placeReview;
    }

    public void setPlaceReview(String placeReview) {
        this.placeReview = placeReview;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getStore_desc() {
        return store_desc;
    }

    public String getStore_logo() {
        return store_logo;
    }

    public void setStore_logo(String store_logo) {
        this.store_logo = store_logo;
    }

    public void setStore_desc(String store_desc) {
        this.store_desc = store_desc;
    }

    public String getStore_about() {
        return store_about;
    }

    public void setStore_about(String store_about) {
        this.store_about = store_about;
    }

    public String getStore_tags() {
        return store_tags;
    }

    public void setStore_tags(String store_tags) {
        this.store_tags = store_tags;
    }

    public String getStore_mobile() {
        return store_mobile;
    }

    public void setStore_mobile(String store_mobile) {
        this.store_mobile = store_mobile;
    }

    public Double getStore_longitude() {
        return store_longitude;
    }

    public void setStore_longitude(Double store_longitude) {
        this.store_longitude = store_longitude;
    }

    public Double getStore_latitude() {
        return store_latitude;
    }

    public void setStore_latitude(double store_latitude) {
        this.store_latitude = store_latitude;
    }

    public String getStore_country_id() {
        return store_country_id;
    }

    public void setStore_country_id(String store_country_id) {
        this.store_country_id = store_country_id;
    }

    public String getCountry_name_en() {
        return country_name_en;
    }

    public void setCountry_name_en(String country_name_en) {
        this.country_name_en = country_name_en;
    }

    public String getStore_city_id() {
        return store_city_id;
    }

    public void setStore_city_id(String store_city_id) {
        this.store_city_id = store_city_id;
    }

    public String getCity_name_en() {
        return city_name_en;
    }

    public void setCity_name_en(String city_name_en) {
        this.city_name_en = city_name_en;
    }

    public String getStore_location_type() {
        return store_location_type;
    }

    public void setStore_location_type(String store_location_type) {
        this.store_location_type = store_location_type;
    }

    public String getStore_location_address() {
        return store_location_address;
    }

    public void setStore_location_address(String store_location_address) {
        this.store_location_address = store_location_address;
    }

    public String getStore_cat_id() {
        return store_cat_id;
    }

    public void setStore_cat_id(String store_cat_id) {
        this.store_cat_id = store_cat_id;
    }

    public String getStore_website_link() {
        return store_website_link;
    }

    public void setStore_website_link(String store_website_link) {
        this.store_website_link = store_website_link;
    }

    public String getStore_twitter_link() {
        return store_twitter_link;
    }

    public void setStore_twitter_link(String store_twitter_link) {
        this.store_twitter_link = store_twitter_link;
    }

    public String getStore_instegram_link() {
        return store_instegram_link;
    }

    public void setStore_instegram_link(String store_instegram_link) {
        this.store_instegram_link = store_instegram_link;
    }

    public String getStore_gmail_link() {
        return store_gmail_link;
    }

    public void setStore_gmail_link(String store_gmail_link) {
        this.store_gmail_link = store_gmail_link;
    }

    /*

    public List<Day> getStore_workdays() {
        return store_workdays;
    }

    public void setStore_workdays(List<Day> store_workdays) {
        this.store_workdays = store_workdays;
    }

    public Store_WorkTime getStore_worktime() {
        return store_worktime;
    }

    public void setStore_worktime(Store_WorkTime store_worktime) {
        this.store_worktime = store_worktime;
    }

    */

    public String getStore_rating() {
        return store_rating;
    }

    public void setStore_rating(String store_rating) {
        this.store_rating = store_rating;
    }

    public List<StorePhoto> getStore_images() {
        return store_images;
    }

    public void setStore_images(List<StorePhoto> store_images) {
        this.store_images = store_images;
    }

    public List<Comment> getStore_comments() {
        return store_comments;
    }

    public void setStore_comments(List<Comment> store_comments) {
        this.store_comments = store_comments;
    }

    public List<Product> getStore_products() {
        return store_products;
    }

    public void setStore_products(List<Product> store_products) {
        this.store_products = store_products;
    }

    public int getIf_saved() {
        return if_saved;
    }

    public void setIf_saved(int if_saved) {
        this.if_saved = if_saved;
    }
}
