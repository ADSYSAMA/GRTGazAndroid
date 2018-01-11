package com.example.adsy.grtgaz;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;


public class DptActivity extends Activity {

    ListView view_dpt;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dpt);

        view_dpt = (ListView)findViewById(R.id.view_dpt);
        ArrayList<String> arrayDPT = new ArrayList<>();
        arrayDPT.addAll(Arrays.asList(getResources().getStringArray(R.array.dpt)));

        adapter = new ArrayAdapter<String>(
                DptActivity.this,
                android.R.layout.simple_list_item_1,
                arrayDPT
        );

        view_dpt.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.search_dpt);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return  super.onCreateOptionsMenu(menu);
    }
}