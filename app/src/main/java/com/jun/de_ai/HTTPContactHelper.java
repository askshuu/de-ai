package com.jun.de_ai;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPContactHelper {
    public String readJSON(String address){
        URL url = null;
        try{
            url = new URL(address);
        }catch (MalformedURLException e){
            e.printStackTrace();
        };
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection urlConnection = null;
        try{
            urlConnection = (HttpURLConnection)url.openConnection();
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            InputStream content = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line=reader.readLine())!=null){
                stringBuilder.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            urlConnection.disconnect();
        }
        return stringBuilder.toString();
    }

    private class ReadJSONTask extends AsyncTask<String,Void,String>{
        protected String doInBackground(String... urls){
            return readJSON(urls[0]);
        }

        protected void onPostExecute(String result){
            try{
                JSONArray jsonArray = new JSONArray(result);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private class SendJSONTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            String data = "";
            HttpURLConnection httpURLConnection = null;
            try{
                httpURLConnection = (HttpURLConnection)new URL(params[0]).openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes("PostData="+params[1]);
                wr.flush();
                wr.close();

                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in);

                int inputStreamData = inputStreamReader.read();
                while (inputStreamData != -1){
                    char current = (char)inputStreamData;
                    inputStreamData = inputStreamReader.read();
                    data += current;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                httpURLConnection.disconnect();
            }
            return data;
        }

        protected void onPostExecute(String result){
            super.onPostExecute(result);
        }
    }

}
