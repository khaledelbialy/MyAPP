package com.example.hp.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class MovieDetails extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView title;
    TextView overview;
    TextView userRate;
    TextView releaseDate;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        poster = (ImageView)findViewById(R.id.poster);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String json = MainActivity.postersData.get(position);
        Movie movie = JsonUtils.parseJson(json);

        if (movie == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }


        populateUI(movie);

        String imagePath="https://image.tmdb.org/t/p/w185/"+movie.getImage();
        Picasso.with(this)
                .load(imagePath)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(poster);
    }

    private void populateUI(Movie movie) {
        title = (TextView)findViewById(R.id.title);
        overview = (TextView)findViewById(R.id.plot_synopsis);
        releaseDate = (TextView)findViewById(R.id.date_release);
        userRate = (TextView) findViewById(R.id.vote_average);

        title.setText(movie.getTitle());
        overview.setText(movie.getOverView());
        releaseDate.setText(movie.getReleaseDate());
        userRate.setText(movie.getUsersRating());


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }
}
