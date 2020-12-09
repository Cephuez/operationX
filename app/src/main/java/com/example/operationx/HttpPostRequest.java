package com.example.operationx;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpPostRequest extends AsyncTask<Object, Void, Void> {
    static final String REQUEST_METHOD = "POST";
    static final int READ_TIMEOUT = 15000;
    static final int CONNECTION_TIMEOUT = 15000;

    @Override
    protected Void doInBackground(Object... objects) {

            // connect to the server

            HttpURLConnection urlConn = null;
            String result = "";

            JSONObject json = new JSONObject();


            Log.d("postTask: JO",json.toString());
            try {
                json.put("user", (String) objects[0]);
                json.put("score", (Integer) objects[1]);
                //URL address = new URL("https://kettlex-server.herokuapp.com/operationx/post");
                URL url;
                DataOutputStream printout;
                String address = "https://kettlex-server.herokuapp.com/operationx/post";
                Log.d("sendPost",address);
                url = new URL (address);
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput (true);
                urlConn.setDoOutput (true);
                urlConn.setUseCaches (false);
                urlConn.setRequestMethod("POST");
                urlConn.setChunkedStreamingMode(100);
                urlConn.setRequestProperty("Content-Type","application/json");
                urlConn.connect();
                // Send POST output.
                DataOutputStream os = new DataOutputStream(urlConn.getOutputStream());
                //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                os.writeBytes(json.toString());

                os.flush();
                os.close();

                Log.i("STATUS", String.valueOf(urlConn.getResponseCode()));
                Log.i("MSG" , urlConn.getResponseMessage());

                urlConn.disconnect();

               /* printout = new DataOutputStream(urlConn.getOutputStream());
                String output = URLEncoder.encode(json.toString(),"UTF-8");
                Log.d("postTaskURL",output);
                printout.writeUTF(json.toString());
                printout.flush();
                result = Integer.toString(urlConn.getResponseCode());
                Log.d("HTTP Response code", result);
                printout.close();*/
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }  finally {
                if(urlConn !=null)
                    urlConn.disconnect();
            }
            return null;
            /*HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setRequestProperty("Content-Type","application/json");
            connection.connect();

            // get the string from the input stream
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            reader.close();
            streamReader.close();
            result = stringBuilder.toString();
            System.out.println("GET result: " + result);

        } catch(IOException e) {
            e.printStackTrace();
            result = "error";
        }*/
    }
}
