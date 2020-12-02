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
//    ArrayList<String> ListSc1,ListSc2,ListClgBt, ListClgBC;
//    ArrayAdapter<String> ad2;


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

//        database = FirebaseDatabase.getInstance().getReference("Uploads");
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
        if(position>0 && position<9)
        {
            filename=ed1.getText().toString().trim() + " -Cl. "+ (position+4);
//                    +" "+ List.get( position)+" : "+ed4;
//            info=ed2.getText().toString();
//
            if(!(ed2.getText().toString().trim().isEmpty()))
                info=ed2.getText().toString().trim();

        }
        else if(position>8 && position<14){
            filename = ed1.getText().toString().trim()+" -Sem "+ed3.getText().toString().trim();
//                    + " "+List.get(position)+"(Sem " + ed3 + ")"+" : "+ed4;
//            info=ed4.getText().toString();
            if(!(ed4.getText().toString().trim().isEmpty()))
                info=ed4.getText().toString().trim();

        }
        else if(position==14){
            filename = ed1.getText().toString().trim()+" -Sem "+ed3.getText().toString().trim();

            if(!(ed4.getText().toString().trim().isEmpty()))
                info=ed4.getText().toString().trim();
        }
        else if(position==15)
        {
            filename = ed1.getText().toString().trim() + "-Other";

            if(!(ed2.getText().toString().trim().isEmpty()))
                info=ed2.getText().toString().trim();
        }

        StorageReference storageReference=storage.child( "Uploads/"+List.get( position )+"/"+filename);
        storageReference.putFile( pdfUri )
                .addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();

//                        UploadPDF uploadPDF=new UploadPDF(filename, url);
                        while (!task.isComplete()) ;
                        Uri uri = task.getResult();
                        uploadData( String.valueOf( uri ) );

//
//                        Toast.makeText( UploadActivity.this, "0000000", Toast.LENGTH_SHORT ).show();
//
//                        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
//                        DatabaseReference myRef = database1.getReference("newmessage");
//
//                        Toast.makeText( UploadActivity.this, "1111111", Toast.LENGTH_SHORT ).show();
//                        myRef.setValue("Hello New World 0710pm");
//                        Toast.makeText( UploadActivity.this, "22222222", Toast.LENGTH_SHORT ).show();
//
//                        Toast.makeText( UploadActivity.this, "3333333", Toast.LENGTH_SHORT ).show();
//
//                        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
//                        DatabaseReference myRef3 = database3.getReference("newnewmessage");
//
//                        Toast.makeText( UploadActivity.this, "4444444", Toast.LENGTH_SHORT ).show();
//                        myRef3.setValue("Hello New New World 0710pm").addOnSuccessListener( new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText( UploadActivity.this, "Sucessfully added ", Toast.LENGTH_SHORT ).show();
//                            }
//                        } );
//                        Toast.makeText( UploadActivity.this, "5555555555", Toast.LENGTH_SHORT ).show();

//                        Log.d("a", "error");
//                        Log.e( "kuch", "gadbad" );

                    }
                } )
                .addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
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

        database.child( String.valueOf( System.currentTimeMillis() ) );
        database.setValue( helperclass )
                 .addOnCompleteListener( new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

//                                Toast.makeText( UploadActivity.this, "database", Toast.LENGTH_SHORT ).show();
                                if (task.isSuccessful()) {
                                    Toast.makeText( UploadActivity.this, "File Successfuly Uploaded", Toast.LENGTH_SHORT ).show();
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
        startActivityForResult( i, 37 );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == 37 && resultCode == RESULT_OK && data != null) {
            pdfUri = data.getData();
            tv_filename.setText( data.getData().getLastPathSegment() );
        } else {
            Toast.makeText( UploadActivity.this, "Please select a pdf file", Toast.LENGTH_SHORT ).show();
        }
    }
}
        //
