package com.nikunjpc.edapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.widget.Toast;

public class AllFilesView extends AppCompatActivity {

    RecyclerView recyclerView;
    Myadapter adapter;
    String cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_all_files_view );

        RecyclerView recyclerView= findViewById( R.id.recyclarview );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

//        ClassList schobj= new ClassList();
//        String cl=schobj.classtype;

        cl=getIntent().getStringExtra( "classtype" );
//        Toast.makeText( this, cl, Toast.LENGTH_LONG ).show();

        FirebaseRecyclerOptions<UploadPDF> options= new FirebaseRecyclerOptions.Builder<UploadPDF>()
                .setQuery( FirebaseDatabase.getInstance().getReference().child( "Uploads/"+ cl ),UploadPDF.class )
                .build();
//        Toast.makeText( this, "000000", Toast.LENGTH_SHORT ).show();

        adapter=new Myadapter(options);
        recyclerView.setAdapter( adapter );


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}