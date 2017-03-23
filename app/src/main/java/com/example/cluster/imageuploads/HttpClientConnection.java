package com.example.cluster.imageuploads;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cluster on 3/22/2017.
 */

public class HttpClientConnection  {

    private static MultipartEntity multipartEntity;

    private static byte[] convertToByteArray(InputStream inputStream) throws IOException{

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int next = inputStream.read();
        while (next > -1) {
            bos.write(next);
            next = inputStream.read();
        }

        bos.flush();

        return bos.toByteArray();
    }

    public static   String makeServiceCall(String aVoid) throws IOException {

         List<NameValuePair> params=new ArrayList<NameValuePair>();
         params.add(new BasicNameValuePair("NM","yes"));
        HttpClient httpClient=new DefaultHttpClient();
        HttpPost httpPost=new HttpPost(UrlConnection.UPLOAD);


        InputStream inputStream = new FileInputStream(new File(aVoid));

        //*** CONVERT INPUTSTREAM TO BYTE ARRAY

        byte[] data = convertToByteArray(inputStream);

        // STRING DATA
        StringBody dataString = new StringBody("This is the sample image");
        multipartEntity=new MultipartEntity();

        // FILE DATA OR IMAGE DATA
        InputStreamBody inputStreamBody = new InputStreamBody(new ByteArrayInputStream(data),aVoid);

        multipartEntity.addPart("file", inputStreamBody);

        //*** ADD STRING DATA


        httpPost.setEntity(multipartEntity);
        httpPost.setEntity(multipartEntity);



        HttpResponse httpResponse= httpClient.execute(httpPost);
        HttpEntity entity= httpResponse.getEntity();
        InputStream datas=entity.getContent();
        Log.d("CONTENTRESPONSE","Path:"+httpResponse.toString());
        Log.d("CONTENTENTITY","Path:"+entity.toString());

        BufferedReader reader=new BufferedReader(new InputStreamReader(datas));
        String line=null;
        StringBuilder builder=new StringBuilder();
        while((line=reader.readLine())!=null){


            builder.append(line+"\n");
        }
        datas.close();
        reader.close();
        Log.d("CONTENT","Path:"+builder.toString());
        return builder.toString();







    }



}
