package com.luongthuan.lab2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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

public class PostLoaderEx4 extends AsyncTask<String, Integer, String> {
    TextView tvResult4;
    ProgressDialog progressDialog;
    Context context;

    public PostLoaderEx4(Context context, TextView tvResult4) {
        this.tvResult4 = tvResult4;
        this.context = context;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Calculating ...!");
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

            String a = strings[1];
            String b = strings[2];
            String c = strings[3];
//            String ketqua = "";
//            double delta = (Double.parseDouble(b) * Double.parseDouble(b)) - (4 * Double.parseDouble(a) * Double.parseDouble(c));
//            if (Double.parseDouble(a) == 0) {
//                if (Double.parseDouble(b) == 0) {
//                    if (Double.parseDouble(c) == 0) {
//                        ketqua = "Vô số nghiệm";
//                    } else {
//                        ketqua = "Vô nghiệm";
//                    }
//
//                } else {
//                    double x1 = -(Double.parseDouble(c)) / Double.parseDouble(b);
//                    ketqua = "PT có 1 nghiệm duy nhất: " + x1;
//                }
//            } else {
//                if (delta < 0) {
//                    ketqua = "PT vô nghiệm";
//                } else {
//                    if (delta == 0) {
//                        double x = -(Double.parseDouble(b)) / (2 * Double.parseDouble(a));
//                        ketqua = "PT có một nghiệm kép: " + x;
//                    } else {
//                        double x1 = (-Double.parseDouble(b) + Math.sqrt(delta)) / (2 * Double.parseDouble(a));
//                        double x2 = (-Double.parseDouble(b) - Math.sqrt(delta)) / (2 * Double.parseDouble(a));
//                        ketqua = "PT có 2 nghiệm phân biệt x1: " + x1 + " ; x2: " + x2;
//                    }
//                }
//            }

            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            builder.append("a=").append(a).append("&b=").append(b).append("&c=").append(c);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(builder.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
            StringBuilder builder1 = new StringBuilder();
            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner sc = new Scanner(inputStream);
            while (sc.hasNext()) builder1.append(sc.nextLine());
            sc.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return  builder1.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (s != null) tvResult4.setText(s);
    }
}
