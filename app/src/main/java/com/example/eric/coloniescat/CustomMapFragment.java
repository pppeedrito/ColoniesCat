package com.example.eric.coloniescat;

/**
 * Created by Eric on 24/02/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;

public class CustomMapFragment extends SupportMapFragment {

    private static final String LOG_TAG = "CustomMapFragment";

    public CustomMapFragment(){
        super();
    }

    public static CustomMapFragment newInstance(){
        CustomMapFragment fragment = new CustomMapFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2) {
        View v = super.onCreateView(arg0, arg1, arg2);
        Fragment fragment = getParentFragment();
        if (fragment != null && fragment instanceof OnMapReadyListener) {
            ((OnMapReadyListener) fragment).onMapReadyA();
        }
        return v;
    }

    /**
     * Listener interface to tell when the map is ready
     */

    public static interface OnMapReadyListener {

        void onMapReadyA();
    }
}
