package com.example.cluster.imageuploads;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Cluster on 3/23/2017.
 */

class MyTask extends AsyncTask<String,Void,Void>{
    ProgressDialog progressDialog;
    String message=null;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected Void doInBackground(String... voids) {

        String resp=null;

        try {
            resp=HttpClientConnection.makeServiceCall(voids[0]);
            JSONObject jsonObject=new JSONObject(resp);
            int status=jsonObject.getInt("status");
            message=jsonObject.getString("message");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("FILE PATH","Path:"+voids[0]);





        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(MainActivity.context,""+message,Toast.LENGTH_SHORT).show();

    }
}
