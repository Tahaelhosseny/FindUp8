package khaled.example.com.findup.Helper.Location;


import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.LatLng;
import com.patloew.rxlocation.RxLocation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.adapters.NearMeAdapter;
import khaled.example.com.findup.models.CurrentLocation;
import khaled.example.com.findup.models.Store;

public class LocationUtility {

    static LocationView view;
    private final CompositeDisposable disposable = new CompositeDisposable();

    private final RxLocation rxLocation;
    private final LocationRequest locationRequest;

    public LocationUtility(RxLocation rxLocation) {
        this.rxLocation = rxLocation;

        this.locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5000);
    }

    public void attachView(LocationView view) {
        this.view = view;
        startLocationRefresh();
    }

    public void detachView() {
        this.view = null;
        disposable.clear();
    }

    public void startLocationRefresh() {
        disposable.add(
                rxLocation.settings().checkAndHandleResolution(locationRequest)
                        .flatMapObservable(this::getAddressObservable)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::onAddressUpdate, throwable -> Log.e("MainPresenter", "Error fetching location/address updates", throwable))
        );
    }

    private Observable<Address> getAddressObservable(boolean success) {
        if(success) {
            return rxLocation.location().updates(locationRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(view::onLocationUpdate)
                    .flatMap(this::getAddressFromLocation);

        } else {
            view.onLocationSettingsUnsuccessful();

            return rxLocation.location().lastLocation()
                    .doOnSuccess(view::onLocationUpdate)
                    .flatMapObservable(this::getAddressFromLocation);
        }
    }

    private Observable<Address> getAddressFromLocation(Location location) {
        return rxLocation.geocoding().fromLocation(location).toObservable()
                .subscribeOn(Schedulers.io());
    }


    public static CurrentLocation locationToCurrentLocation(double lat, double longitude) {
        return new CurrentLocation(lat, longitude);
    }

    public static CurrentLocation currentLocation = new CurrentLocation();
    public static void LatitudeToAdapter(double latitude, NearMeAdapter adapter){
        currentLocation.setLocation(new LatLng(latitude,currentLocation.getLocation().longitude));
        ChangeLocationAdapter(adapter);
    }
    public static void LongitudeToAdapter(double longitude, NearMeAdapter adapter){
        currentLocation.setLocation(new LatLng(currentLocation.getLocation().latitude,longitude));
        ChangeLocationAdapter(adapter);
    }

    public static void LatitudeToCurrentLocationModel(double latitude, CurrentLocation currentLocation){
        currentLocation.setLocation(new LatLng(latitude,currentLocation.getLocation().longitude));
    }
    public static void LongitudeToCurrentLocationModel(double longitude, CurrentLocation currentLocation){
        currentLocation.setLocation(new LatLng(currentLocation.getLocation().latitude,longitude));
    }

    private static void ChangeLocationAdapter(NearMeAdapter adapter){
        adapter.setCurrentLocation(currentLocation);
        adapter.notifyDataSetChanged();
    }

    public static float CalcDistance(Location A, Location B) {
        return A.distanceTo(B);
    }
    public static String DisToS(Context mContext,float distance){
        if (distance >= 1000)
            return String.format("%.1f", distance/1000).concat(mContext.getResources().getString(R.string.km));
        else
            return distance+""+mContext.getResources().getString(R.string.m);

    }

    public static List<Store> SortStoresByNearest(Context mContext,final List<Store> storeList, final Location location) {
        if (location != null) {
            Collections.sort(storeList, new Comparator<Store>() {
                public int compare(Store o1, Store o2) {
                    return (int) (o2.getPlaceDistaneFloat(location) - (o1.getPlaceDistaneFloat(location)));
                }
            });
            Collections.reverse(storeList);
        }
        return storeList;
    }

}
