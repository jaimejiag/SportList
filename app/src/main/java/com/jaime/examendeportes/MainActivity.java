package com.jaime.examendeportes;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.jaime.examendeportes.adapters.SportAdapter;
import com.jaime.examendeportes.model.Sport;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_LIKES = "like_key";
    private static final String KEY_FILTER = "filter_key";

    private Button mBtnAccept;
    private ListView mListSport;
    private SportAdapter adapter;
    private Preferences preferences;
    private boolean[] likesRecovery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = new Preferences(this);
        preferences.LoadPreferences();
        adapter = new SportAdapter(this);
        mListSport = (ListView) findViewById(R.id.lstv_items);

        if (savedInstanceState != null) {
            likesRecovery = savedInstanceState.getBooleanArray(KEY_LIKES);

            for (int i = 0; i < likesRecovery.length; i++)
                adapter.getItem(i).setLike(likesRecovery[i]);
        }

        mListSport.setAdapter(adapter);

        mBtnAccept = (Button) findViewById(R.id.btn_accept);
        mBtnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.savePreferences();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        likesRecovery = new boolean[adapter.getCount()];

        for (int i = 0; i < adapter.getCount(); i++)
            likesRecovery[i] = adapter.getItem(i).isLike();

        outState.putBooleanArray(KEY_LIKES, likesRecovery);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sportlist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_filter:
                filter();
                adapter.notifyDataSetChanged();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void filter() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.filter_dialog, null);
        final EditText edt = (EditText) view.findViewById(R.id.edt_filterText);

        builder.setTitle("Filtrar");
        builder.setMessage("Escribe una letra para filtrar los deportes por su inicial.");
        builder.setView(edt);

        builder.setPositiveButton("Filtrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                adapter.showFilteredContent(edt.getText().toString());
            }
        }).create();

        ViewGroup viewGroup = (ViewGroup) view;
        viewGroup.removeAllViews();
        builder.show();
    }
}
