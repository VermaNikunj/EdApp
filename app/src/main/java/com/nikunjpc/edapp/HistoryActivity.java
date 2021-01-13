package com.nikunjpc.edapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_info );

        recyclerView = findViewById( R.id.recyclerViewhistory );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
//        recyclerView.setHasFixedSize( true );

        Toast.makeText( this, "0000000000", Toast.LENGTH_SHORT ).show();

        HistoryDatabaseHelperClass databaseHelperClass = new HistoryDatabaseHelperClass( this );

        List<HistoryModelClass> modelClasses = databaseHelperClass.getList();

        Toast.makeText( this, "1111111111", Toast.LENGTH_SHORT ).show();

        if(modelClasses.size()>0)
        {
            Toast.makeText( this, "2222222222", Toast.LENGTH_SHORT ).show();

//            LinearLayoutManager llm = new LinearLayoutManager( this );
//            llm.setOrientation( LinearLayoutManager.VERTICAL );
//            recyclerView.setLayoutManager( llm );
//            recyclerView.setHasFixedSize( true );

//            Toast.makeText( this, "3333333333", Toast.LENGTH_SHORT ).show();

            HistoryAdapterClass adapterClass = new HistoryAdapterClass(modelClasses, getApplicationContext());

            Toast.makeText( this, "444444444444", Toast.LENGTH_SHORT ).show();

            recyclerView.setAdapter( adapterClass );

            Toast.makeText( this, "555555555", Toast.LENGTH_SHORT ).show();

        }
        else Toast.makeText( this, "There is no history", Toast.LENGTH_SHORT ).show();

    }
}