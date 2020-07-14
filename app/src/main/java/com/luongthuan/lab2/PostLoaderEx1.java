package com.luongthuan.lab2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class PostLoaderEx1 extends AsyncTask<String, Integer, String> {
    TextView tvResult;
    ProgressDialog progressDialog;
    Context context;

    public PostLoaderEx1(Context context,TextView tvResult) {
        this.tvResult = tvResult;
        this.context=context;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Sending ...!");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        String link = strings[0];
        StringBuilder builder = new StringBuilder();

        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");

            String name = strings[1];
            String score = strings[2];
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            builder.append("name=").append(name).append("&score=").append(score);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(builder.toString());
            bufferedWriter.flush();
            bufferedWriter.close();

            StringBuilder builder1=new StringBuilder();
            InputStream inputStream=httpURLConnection.getInputStream();
            Scanner sc=new Scanner(inputStream);
            while (sc.hasNext())builder1.append(sc.nextLine());
            sc.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return builder1.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Hahah",e.getMessage());
        }
        return builder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (s != null) tvResult.setText(s);
    }
}
