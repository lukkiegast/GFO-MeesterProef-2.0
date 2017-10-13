package com.gfo.gfo_meesterproef.Admin;

import android.app.AlertDialog;

        import android.content.Context;
        import android.os.AsyncTask;
import android.widget.EditText;

import com.gfo.gfo_meesterproef.R;

import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.net.URLEncoder;

        import static android.R.attr.data;


public class AddAccountBackgroundWorker extends AsyncTask<String, Void, String> {


    Context context;
    AlertDialog alertDialog;

    AddAccountBackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "https://mantixcloud.nl/gfo/useradd.php";
        if(type.equals("add_account")) {
            try {
                String usernamec = params[1];
                String passwordc = params[2];
                String emailc = params[3];
                String adminflagc = params[4];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("usernamec","UTF-8")+"="+URLEncoder.encode(usernamec,"UTF-8")+"&"
                        +URLEncoder.encode("passwordc","UTF-8")+"="+URLEncoder.encode(passwordc,"UTF-8")+"&"
                        +URLEncoder.encode("emailc","UTF-8")+"="+URLEncoder.encode(emailc,"UTF-8")+"&"
                        +URLEncoder.encode("adminflagc","UTF-8")+"="+URLEncoder.encode(adminflagc,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() { }

    @Override
    protected void onPostExecute(String result) { }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}