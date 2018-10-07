package khaled.example.com.findup.models;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class CurrentLocation {
    private LatLng location;


    public CurrentLocation(LatLng location) {
        this.location = location;
    }

    public CurrentLocation() {
        location = new LatLng(0,0);
    }

    public CurrentLocation(double latitude , double longitude){
        location = new LatLng(latitude,longitude);
    }

    public boolean isEnabled() {
        if (location != null){
            if (location.longitude != 0 && location.latitude != 0)
                return true;
            else
                return false;
        }
        else return false;
    }

    public LatLng getLocation() {
        return location;
    }

    public Location getLocationModel() {
        Location location = new Location("current location");
        location.setLatitude(this.location.latitude);
        location.setLongitude(this.location.longitude);
        return location;
    }
    public void setLocation(LatLng location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CurrentLocation) {
            if (getLocation().longitude == ((CurrentLocation) obj).location.longitude
                    && getLocation().latitude == ((CurrentLocation) obj).location.latitude)
                return true;
            else
                return false;
        }else
            return false;
    }

    @Override
    public String toString() {
        return getLocation().latitude+" "+getLocation().longitude;
    }
}
