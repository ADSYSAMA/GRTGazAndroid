package com.example.adsy.grtgaz;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Region> arrayList = new ArrayList<>();
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boutonRegion  = (Button)findViewById(R.id.boutonRegion);
        BackgroundTask backgroundTask = new BackgroundTask(MainActivity.this);
        arrayList = backgroundTask.getList();
        Button btnMap = findViewById(R.id.boutonMap);

        boutonRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                creerTextViewRegion(arrayList);
            }
        });

        if (isServicesOK()) {

            init();
        }

    }

    private void init(){
        //setup du bouton qui va appeler l'activité MAPSACTIVITY
        Button btnMap = findViewById(R.id.boutonMap);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

    }
    public boolean isServicesOK(){
        //Retourne l'état des service google
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //L'utilisateur peut utiliser la carte
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        //une erreur qui peut être résolu
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;

    }

    public void creerTextViewRegion(ArrayList<Region> arrayList) {
        LinearLayout layoutRegion = (LinearLayout) findViewById(R.id.layoutRegion);
        Log.i("bb", "j'appele ma fonction creerTextViewRegion : " );
        for (int i = 0; i < arrayList.size(); i++) {
            TextView textRegion = new TextView(MainActivity.this);
            Region r = arrayList.get(i);
            Log.i("aa", "élément n : " + i + "  " + r.getNom());
            textRegion.setText(r.getNom());
            layoutRegion.addView(textRegion);

        }

}}
