package com.nikunjpc.edapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class CourseList extends AppCompatActivity implements View.OnClickListener {

    String classtype;
    Button c6, c7, c8, c5, c9, c10, c11;
    ImageButton c51, c61, c71, c81, c91, c101, c111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_course_list );

        c5 = (Button) findViewById( R.id.c5 );
        c6 = (Button) findViewById( R.id.c6 );
        c7 = (Button) findViewById( R.id.c7 );
        c8 = (Button) findViewById( R.id.c8 );
        c9 = (Button) findViewById( R.id.c9 );
        c10 = (Button) findViewById( R.id.c10 );
        c11 = (Button) findViewById( R.id.c11 );
        c51 = (ImageButton) findViewById( R.id.c51 );
        c61 = (ImageButton) findViewById( R.id.c61 );
        c71 = (ImageButton) findViewById( R.id.c71 );
        c81 = (ImageButton) findViewById( R.id.c81 );
        c91 = (ImageButton) findViewById( R.id.c91 );
        c101 = (ImageButton) findViewById( R.id.c101 );
        c111 = (ImageButton) findViewById( R.id.c111 );


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

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.c5:{

            }
            case R.id.c51:{
                Intent i= new Intent( CourseList.this, AllFilesView.class );
                classtype="BTech CSE";
                i.putExtra( "classtype",classtype );
                startActivity( i );
                break;
            }
            case R.id.c6:{

            }
            case R.id.c61:{
                Intent i= new Intent( CourseList.this, AllFilesView.class );
                classtype="BTech IT";
                i.putExtra( "classtype",classtype );
                startActivity( i );
                break;
            }
            case R.id.c7:{

            }
            case R.id.c71:{
                Intent i= new Intent( CourseList.this, AllFilesView.class );
                classtype="BTech ECE";
                i.putExtra( "classtype",classtype );
                startActivity( i );
                break;
            }
            case R.id.c8:{

            }
            case R.id.c81:{

                Intent i= new Intent( CourseList.this, AllFilesView.class );
                classtype="BTech MAE";
                i.putExtra( "classtype",classtype );
                startActivity( i );
                break;
            }
            case R.id.c9:{

            }
            case R.id.c91:{

                Intent i= new Intent( CourseList.this, AllFilesView.class );
                classtype="BTech EEE";
                i.putExtra( "classtype",classtype );
                startActivity( i );
                break;
            }
            case R.id.c10:{

            }
            case R.id.c101:{

                Intent i= new Intent( CourseList.this, AllFilesView.class );
                classtype="BCA";
                i.putExtra( "classtype",classtype );
                startActivity( i );
                break;
            }
            case R.id.c11:{

            }
            case R.id.c111:{

                Intent i= new Intent( CourseList.this, AllFilesView.class );
                classtype="Other";
                i.putExtra( "classtype",classtype );
                startActivity( i );
                break;
            }
        }
    }
}