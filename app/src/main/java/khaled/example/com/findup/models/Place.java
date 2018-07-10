package khaled.example.com.findup.models;

/**
 * Created by khaled on 7/4/18.
 */

public class Place {
    private int placeID;
    private String placeName;
    private String placeDistane;
    private String placeImg;
    private String placeReview;
    private String placeShortDescription;


    public Place(int placeID, String placeName, String placeDistane, String placeImg, String placeReview, String placeShortDescription) {
        this.placeID = placeID;
        this.placeName = placeName;
        this.placeDistane = placeDistane;
        this.placeImg = placeImg;
        this.placeReview = placeReview;
        this.placeShortDescription = placeShortDescription;
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

    public int getPlaceID() {
        return placeID;
    }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }

    public String getPlaceShortDescription() {
        return placeShortDescription;
    }
}
