package khaled.example.com.findup.models;

/**
 * Created by khaled on 7/4/18.
 */

public class Place {
    private String placeName, placeDistane,placeImg, placeReview;

    public Place(String placeName, String placeDistane, String placeImg, String placeReview) {
        this.placeName = placeName;
        this.placeDistane = placeDistane;
        this.placeImg = placeImg;
        this.placeReview = placeReview;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceDistane() {
        return placeDistane;
    }

    public void setPlaceDistane(String placeDistane) {
        this.placeDistane = placeDistane;
    }

    public String getPlaceImg() {
        return placeImg;
    }

    public void setPlaceImg(String placeImg) {
        this.placeImg = placeImg;
    }

    public String getPlaceReview() {
        return placeReview;
    }

    public void setPlaceReview(String placeReview) {
        this.placeReview = placeReview;
    }
}
