package com.example.conexionhtml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    String url2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview1);
        url2="https://freetexthost.net/yQizOCl";


    }
    private class ConnectAsync extends AsyncTask<String, Void, String> {
        URL url;
        HttpURLConnection urlConnection;
        InputStream in;
        private Exception exception;
        BufferedReader r;
        StringBuilder total;
        TextView textView = (TextView) findViewById(R.id.textview1);
        String result;
        protected String doInBackground(String... urls) {

            String Texto = null;
            try {
                url = new URL(url2);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                r = new BufferedReader(new InputStreamReader(in));
                total = new StringBuilder();
                for (String line; (line = r.readLine()) != null; ) {
                    total.append(line).append('\n');
                }
                Texto = total.toString(); Texto = total.toString();
                Pattern pattern = Pattern.compile("<div id=\"paste\">(.*?)</div>");
                Matcher matcher = pattern.matcher(Texto);
                if (matcher.find())
                {
                    Texto=matcher.group(1);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return Texto;
        }

        protected void onPostExecute(String Texto) {

    textView.setText(Texto);


        }






        }

    }

