package com.nikunjpc.edapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btProfile;
    Button btStart, btUpload, btContact,btInfo,btStartClg;

    private static  final int TIME_INTERVAL=2000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btProfile=(Button) findViewById( R.id.btProfile );
        btStart = (Button) findViewById( R.id.btStart );
//        btSetting = (Button) findViewById( R.id.btSetting );
        btUpload = (Button) findViewById( R.id.btUpload );
        btContact = (Button) findViewById( R.id.btContact );
        btInfo = (Button) findViewById( R.id.btInfo );
        btStartClg= (Button) findViewById( R.id.btStartclg );

        btStart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( MainActivity.this,  ClassList.class );
                startActivity( i );
            }
        } );


        btStartClg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( MainActivity.this,  CourseList.class );
                startActivity( i );
            }
        } );


//
//        btSetting.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent i= new Intent( MainActivity.this,  UploadActivity.class );
////                startActivity( i );
//            }
//        } );


        btUpload.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( MainActivity.this,  UploadActivity.class );
                startActivity( i );
            }
        } );

        btProfile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( MainActivity.this,  ProfileActivity.class );
                startActivity( i );
            }
        } );

        btContact.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( MainActivity.this,  Contact.class );
                startActivity( i );
            }
        } );



        btInfo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( MainActivity.this,  InfoActivity.class );
                startActivity( i );
            }
        } );



    }

    @Override
    public void onBackPressed() {
        if(mBackPressed+ TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        }
        else{
            Toast.makeText( this, "Click two times to exit", Toast.LENGTH_SHORT ).show();
        }
        mBackPressed= System.currentTimeMillis();
    }
}