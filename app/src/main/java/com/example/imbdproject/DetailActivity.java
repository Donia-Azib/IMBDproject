package com.example.imbdproject;

import androidx.appcompat.app.AppCompatActivity;
import id.zelory.compressor.Compressor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class DetailActivity extends AppCompatActivity {

    private IMBDmodel model ;
    private String plot,poster,awards,rating,released,title,year;
    private static final String API_KEY="3455f971";
    private TextView txt_genre,txt_awards,txt_rating,txt_released,txt_title,txt_totalSeasons;
    private ImageView img_poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        img_poster = findViewById(R.id.img);
        txt_title = findViewById(R.id.title);
        txt_released = findViewById(R.id.released);
        txt_genre = findViewById(R.id.genre);
        txt_awards = findViewById(R.id.awards);
        txt_rating = findViewById(R.id.rating);
        txt_totalSeasons = findViewById(R.id.total);


        Intent getInfos = getIntent();
        title = getInfos.getStringExtra("title");
        year = getInfos.getStringExtra("year");
        if (!title.isEmpty())
            SearchByYear(title,year);





    }
    private void SearchByYear(final String title, final String year) {
        String url;
        if (!year.isEmpty())

            url = "http://www.omdbapi.com/?t=" + title.replace(" ", "%20") + "&y=" + year + "&apikey=" + API_KEY;
        else {
            url = "http://www.omdbapi.com/?t=" + title.replace(" ", "%20") + "&apikey=" + API_KEY;
        }

        Ion.with(this)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null)
                            Toast.makeText(DetailActivity.this, "ERROR : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        else {
                            try {
                                if(result.get("Type").getAsString().equals("series"))
                                {
                                    txt_totalSeasons.setText("totalSeasons : "+result.get("totalSeasons").getAsString());
                                    txt_totalSeasons.setVisibility(View.VISIBLE);
                                }

                                Glide.with(DetailActivity.this).load(result.get("Poster").getAsString())
                                    .centerCrop().placeholder(R.drawable.imdb_icon)
                                    .into(img_poster);

                                txt_rating.setText("Rating : "+result.get("imdbRating").getAsString());
                                txt_title.setText(title.toUpperCase());
                                txt_released.setText("Released : "+result.get("Released").getAsString()); ;
                                txt_genre.setText("Genre : "+result.get("Genre").getAsString());
                                txt_awards.setText("Awards : "+result.get("Awards").getAsString());


                            } catch (Exception ex) {
                                Toast.makeText(DetailActivity.this, "EXCEPTION : " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        }
                    }
                });
    }}
