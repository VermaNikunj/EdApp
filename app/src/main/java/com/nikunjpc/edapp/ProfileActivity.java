package com.nikunjpc.edapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    TextView profilename, profileemail;
    Button signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );

        profilename=(TextView) findViewById( R.id.tvprofile1 );
        profileemail=(TextView) findViewById( R.id.tvprofile2 );
        signout=(Button) findViewById( R.id.btsignout );

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            String phone= user.getPhoneNumber();

            profilename.setVisibility( View.VISIBLE );
            profileemail.setVisibility( View.VISIBLE );
            signout.setVisibility( View.VISIBLE );

            String demo;
            demo="Name : "+name;

            profilename.setText(demo );

            if(email==null) {
                demo="Email id/Phone No. : \n "+ phone;
                profileemail.setText(  demo);
            }
            else {
                demo="Email id/Phone No. : \n"+ email;
                profileemail.setText( demo );
            }

            signout.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText( ProfileActivity.this, "Successfully Sign-out", Toast.LENGTH_SHORT ).show();
                    Intent i= new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity( i );
                }
            } );

        }
        else
        {
            Toast.makeText( this, "Please Login First", Toast.LENGTH_SHORT ).show();
            Intent i= new Intent( ProfileActivity.this, StartActivity.class );
            startActivity( i );

        }
    }
}