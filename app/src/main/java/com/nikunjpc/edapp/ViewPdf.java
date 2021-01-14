package com.nikunjpc.edapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ViewPdf extends AppCompatActivity {

    WebView pdfview;

    String fname, furl,fcl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_pdf );

//                    Toast.makeText( MainActivity.this, "Added", Toast.LENGTH_SHORT ).show();

//                finish();
//                startActivity( getIntent() );

        pdfview = (WebView) findViewById( R.id.viewpdf );
        pdfview.getSettings().setJavaScriptEnabled( true );

        fname = getIntent().getStringExtra( "filename" );
        furl = getIntent().getStringExtra( "url" );
        fcl = getIntent().getStringExtra( "classtype" );

//            Log.e("Url CHECK", furl);
            HistoryDatabaseHelperClass databaseHelperClass = new HistoryDatabaseHelperClass( ViewPdf.this );
            HistoryModelClass historymodelClass = new HistoryModelClass( fcl, fname, furl );
//            Log.e("Url CHECK 2", furl);
            databaseHelperClass.addItem( historymodelClass );


//        ViewFilesClass5 obj= new ViewFilesClass5();
//        String furl= obj.furl;
//
        final ProgressDialog pd = new ProgressDialog( this );
        pd.setTitle( fname );
        pd.setMessage( "Opening... !!!" );

        pdfview.setWebViewClient( new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted( view, url, favicon );
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished( view, url );
                pd.dismiss();
            }
        } );

        String url="";
        try{
            url= URLEncoder.encode( furl,"UTF-8" );
        } catch (Exception e) {

        }

        pdfview.loadUrl( "http://docs.google.com/gview?embedded=true&url="+url );

    }

}