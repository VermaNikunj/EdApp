package com.nikunjpc.edapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Contact extends AppCompatActivity {

    ArrayList<String> List2;
    ArrayAdapter<String> ad2;

    Spinner spcontact;
    Button btsubmit;
    EditText comment,email;

    int c=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_contact );


        spcontact = (Spinner) findViewById( R.id.spcontact );
        btsubmit= (Button) findViewById( R.id.btsubmit );
        comment = (EditText) findViewById( R.id.comment );
        email = (EditText) findViewById( R.id.etemail );

        List2 = new ArrayList<String>();
        List2.add( "Select the Option" );
        List2.add( "Issue" );
        List2.add( "Bug" );
        List2.add( "Feedback" );
        List2.add( "Other" );

        ad2 = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, List2 );
        ad2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spcontact.setAdapter( ad2 );

        spcontact.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0 && position<=4)
                    c=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        btsubmit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c>0){
                     String str=email.getText().toString().trim();
                    String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if(str.matches( emailpattern ))
                    {
                        if(!comment.getText().toString().trim().isEmpty())
                        {
                           sendcomment();

                        }
                        else Toast.makeText( Contact.this, "Please enter the comment", Toast.LENGTH_SHORT ).show();
                    }
                    else
                    {
                        Toast.makeText( Contact.this, "Please enter the correct email id", Toast.LENGTH_SHORT ).show();
                    }
                }
                else
                {
                    Toast.makeText( Contact.this, "Please select the option", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }

    private void sendcomment() {

                        String type,cmt;
                        type=List2.get( c )+"-"+System.currentTimeMillis();
                        cmt=email.getText().toString().trim()+" : "+comment.getText().toString().trim();
        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
                        DatabaseReference myRef3 = database3.getReference(type);

                        myRef3.setValue(cmt).addOnSuccessListener( new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText( Contact.this, "Thank you for the comment ", Toast.LENGTH_SHORT ).show();

                                email.setText( "" );
                                comment.setText( "" );
                            }
                        } );
    }
}