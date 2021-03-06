package com.example.eric.coloniescat;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.Frame;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentCasaCaritat.OnFragmentInteractionListener,
        FragmentPlacaAngels.OnFragmentInteractionListener, OnMapReadyCallback, FragmentEsglesiaLlatzer.OnFragmentInteractionListener, FragmentEdificiModern.OnFragmentInteractionListener,
        FragmentCasaConv.OnFragmentInteractionListener, FragmentAjuntament.OnFragmentInteractionListener, FragmentTeatra.OnFragmentInteractionListener{

    //crear suport map fragment per poder crear el FragmentMap

     SupportMapFragment sM;
   // private GoogleMap mMap;

    private Marker marcador;
    double latitut = 0;
    double longitud =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sM=SupportMapFragment.newInstance();


      //  setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = null;
      final  android.support.v4.app.FragmentManager fM= getSupportFragmentManager();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sM.isAdded()){
                    fM.beginTransaction().add(R.id.map, sM).commit();
                }else{
                    fM.beginTransaction().show(sM).commit();
                }

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        if(!sM.isAdded()){
            fM.beginTransaction().add(R.id.map, sM).commit();
        }else{
            fM.beginTransaction().show(sM).commit();
        }


        sM.getMapAsync(this);
    }



    /*private SupportMapFragment getMapAsync(MainActivity mainActivity) {



    }*/



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //És el botó dels 3 punts que tenim
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    //Aquest es el mètode que té el nav drawer.
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //Creem una flag que ens servirà per saber si estem en un Fragment o no

        boolean FragmentTransaction = false;
        Fragment fragment = null;
        android.support.v4.app.FragmentManager fM= getSupportFragmentManager();



        if(sM.isAdded())  fM.beginTransaction().hide(sM).commit();

        if (id == R.id.Casa_de_La_Caritat) {
            // Carragarem el Fragmet aquí, fragment Estàtic

            fragment = new FragmentCasaCaritat();
            FragmentTransaction = true;
        } else if (id == R.id.pl_angels) {

            fragment = new FragmentPlacaAngels();
            FragmentTransaction = true;

        } else if (id == R.id.nav_map) {


            /*if(!sM.isAdded()){
                fM.beginTransaction().add(R.id.map, sM).commit();
            }else{
               // fM.getView().setVisibility(View.INVISIBLE);
               ///this.getSupportFragmentManager().beginTransaction().hide(sM).commit();
             //   getActivity().getWindow().findViewById(CONTENT_VIEW_ID);

                getFragmentManager().beginTransaction().detach(fragment).commit();
            }*/
            if(!sM.isAdded()){
                fM.beginTransaction().add(R.id.map, sM).commit();
            }else{
                fM.beginTransaction().show(sM).commit();
            }



        } else if (id == R.id.esglesia_llatzer) {
            fragment = new FragmentEsglesiaLlatzer();
            FragmentTransaction = true;

        } else if (id == R.id.Casa_de_Convalescencia) {

            fragment = new FragmentCasaConv();
            FragmentTransaction = true;

        } else if (id == R.id.teatra_raval) {
            fragment = new FragmentTeatra();
            FragmentTransaction = true;

        }else if (id== R.id.ajuntament){
            fragment = new FragmentAjuntament();
            FragmentTransaction = true;

        }else if (id ==R.id.edifici_modern){
            fragment = new FragmentEdificiModern();
            FragmentTransaction = true;
        }


        /*El replace el carragem en el content main que és el cos de l'activity
        *
        *
        *
        *
        * */

        //.remove per mirar si el fragment es borra un cop passem a un altre fragment
        if(FragmentTransaction){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main, fragment)
                    .commit();


            //Aquestes dues lineas faran que el titol del lloc tingui el mateix nom que el de la nav bar
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Marker afegirMarcadorMapa(double lat, double lng, String text, GoogleMap mMap){
        LatLng coordenades = new LatLng(lat, lng);

        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenades)
                .title(text)
                .icon(BitmapDescriptorFactory.fromResource((R.mipmap.marcadomapa))));

        return (marcador);
    }

    @Override
    public void onMapReady(GoogleMap googleMap){


        Marker[] llM = new Marker[7];
        llM[0] = afegirMarcadorMapa(41.3837963, 2.1667484, "Grafitti, Casa de la Caritat", googleMap);
        llM[1] = afegirMarcadorMapa(41.3827189, 2.1652982, "Convent Dels Angels", googleMap);
        llM[2] = afegirMarcadorMapa(41.3796194,2.1641434 , "Esglèsia de Sant Llàtzer", googleMap);
        llM[3] = afegirMarcadorMapa(41.4132187,2.1734126,   "Casa de Convalescència", googleMap);
        llM[4] = afegirMarcadorMapa(41.3793014,2.1628403, "Teatre del Raval", googleMap);
        llM[5] = afegirMarcadorMapa(41.3822713,2.1753178, "Ajuntament del barri ", googleMap);
        llM[6] = afegirMarcadorMapa(41.3866286,2.1692277, "Edificis del Modernisme", googleMap);
        llM[6].setDraggable(true);

        CameraUpdate mevaUbicacio = CameraUpdateFactory.newLatLngZoom(new LatLng(llM[0].getPosition().latitude,llM[0].getPosition().longitude), 15);
        googleMap.animateCamera(mevaUbicacio);

    }
    //mètode implementat per el Fragment per poder fer la comunicacio amb Fragments
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}


/*
* public void onMapReady(GoogleMap googleMap){
* lim[0] = afegirMarcadorMapa("Lat i Long", googleMap)
*
* }
*
* private Marker afegirmarcador(double lat, double long, googleMap mMap){
*
* LatLong coordenades = new Latlng
*
*
* }
*
* */