//        sp2.setVisibility( View.INVISIBLE );
//        sp3.setVisibility( View.INVISIBLE );
//
//        final List<String> ListSc = new ArrayList<String>();
//        ListSc.add("Select the Class");
//        ListSc.add("Class 5");
//        ListSc.add("Class 6");
//        ListSc.add("Class 7");
//        ListSc.add("Class 8");
//        ListSc.add("Class 9");
//        ListSc.add("Class 10");
//        ListSc.add("Class 11");
//        ListSc.add("Class 12");
//
//        ListSc1 = new ArrayList<String>();
//        ListSc1.add("Select the Option");
//        ListSc1.add("Science");
//        ListSc1.add("Commerce");
//        ListSc1.add("Arts");

//
//        final List<String> ListClg = new ArrayList<String>();
//        ListClg.add("Select the Course");
//        ListClg.add("B.Tech CSE");
//        ListClg.add("B.Tech IT");
//        ListClg.add("B.Tech ECE");
//        ListClg.add("B.Tech MAE");
////        ListClg.add("BCA");
//
//        ListClgBt = new ArrayList<String>();
//        ListClgBt.add("Select the Option");
//        ListClgBt.add("Semester 1");
//        ListClgBt.add("Semester 2");
//        ListClgBt.add("Semester 3");
//        ListClgBt.add("Semester 4");
//        ListClgBt.add("Semester 5");
//        ListClgBt.add("Semester 6");
//        ListClgBt.add("Semester 7");
//        ListClgBt.add("Semester 8");
//
//        ListClgBC = new ArrayList<String>();
//        ListClgBC.add("Select the Option");
//        ListClgBC.add("Semester 1");
//        ListClgBC.add("Semester 2");
//        ListClgBC.add("Semester 3");
//        ListClgBC.add("Semester 4");
//        ListClgBC.add("Semester 5");
//        ListClgBC.add("Semester 6");
//
//        ListSc2 = new ArrayList<String>();
//        ListSc2.add("Select the Option");
//        ListSc2.add("English Language");
//        ListSc2.add("Hindi Language");
//
//        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

//
//                if(i>0 && i<7)
//                {
//                    ad2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ListSc2 );
//                }
//                if(i==7 || i==8) {
//                    ad2 = new ArrayAdapter<String>( getApplicationContext(), android.R.layout.simple_spinner_item, ListSc1 );
//                }
//                if(i>8 && i<14){
//                    ad2 = new ArrayAdapter<String>( getApplicationContext(), android.R.layout.simple_spinner_item, ListClgBt );
//                }
//                if(i==14)
//                {
//                    ad2 = new ArrayAdapter<String>( getApplicationContext(), android.R.layout.simple_spinner_item, ListClgBC );
//                }
//                sp2.setAdapter( ad2 );
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        if(n==1){
//            final ArrayAdapter<String> ad2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,ListSc);
//            ad2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
//            sp2.setAdapter(ad2);
//
//            sp2.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    if(position==7 || position==8)
//                    {
//                        n=4;
//                    }
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            } );
//        }
//        if(n==2)
//        {
//            ArrayAdapter<String> ad2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,ListClg);
//            ad2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
//            sp2.setAdapter(ad2);
//
//            sp2.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    if(position>0 && position<5)
//                    {
//                        n=5;
//                    }
//                    else if(position==5){
//                        n=6;
//                    }
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            } );
//        }
//
//
//        if(n==4)
//        {
//            ArrayAdapter<String> ad3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,ListSc1);
//            ad3.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
//            sp3.setAdapter(ad3);
//        }
//
//        if(n==5)
//        {
//            ArrayAdapter<String> ad3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,ListClgBt);
//            ad3.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
//            sp3.setAdapter(ad3);
//        }
//
//        if(n==6)
//        {
//            ArrayAdapter<String> ad3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,ListClgBC);
//            ad3.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
//            sp3.setAdapter(ad3);
//        }
//    }
//}