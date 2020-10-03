package id.my.codemerindu.ayomembacaa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawableWrapper;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import id.my.codemerindu.ayomembacaa.Adapter.BacaBukuOnlineAdapter;
import id.my.codemerindu.ayomembacaa.Model.Buku;

public class DetailBuku extends AppCompatActivity {

    String id;
    TextView judulDetail,pengarangDetail,mulaiBaca;

    FloatingActionButton Favorite;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buku);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        id = getIntent().getStringExtra("id_buku");

        actionBar = getSupportActionBar();


        judulDetail = findViewById(R.id.judulDetail);
        pengarangDetail = findViewById(R.id.PengarangDetail);
        Favorite = findViewById(R.id.btnFavorite);
        mulaiBaca = findViewById(R.id.btnMulaiBaca);
        mulaiBaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailBuku.this,BacaBukuOnline.class);
                intent.putExtra("id_buku",id);
                startActivity(intent);
            }
        });

        Toast.makeText(this,id,Toast.LENGTH_LONG).show();
        AndroidNetworking.initialize(getApplicationContext()); //inisialisasi FAN
        getData();
    }


    public void getData(){

        AndroidNetworking.get("https://muslikh.my.id/api/ayomembaca/bacabuku.php?id="+id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response", "onResponse: " + response); //untuk log pada onresponse
                        // do anything with response
                        {
                            //mengambil data dari JSON array pada read_all.php
                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject data = response.getJSONObject(i);
//
                                    judulDetail.setText(data.getString("judul"));
                                    pengarangDetail.setText(data.getString("nama"));
//                                    halaman.setText(data.getInt("halaman"));

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    } @Override
                    public void onError(ANError error) {
                        Log.d("Pesan gan", "onError: " + error); //untuk log pada onerror
                        // handle error
                    }
                });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
