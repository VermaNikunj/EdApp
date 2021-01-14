package com.nikunjpc.edapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapterClass extends RecyclerView.Adapter<HistoryAdapterClass.ViewHolder>{

        List<HistoryModelClass> item;
    Context context;
    HistoryDatabaseHelperClass databaseHelperClass;


    public HistoryAdapterClass(List<HistoryModelClass> item, Context context) {
        this.item = item;
        this.context = context;
        databaseHelperClass = new HistoryDatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate( R. layout.history_item, parent,false);
        ViewHolder viewHolder=new ViewHolder( view );
        return viewHolder;
    }

    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        final HistoryModelClass modelClass= item.get( position );

        holder.id.setText(Integer.toString(position+1));
//        String name=modelClass.getFile_name() + " -- "+ modelClass.getClass_type();
        holder.file_name.setText( modelClass.getFile_name());
        holder.class_type.setText( modelClass.getClass_type() );


        holder.file_name.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemclicked(holder, modelClass.getFile_name(), modelClass.getUrl());
                }
        } );

        holder.class_type.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemclicked(holder, modelClass.getFile_name(),modelClass.getUrl());
            }
            } );

        holder.id.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemclicked(holder, modelClass.getFile_name(),modelClass.getUrl());
            }
        } );

        holder.historyDelete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteItem( (modelClass.getId()) );
                Toast.makeText( context, modelClass.getFile_name()+" : Deleted Successfully", Toast.LENGTH_SHORT ).show();
                item.remove(position);
                notifyDataSetChanged();
            }
        } );
    }

    private void itemclicked(ViewHolder holder,String file_name, String url) {
        Intent intent = new Intent( context, ViewPdf.class );
        intent.putExtra( "filename",  file_name);
        intent.putExtra( "url",url );

        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
        holder.file_name.getContext().startActivity( intent );
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView id, class_type, file_name;
        ImageButton historyDelete;

        public ViewHolder(View itemView){
            super(itemView);

            id= itemView.findViewById( R.id.id );
            file_name= itemView.findViewById( R.id.file_name);
            class_type= itemView.findViewById( R.id.class_type );
            historyDelete= itemView.findViewById( R.id.HistoryDelete );



        }
    }
}