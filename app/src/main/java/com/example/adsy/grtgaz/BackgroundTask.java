package com.example.adsy.grtgaz;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.adsy.grtgaz.Region;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class BackgroundTask {

    Context context;
    ArrayList<Region> arrayList = new ArrayList<>();
    String json_url = "https://geo.api.gouv.fr/regions";

    public  BackgroundTask (Context context){
        this.context = context;
    }

    public ArrayList<Region> getList(){

        JsonArrayRequest jsonArrayRequest =  new JsonArrayRequest(Request.Method.GET, json_url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count < response.length()){
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                Region region = new Region(jsonObject.getString("code"),
                                        jsonObject.getString("nom"));
                                arrayList.add(region);
                                Log.i("JJ", "Le tableau contient actuellement " + arrayList.size() + " éléments "+jsonObject.getString("nom"));

                                count++;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context,"Error....", Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        });

        MySingleton.getInstance(context).addToRequestque(jsonArrayRequest);

        Log.i("JJ", "Le tableau contient " + arrayList.size() + "  éléments");
        return arrayList;

    }
}
