package com.nikunjpc.edapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

public class ClassList extends AppCompatActivity implements View.OnClickListener {

    String classtype;
    Button c6, c7, c8, c5, c9, c10, c11, c12;
    ImageButton c51, c61, c71, c81, c91, c101, c111, c121;
    String[] listitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_class_list );

        c5 = (Button) findViewById( R.id.c5 );
        c6 = (Button) findViewById( R.id.c6 );
        c7 = (Button) findViewById( R.id.c7 );
        c8 = (Button) findViewById( R.id.c8 );
        c9 = (Button) findViewById( R.id.c9 );
        c10 = (Button) findViewById( R.id.c10 );
        c11 = (Button) findViewById( R.id.c11 );
        c12 = (Button) findViewById( R.id.c12 );
        c51 = (ImageButton) findViewById( R.id.c51 );
        c61 = (ImageButton) findViewById( R.id.c61 );
        c71 = (ImageButton) findViewById( R.id.c71 );
        c81 = (ImageButton) findViewById( R.id.c81 );
        c91 = (ImageButton) findViewById( R.id.c91 );
        c101 = (ImageButton) findViewById( R.id.c101 );
        c111 = (ImageButton) findViewById( R.id.c111 );
        c121 = (ImageButton) findViewById( R.id.c121 );


        c5.setOnClickListener( this );
        c51.setOnClickListener( this );
        c6.setOnClickListener( this );
        c61.setOnClickListener( this );
        c7.setOnClickListener( this );
        c71.setOnClickListener( this );
        c8.setOnClickListener( this );
        c81.setOnClickListener( this );
        c9.setOnClickListener( this );
        c10.setOnClickListener( this );
        c101.setOnClickListener( this );
        c11.setOnClickListener( this );
        c111.setOnClickListener( this );
        c12.setOnClickListener( this );
        c121.setOnClickListener( this );

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.c5:{

            }
            case R.id.c51:{

//                Toast.makeText( this, "Intent", Toast.LENGTH_SHORT ).show();
                Intent i= new Intent( ClassList.this, AllFilesView.class );
                classtype="Class 5";
                i.putExtra( "classtype",classtype );
                startActivity( i );
                break;
            }
            case R.id.c6:{

            }
            case R.id.c61:{
                Intent i= new Intent( ClassList.this, AllFilesView.class );
                classtype="Class 6";
                i.putExtra( "classtype",classtype );
                startActivity( i );
                break;
            }
            case R.id.c7:{

            }
            case R.id.c71:{
                Intent i= new Intent( ClassList.this, AllFilesView.class );
                classtype="Class 7";
                i.putExtra( "classtype",classtype );
                startActivity( i );
                break;
            }
            case R.id.c8:{

            }
            case R.id.c81:{

                Intent i= new Intent( ClassList.this, AllFilesView.class );
                classtype="Class 8";
                i.putExtra( "classtype",classtype );
                startActivity( i );
                break;
            }
            case R.id.c9:{

            }
            case R.id.c91:{

                Intent i= new Intent( ClassList.this, AllFilesView.class );
                classtype="Class 9";
                i.putExtra( "classtype",classtype );
                startActivity( i );
                break;
            }
            case R.id.c10:{

            }
            case R.id.c101:{

                Intent i= new Intent( ClassList.this, AllFilesView.class );
                classtype="Class 10";
                i.putExtra( "classtype",classtype );
                startActivity( i );
                break;
            }
            case R.id.c11:{

            }
            case R.id.c111:{

                listitems = new String[]{"Science", "Commerce", "Arts"};
                AlertDialog.Builder builder = new AlertDialog.Builder( ClassList.this );
                builder.setTitle( "Choose the Stream " );
                builder.setSingleChoiceItems( listitems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText( ClassList.this, "Stream Selected : " + listitems[which], Toast.LENGTH_SHORT ).show();
                        dialog.dismiss();
                        Intent i= new Intent( ClassList.this, AllFilesView.class );
                        classtype="Class 11";
                        i.putExtra( "classtype",classtype );
                        startActivity( i );

                    }
                } );
                builder.setNeutralButton( "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText( ClassList.this, "Stream not Selected", Toast.LENGTH_SHORT ).show();

                    }
                } );

                AlertDialog mDialog = builder.create();
                mDialog.show();
                break;
            }
            case R.id.c12:{

            }
            case R.id.c121:{

                listitems = new String[]{"Science", "Commerce", "Arts"};
                AlertDialog.Builder builder = new AlertDialog.Builder( ClassList.this );
                builder.setTitle( "Choose the Stream " );
                builder.setSingleChoiceItems( listitems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText( ClassList.this, "Stream Selected : " + listitems[which], Toast.LENGTH_SHORT ).show();
                        dialog.dismiss();

                        Intent i= new Intent( ClassList.this, AllFilesView.class );
                        classtype="Class 12";
                        i.putExtra( "classtype",classtype );
                        startActivity( i );
                    }
                } );
                builder.setNeutralButton( "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText( ClassList.this, "Stream not Selected", Toast.LENGTH_SHORT ).show();

                    }
                } );

                AlertDialog mDialog = builder.create();
                mDialog.show();
                break;
            }
        }

    }

}
//
//    @Override
//    public void onPositiveButtonClicked(int position) {
//        Toast.makeText( this, "Selected : "+ position , Toast.LENGTH_SHORT ).show();
//    }
//
//    @Override
//    public void onNegativeButtonClicked() {
//        Toast.makeText( this, "Cancelled List", Toast.LENGTH_SHORT ).show();
//
//    }
