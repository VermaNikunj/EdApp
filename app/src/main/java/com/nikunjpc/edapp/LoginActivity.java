package com.nikunjpc.edapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


    private static final int MY_REQUEST_CODE = 711;
    List<AuthUI.IdpConfig> providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        showSignInOptions();

    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders( providers )
                        .setTheme( R.style.MyTheme2 )
                        .build(), MY_REQUEST_CODE
        );
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult( requestCode, resultCode, data);

        if(requestCode == MY_REQUEST_CODE)
        {
            IdpResponse response =IdpResponse.fromResultIntent( data );

            if(resultCode == RESULT_OK)
            {
                //Get User
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                Toast.makeText( this, "Successfully Login", Toast.LENGTH_SHORT  ).show();

                Intent i= new Intent( LoginActivity.this, MainActivity.class );
                startActivity( i );

            }
            else
            {
                Toast.makeText( this, "Error: "+response.getError().getMessage(), Toast.LENGTH_SHORT ).show();
            }
        }
    }


}