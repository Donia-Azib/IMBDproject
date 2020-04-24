package com.example.imbdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class MainActivity extends AppCompatActivity {

    private Button btnsearch;
    private EditText Edit_title,Edit_year;
    private String title,year;
    private static final String API_KEY="3455f971";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsearch = findViewById(R.id.btnsearch);
        Edit_title = findViewById(R.id.title);
        Edit_year = findViewById(R.id.year);

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = Edit_title.getText().toString();
                year = Edit_year.getText().toString();
                if(!title.isEmpty())
                {
                    SearchByYear(title,year);
                }
                else
                    Toast.makeText(MainActivity.this, "Please enter your film or serie title", Toast.LENGTH_SHORT).show();

            }
        });


    }



    private void SearchByYear(final String title, final String year) {
        String url;
        if (!year.isEmpty())

            url = "http://www.omdbapi.com/?t="+title.replace(" ","%20")+"&y="+year+"&apikey="+API_KEY;
        else
        {
            url = "http://www.omdbapi.com/?t=" + title.replace(" ", "%20") + "&apikey=" + API_KEY;
        }

        Ion.with(this)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                       if(e != null)
                           Toast.makeText(MainActivity.this, "ERROR : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                       else
                       {

                               Boolean rst = result.get("Response").getAsBoolean();
                               if (rst)
                               {
                                   Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                                   intent.putExtra("rst",rst);
                                   intent.putExtra("title",result.get("Title").getAsString());
                                   intent.putExtra("year",result.get("Year").getAsString());
                                   startActivity(intent);
                               }
                               else {
                                   String error = result.get("Error").getAsString();
                                   Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                               }
                       }
                    }
                });
    }
}
