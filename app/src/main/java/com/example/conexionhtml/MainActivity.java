package com.example.conexionhtml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview1);
        editText=(EditText) findViewById(R.id.editText);


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
                url = new URL(urls[0]);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                r = new BufferedReader(new InputStreamReader(in));
                total = new StringBuilder();
                for (String line; (line = r.readLine()) != null; ) {
                    total.append(line).append('\n');
                }
                Texto = total.toString();
                Pattern pattern = Pattern.compile("<div id=\"paste\">(.*?)</div>");
                Matcher matcher = pattern.matcher(Texto);
                if (matcher.find()) {
                    Texto = matcher.group(1);

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            boolean found=false;
            if (Texto !=null){
               found = Texto.indexOf("<p>") != -1 ? true : false;
            }
            if(found=true && Texto !=null){
                String texto=Texto.replaceAll("<p>","" );
                texto=texto.replaceAll("</p>", "\n \n");
                Texto=texto;
            }
                return Texto;
            }


        protected void onPostExecute(String Texto) {

    textView.setText(Texto);


        }


    }




        public void crearasynctask(View v){
            String content;
            content = editText.getText().toString();
            new ConnectAsync().execute(content);
    }

    }

