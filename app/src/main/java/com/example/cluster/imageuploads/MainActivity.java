package com.example.cluster.imageuploads;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static android.view.ViewGroup.*;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    ImageView imageView;
    Button btnSelect,btnUpload;
    String path;
    static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setUpLayout();
       context=getApplicationContext();
       btnSelect.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
               intent.setType("image/*");
               startActivityForResult(Intent.createChooser(intent,"Choose Image"),1001);

           }
       });
       btnUpload.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {


               new MyTask().execute(path);


           }
       });
    }

    @SuppressLint("ResourceType")
    private void setUpLayout() {
        relativeLayout=new RelativeLayout(this);
         imageView=new ImageView(this);
        btnSelect=new Button(this);
        btnUpload=new Button(this);
RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        // use same id as defined when adding the button
        btnUpload.setLayoutParams(params);
        btnUpload.setId(1001);
        RelativeLayout.LayoutParams params1=new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
       params1.addRule(RelativeLayout.BELOW,1001);
         btnSelect.setLayoutParams(params1);
        relativeLayout.addView(imageView);
        relativeLayout.addView(btnSelect);
        relativeLayout.addView(btnUpload);

       btnSelect.setId(1002);
        RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams(1200, 1200);
        params2.addRule(RelativeLayout.BELOW,1002);
        params2.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(params2);
       imageView.setScaleType(ImageView.ScaleType.FIT_END);
        imageView.setImageResource(R.drawable.k);

        btnSelect.setText("select from storage");
        btnUpload.setText("Upload");
        this.setContentView(relativeLayout);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1001&&resultCode==RESULT_OK){

            Uri uri=data.getData();
            imageView.setImageURI(uri);
            String projectino[]={MediaStore.Images.Media.DATA};
            ContentResolver contentProvider=getContentResolver();
            Cursor cursor=contentProvider.query(uri,projectino,null,null,null);
            cursor.moveToFirst();
            int index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            path=cursor.getString(index);

        }
    }
}
