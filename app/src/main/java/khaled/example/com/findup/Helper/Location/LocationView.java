package khaled.example.com.findup.Helper.Location;

import android.location.Address;
import android.location.Location;

public interface LocationView {
    void onLocationUpdate(Location location);
    void onAddressUpdate(Address address);
    void onLocationSettingsUnsuccessful();
}
