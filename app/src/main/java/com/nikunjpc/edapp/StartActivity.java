package com.nikunjpc.edapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

    Button btskip, btlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_start );

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user==null)
        {
        btskip= (Button) findViewById( R.id.btskip );
        btlogin= (Button) findViewById( R.id.btlogin );

        btskip.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( StartActivity.this, MainActivity.class );
                startActivity( i );
            }
        } );


        btlogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( StartActivity.this, LoginActivity.class );
                startActivity( i );
            }
        } );

        }
        else
            {
        Intent i= new Intent( StartActivity.this, MainActivity.class );
        startActivity( i );
        }
    }
}