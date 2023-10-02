package com.example.moviegamesapp.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviegamesapp.R;
import com.example.moviegamesapp.model.CustomAdapterMovieList;
import com.example.moviegamesapp.model.Movie;
import com.example.moviegamesapp.singletonclasses.GlobalSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MovieLibrary extends AppCompatActivity {

    private EditText editTextMovieLibrary;
    private Button buttonMovieLibrary;
    private ImageButton buttonBackMovieLibrary;
    private RecyclerView recyclerViewLibrary;
    private CustomAdapterMovieList customAdapterMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_library);

        editTextMovieLibrary = findViewById(R.id.editTextSearchMovieLibrary);
        buttonMovieLibrary = findViewById(R.id.buttonSearchMovie);
        buttonBackMovieLibrary = findViewById(R.id.imageButtonBackMovieLibrary);
        recyclerViewLibrary = findViewById(R.id.recyclerViewListLibrary);
        recyclerViewLibrary.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        LinearLayoutManager linearLayoutManger = new LinearLayoutManager(this);
        recyclerViewLibrary.setLayoutManager(linearLayoutManger);

        customAdapterMovieList = new CustomAdapterMovieList(GlobalSingleton.listMovies, this);
        recyclerViewLibrary.setAdapter(customAdapterMovieList);

        if (GlobalSingleton.listMovies.isEmpty()) getListTheMoviedb(0, null);

        buttonMovieLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalSingleton.listMovies.clear();

                if (editTextMovieLibrary.getText().toString().trim().isEmpty()) {
                    getListTheMoviedb(0, null);
                } else if (editTextMovieLibrary.getText().toString().equalsIgnoreCase("popular")) {
                    getListTheMoviedb(0, null);
                } else if (editTextMovieLibrary.getText().toString().equalsIgnoreCase("top")) {
                    getListTheMoviedb(1, null);
                } else {
                    getListTheMoviedb(2, editTextMovieLibrary.getText().toString().trim());
                }
            }
        });

        buttonBackMovieLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MovieLibrary.this,MenuActivity.class);
                startActivity(next);
            }
        });
    }

    public void getListTheMoviedb(int listType, String word) {
        try {
            String url = "https://api.themoviedb.org/3/movie/popular?language=es-US&page=1";
            if (listType == 0 ) {
                url = "https://api.themoviedb.org/3/movie/popular?language=es-US&page=1";
            } else if (listType == 1) {
                url = "https://api.themoviedb.org/3/movie/top_rated?language=es&page=1";
            } else {
                url = "https://api.themoviedb.org/3/search/movie?query=" + word +"&include_adult=false&language=es&page=1";
            }
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray results = response.getJSONArray("results");
                                for (int i = 0; i < results.length(); i++) {
                                    JSONObject pokemon = results.getJSONObject(i);
                                    String title = pokemon.getString("title");
                                    String date = pokemon.getString("release_date");
                                    String descripton = pokemon.getString("overview");
                                    String image = pokemon.getString("poster_path");
                                    Movie movie = new Movie(title, date, descripton, "https://image.tmdb.org/t/p/w500" + image);
                                    GlobalSingleton.listMovies.add(movie);
                                }
                                customAdapterMovieList.notifyDataSetChanged();

                            } catch (JSONException e) {
                                Toast.makeText(MovieLibrary.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MovieLibrary.this, "Error al obtener los datos" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() {
                    // Aquí puedes agregar los headers personalizados
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Accept", "application/json");
                    headers.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1N2E1NTBjZjQ1YzdjNDYxNGVlZmQ2NGRiNDVkNzczOCIsInN1YiI6IjY1MGE1ZDIxYWVkZTU5MDEzODg1YTViYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.5aA20cOhzDO2VunpPsq2P1HPZNj6ThWyMQU6yTy5tCI");
                    return headers;
                }
            };
            // Agregar la solicitud al RequestQueue
            requestQueue.add(request);
        } catch (Exception e) {
            Toast.makeText(MovieLibrary.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}