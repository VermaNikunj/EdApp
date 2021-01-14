package com.nikunjpc.edapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class UploadActivity extends AppCompatActivity {

    Spinner sp1;
    Button btselect,btselect2,btupload;
    TextView tv_filename,tv_filename2;
    ImageButton plus, minus;
    EditText ed1,ed2,ed3,ed4;

    int position=0,n=0,c=0;

    ArrayList<String> List;
    ArrayAdapter<String> ad1;


    String filename="NoName";
    String info="None";

//    DatabaseReference database;

    DatabaseReference database;
    FirebaseDatabase databasereference;

    StorageReference storage;
    Uri pdfUri;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_upload );


        storage = FirebaseStorage.getInstance().getReference();

        databasereference = FirebaseDatabase.getInstance();

        sp1 = (Spinner) findViewById( R.id.sp1 );
        //sp2 = (Spinner) findViewById( R.id.sp2 );
        btselect = (Button) findViewById( R.id.btselect );
        btselect2 = (Button) findViewById( R.id.btselect2 );
        btupload = (Button) findViewById( R.id.btupload );
        tv_filename = (TextView) findViewById( R.id.tv_filename );
        tv_filename2 = (TextView) findViewById( R.id.tv_filename2 );
        plus = (ImageButton) findViewById( R.id.plus );
        minus = (ImageButton) findViewById( R.id.minus );
        ed1=(EditText) findViewById( R.id.ed1 );
        ed2=(EditText) findViewById( R.id.ed2 );
        ed3=(EditText) findViewById( R.id.ed3 );
        ed4=(EditText) findViewById( R.id.ed4 );


        List = new ArrayList<String>();
        List.add( "Select the Option" );
        List.add( "Class 5" );
        List.add( "Class 6" );
        List.add( "Class 7" );
        List.add( "Class 8" );
        List.add( "Class 9" );
        List.add( "Class 10" );
        List.add( "Class 11" );
        List.add( "Class 12" );
        List.add( "BTech CSE" );
        List.add( "BTech IT" );
        List.add( "BTech ECE" );
        List.add( "BTech MAE" );
        List.add( "BTech EEE" );
        List.add( "BCA" );
        List.add( "Other" );

        ad1 = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, List );
        ad1.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        sp1.setAdapter( ad1 );

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>8 && i<15)
                {
                    ed1.setVisibility( View.VISIBLE );
                    ed3.setVisibility( View.VISIBLE );
                    ed4.setVisibility( View.VISIBLE );
                    btselect.setVisibility( View.VISIBLE );
                    ed2.setVisibility( View.INVISIBLE );
                    n=3;
                    position=i;
                }
                else if((i>0 && i<=8) || (i==15))
                {
                    ed1.setVisibility( View.VISIBLE );
                    ed2.setVisibility( View.VISIBLE );
                    btselect.setVisibility( View.VISIBLE );
                    ed3.setVisibility( View.INVISIBLE );
                    ed4.setVisibility( View.INVISIBLE );
                    n=2;
                    position =i;
                }
                else if(i==0)
                {
                    ed1.setVisibility( View.INVISIBLE );
                    ed3.setVisibility( View.INVISIBLE );
                    ed4.setVisibility( View.INVISIBLE );
                    btselect.setVisibility( View.INVISIBLE );
                    ed2.setVisibility( View.INVISIBLE );

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btselect.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(UploadActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
                    selectPDF();
                }
                else
                    ActivityCompat.requestPermissions( UploadActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9 );
            }
        } );


       btupload.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(n==2)
               {
                   if(!ed1.getText().toString().trim().isEmpty()) {
                           c = 10;
                       }
                   else{
                       Toast.makeText( UploadActivity.this, "Enter the Subject", Toast.LENGTH_SHORT ).show();
                   }
               }
               else if(n==3)
               {
                   if(!ed1.getText().toString().trim().isEmpty()){
                       if(!ed3.getText().toString().trim().isEmpty()) {
                           int sem= Integer.parseInt( ed3.getText().toString().trim() );
                           if(sem>0 && sem<9 && position<14 && position>8)
                           c = 10;
                           else if(sem>0 && sem<7 && position==14)
                               c=10;
                           else
                               Toast.makeText( UploadActivity.this, "Enter the correct Semester", Toast.LENGTH_SHORT ).show();
                           }
                       else Toast.makeText( UploadActivity.this, "Enter the Semester", Toast.LENGTH_SHORT ).show();
                   }
                   else Toast.makeText( UploadActivity.this, "Enter the Subject", Toast.LENGTH_SHORT ).show();
               }
               else
               {
                   Toast.makeText( UploadActivity.this, "First Select the Option", Toast.LENGTH_SHORT ).show();
               }

               if(c==10) {
                   if (pdfUri != null) {

//                       Log.e( "Error", "Uri: "+pdfUri );

                       uploadFile( pdfUri );
                   }
                   else Toast.makeText( UploadActivity.this, "Select the file", Toast.LENGTH_SHORT ).show();
               }
           }
       } );


    }

    private void uploadFile(Uri pdfUri) {
        progressDialog = new ProgressDialog( UploadActivity.this );
        progressDialog.setProgressStyle( ProgressDialog.STYLE_HORIZONTAL );
        progressDialog.setTitle( "Uploading file..." );
        progressDialog.setProgress( 0 );
        progressDialog.show();

//        final String info;
        if(position>0 && position<6)
        {
            filename=ed1.getText().toString().trim() + " - Cl. "+ (position+4);
//                    +" "+ List.get( position)+" : "+ed4;
//            info=ed2.getText().toString();
//
            if(!(ed2.getText().toString().trim().isEmpty()))
                info=ed2.getText().toString().trim();

        }
        if(position>5 && position<9)
        {
            filename=ed1.getText().toString().trim() + " - Cl "+ (position+4);
//                    +" "+ List.get( position)+" : "+ed4;
//            info=ed2.getText().toString();
//
            if(!(ed2.getText().toString().trim().isEmpty()))
                info=ed2.getText().toString().trim();

        }
        else if(position>8 && position<14){
            filename = ed1.getText().toString().trim()+" - Sem "+ed3.getText().toString().trim();
//                    + " "+List.get(position)+"(Sem " + ed3 + ")"+" : "+ed4;
//            info=ed4.getText().toString();
            if(!(ed4.getText().toString().trim().isEmpty()))
                info=ed4.getText().toString().trim();

        }
        else if(position==14){
            filename = ed1.getText().toString().trim()+" - Sem "+ed3.getText().toString().trim();

            if(!(ed4.getText().toString().trim().isEmpty()))
                info=ed4.getText().toString().trim();
        }
        else if(position==15)
        {
            filename = ed1.getText().toString().trim() + " - Other";

            if(!(ed2.getText().toString().trim().isEmpty()))
                info=ed2.getText().toString().trim();
        }

        StorageReference storageReference=storage.child( "Uploads/"+List.get( position )+"/"+filename);

//        Log.e( "Error", "Error message 0" );
        storageReference.putFile( pdfUri )
                .addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

//                        Log.e( "Error", "Error message 2" );
                        Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();

                        while (!task.isComplete()) ;
                        Uri uri = task.getResult();
                        uploadData( String.valueOf( uri ) );

                    }
                } )
                .addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                Log.e( "Error", "Error message 1" );
                Toast.makeText( UploadActivity.this, "Something went wrong try again!!!", Toast.LENGTH_SHORT ).show();
                progressDialog.dismiss();
            }
        } )
                .addOnProgressListener( new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

                int currentProgress= (int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress  );
            }
        } );


    }

    private void uploadData(String uri) {
        String downloadUrl=String.valueOf( uri );
//        Toast.makeText( UploadActivity.this, "File Stored:"+downloadUrl, Toast.LENGTH_LONG ).show();
//     String info="None";
//        if(!(ed4.getText().toString().trim().isEmpty()))
//            info=ed4.getText().toString().trim();
//
//        String classType=List.get( position )+"-"+System.currentTimeMillis();
        UploadPDF helperclass= new UploadPDF( List.get(position), filename, downloadUrl, info  );

//        Log.e( "Error", "Error message 3" );

//        Toast.makeText( UploadActivity.this, "File Stored:"+helperclass.getClasstype(), Toast.LENGTH_LONG ).show();

//        Toast.makeText( UploadActivity.this, "File Stored:"+helperclass.getName(), Toast.LENGTH_LONG ).show();

//
//        database.child( String.valueOf( System.currentTimeMillis() ) );
//                        Toast.makeText( UploadActivity.this, "File Stored 2", Toast.LENGTH_SHORT ).show();
//                        progressDialog.dismiss();

//                .addOnSuccessListener( new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText( UploadActivity.this, "File Successfuly Uploaded", Toast.LENGTH_SHORT ).show();
//                        progressDialog.dismiss();
//                    }
//                } );
//        Toast.makeText( UploadActivity.this, "File Stored:"+helperclass.getUrl(), Toast.LENGTH_LONG ).show();

//        Toast.makeText( this, "File Stored:"+helperclass.getInfo(), Toast.LENGTH_SHORT ).show();
//                 database.child( String.valueOf( System.currentTimeMillis() ) );
//        Toast.makeText( UploadActivity.this, "0000000", Toast.LENGTH_LONG ).show();
//                 database.push();
//        Toast.makeText( UploadActivity.this, "1111111", Toast.LENGTH_LONG ).show();
//                         database.setValue( helperclass ).addOnSuccessListener( new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText( UploadActivity.this, "File Successfuly Uploaded", Toast.LENGTH_SHORT ).show();
//                        progressDialog.dismiss();
//                    }
//                } );

//        Toast.makeText( UploadActivity.this, "222222222", Toast.LENGTH_LONG ).show();

        database = databasereference.getReference("Uploads/"+List.get( position )+"/"+System.currentTimeMillis());
//        Log.e( "Error", "Error message 4" );

        database.child( String.valueOf( System.currentTimeMillis() ) );
//        Log.e( "Error", "Error message 5" );

        database.setValue( helperclass )
                 .addOnCompleteListener( new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

//                                Log.e( "Error", "Error message 6" );

//                                Toast.makeText( UploadActivity.this, "database", Toast.LENGTH_SHORT ).show();
                                if (task.isSuccessful()) {
                                    Toast.makeText( UploadActivity.this, "File Successfully Uploaded", Toast.LENGTH_SHORT ).show();
                                    progressDialog.dismiss();

                                    ed1.setVisibility( View.INVISIBLE );
                                    ed2.setVisibility( View.INVISIBLE );
                                    btselect.setVisibility( View.INVISIBLE );
                                    ed3.setVisibility( View.INVISIBLE );
                                    ed4.setVisibility( View.INVISIBLE );
                                    tv_filename.setText( "" );
                                } else {
                                    Toast.makeText( UploadActivity.this, "File not Successfully Uploaded", Toast.LENGTH_SHORT ).show();
                                    progressDialog.dismiss();
                                }
                            }
                                } );

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectPDF();
        }
        else
        {
            Toast.makeText( UploadActivity.this, "Please provide permission..", Toast.LENGTH_SHORT ).show();
        }
    }

    private void selectPDF() {

        Intent i=new Intent();
        i.setType( "application/pdf" );
        i.setAction( Intent.ACTION_GET_CONTENT );
        startActivityForResult( i, 40 );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == 40 && resultCode == RESULT_OK && data != null) {
            pdfUri = data.getData();
            tv_filename.setText( data.getData().getLastPathSegment() );
        } else {
            Toast.makeText( UploadActivity.this, "Please select a pdf file", Toast.LENGTH_SHORT ).show();
        }
    }
}
