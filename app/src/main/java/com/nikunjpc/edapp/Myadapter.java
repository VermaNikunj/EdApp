package com.nikunjpc.edapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Myadapter extends FirebaseRecyclerAdapter<UploadPDF,Myadapter.ViewHolder> {


    public Myadapter(@NonNull FirebaseRecyclerOptions<UploadPDF> options) {
        super( options );
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i, @NonNull final UploadPDF uploadPDF) {
        final String filename,type,cl=uploadPDF.getClasstype(),str= uploadPDF.getName();
        int no=str.length();
        if(str.charAt(no-5)=='S')
        {
            filename=str;
            type=cl+" Semester"+str.charAt( no-1 );
        }
        else {
            filename=str.substring( 0,(no-6) );
            type=cl;
        }
        viewHolder.tvname.setText(filename );

        viewHolder.tvclasstype.setText( type );

        viewHolder.tvname.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( viewHolder.tvname.getContext(), ViewPdf.class );
                intent.putExtra( "filename",filename );
                intent.putExtra( "url",uploadPDF.getUrl() );

                intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                viewHolder.tvname.getContext().startActivity( intent );
            }
        } );
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate( R.layout.item,parent,false );
        return new ViewHolder( view );
    }

    class ViewHolder extends  RecyclerView.ViewHolder{

        TextView tvname, tvclasstype;

        public ViewHolder(View itemView){
            super(itemView);

            tvname=itemView.findViewById( R.id.tvname );
            tvclasstype=itemView.findViewById( R.id.tvclasstype );


        }
    }
}
